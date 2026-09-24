package org.example.C_CPP.C;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.Arrays;
// Stores the native linker used to connect Java with C functions.
public class CNative {

    // Stores the native linker used to connect Java with C functions.
    private final CLinker linker;

    // Stores the loaded native library.
    private final CLibrary library;
    // Initializes the native linker without loading a library.
    public CNative() {
        this.linker = new CLinker();
        this.library = null;

    }
    // Initializes the native linker and loads the specified .dll file.
    private CNative(String path) {
        this.linker = new  CLinker();
        this.library = CLibrary.load(path);
    }

    // Loads a .dll file and returns it as a CLibrary object.
    public CLibrary loader(String Path){
        return CLibrary.load(Path);
    }
    // Creates a CFunctions object for the specified native function.
    public CFunctions loaderf(CLibrary library, String name, CType returnType, CType... parameters){
        ValueLayout[] layouts = Arrays.stream(parameters)
                .map(CType::Layout)
                .toArray(ValueLayout[]::new);

        // Describes the return type and parameter types of the native function.
        FunctionDescriptor descriptor = FunctionDescriptor.of(
                returnType.Layout(),
                layouts
        );
        // Stores the memory address of the native function.
        MemorySegment function = library.find(name);
        // Stores the handle used to invoke the native function.
        MethodHandle handle = linker.link(function, descriptor);

         return new CFunctions(name, returnType, handle, parameters);
    }




}
