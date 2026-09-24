package org.example.C_CPP.C;

import java.lang.invoke.MethodHandle;
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
    public Object call(Object... args) throws Throwable {
        return Handle.invokeWithArguments(args);
    }
}
