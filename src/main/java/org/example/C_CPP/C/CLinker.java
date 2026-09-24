package org.example.C_CPP.C;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
// Provides access to the native linker used to connect Java with native functions.
public class CLinker {
    // Provides access to the native linker.
    private final Linker linker = Linker.nativeLinker();
    // Creates a MethodHandle that can invoke the native function using the specified function descriptor.
    public MethodHandle link(MemorySegment function, FunctionDescriptor descriptor) {
        return linker.downcallHandle(function, descriptor);
    }
}
