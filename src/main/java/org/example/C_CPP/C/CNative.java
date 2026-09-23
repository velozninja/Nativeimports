package org.example.C_CPP.C;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.Arrays;

public class CNative {


    private final CLinker linker;
    private final CLibrary library;
    public CNative() {
        this.linker = new CLinker();
        this.library = null;

    }

    private CNative(String path) {
        this.linker = new  CLinker();
        this.library = CLibrary.load(path);
    }


    public CLibrary loader(String Path){
        return CLibrary.load(Path);
    }
    public CFunctions loaderf(CLibrary library, String name, CType returnType, CType... parameters){
        ValueLayout[] layouts = Arrays.stream(parameters)
                .map(CType::Layout)
                .toArray(ValueLayout[]::new);

        FunctionDescriptor descriptor = FunctionDescriptor.of(
                returnType.Layout(),
                layouts
        );
        MemorySegment function = library.find(name);
        MethodHandle handle = linker.link(function, descriptor);

         return new CFunctions(name, returnType, handle, parameters);
    }




}
