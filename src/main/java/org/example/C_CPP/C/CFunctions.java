package org.example.C_CPP.C;

import org.example.C_CPP.C.exceptions.InvalidTypeOfParameters;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// This class handles the implementation of C functions.
public class CFunctions {
    private final String name;

    // This is return type of C function
    private final CType returnType;
    // This is parameters of C function
    private final CType[] parameters;
    // This Stores a reference to the C function that can be invoked from Java.
    private final MethodHandle Handle;
    // This contructor Initializes a CFunctions object with the function name, return type, native function handle, and parameter types.
    public CFunctions(String name, CType returnType, MethodHandle handle, CType... parameters) {

        this.name = name;
        this.returnType = returnType;
        this.Handle = handle;
        this.parameters = parameters;
    }
    //This is C funtion name Getter
    public String getName() {
        return name;
    }
    //This is C function return type getter
    public CType getReturnType() {
        return returnType;
    }
    //This is C function parameters getter
    public CType[] getParameters() {
        return parameters;
    }
    //This method executes the C function.
    /**
     * Invokes a loaded C function and converts Java arguments
     * into the types expected by the native function.
     *
     * @param args arguments to be passed to the native function.
     * @return the value returned by the native function.
     * @throws Throwable if the native function invocation or conversion fails.
     */
    public Object call(Object... args) throws Throwable {

        // Creates a temporary Arena used to allocate native memory,
        // such as memory for C strings.
        try (Arena arena = Arena.ofConfined()) {

            // Stores the arguments in the format expected
            // by the native MethodHandle.
            Object[] nativeArgs = new Object[args.length];

            // Iterates through all provided arguments.
            for (int i = 0; i < args.length; i++) {

                // Checks whether the current parameter is a C string (char*).
                if (parameters[i] == CType.STRING) {
                    String value;

                    // Accepts a Java String directly.
                    if (args[i] instanceof String text) {
                        value = text;

                        // Also accepts a char[].
                    } else if (args[i] instanceof char[] chars) {
                        value = new String(chars);

                        // Rejects unsupported types.
                    } else {
                        throw new IllegalArgumentException(
                                "Expected String or char[] for parameter " + i
                        );
                    }

                    // Converts the String to UTF-8 and adds
                    // the null terminator required by C strings.
                    byte[] bytes = (value + "\0").getBytes(StandardCharsets.UTF_8);

                    // Allocates native memory inside the Arena.
                    MemorySegment segment = arena.allocate(bytes.length);

                    // Copies the string bytes into native memory.
                    segment.copyFrom(MemorySegment.ofArray(bytes));

                    // Stores the native memory segment as the argument.
                    nativeArgs[i] = segment;

                } else {
                    // Passes non-string arguments directly.
                    nativeArgs[i] = args[i];
                }
            }

            // Invokes the native function through the MethodHandle.
            Object result = Handle.invokeWithArguments(nativeArgs);

            // If the native function returns a C string (char*),
            // converts the returned memory segment into a Java String.
            if (returnType == CType.STRING) {
                MemorySegment segment = (MemorySegment) result;

                // Allows access to the returned memory and reads
                // the string starting at memory offset 0.
                return segment.reinterpret(1024).getString(0);
            }

            // Returns the result directly for other types.
            return result;

            // Converts type-casting errors into the library's
            // custom parameter type exception.
        } catch (ClassCastException e) {
            throw new InvalidTypeOfParameters("invalid type of parameters");
        }
    }


}


