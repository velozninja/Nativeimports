package org.example.C_CPP.C;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
//CLibrary represents a loaded .dll file and allows access to its functions.
public class CLibrary {
    //arena manages the memory allocated for the native library and controls its lifetime.
    private final Arena arena;
    //lookup is used to find functions exported by the loaded .dll file.
    private final SymbolLookup lookup;
    // Creates a shared memory arena and loads the .dll library for symbol lookup.
    public CLibrary(String path) {
        this.arena = Arena.ofShared();
        this.lookup = SymbolLookup.libraryLookup(path, arena);
    }

    // Loads a .dll file and returns it as a CLibrary object.
    public static CLibrary load(String path) {
        return new CLibrary(path);
    }
    // Finds an exported function in the loaded .dll and returns its memory address.
    public MemorySegment find(String function) {
        return lookup.find(function)
                .orElseThrow(() -> new UnsatisfiedLinkError("Function not found: " + function));
    }
}
