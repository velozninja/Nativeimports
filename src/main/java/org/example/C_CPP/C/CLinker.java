package org.example.C_CPP.C;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;

public class CLinker {
    private final Linker linker = Linker.nativeLinker();

    public MethodHandle link(MemorySegment function, FunctionDescriptor descriptor) {
        return linker.downcallHandle(function, descriptor);
    }
}
