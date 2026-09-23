package org.example.C_CPP.C;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;

public class CLibrary {
    private final Arena arena;
    private final SymbolLookup lookup;

    public CLibrary(String path) {
        this.arena = Arena.ofShared();
        this.lookup = SymbolLookup.libraryLookup(path, arena);
    }


    public static CLibrary load(String path) {
        return new CLibrary(path);
    }

    public MemorySegment find(String function) {
        return lookup.find(function)
                .orElseThrow(() -> new UnsatisfiedLinkError("Function not found: " + function));
    }
}
