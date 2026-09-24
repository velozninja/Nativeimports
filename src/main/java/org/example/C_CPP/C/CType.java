package org.example.C_CPP.C;

import java.lang.foreign.ValueLayout;
// Defines the C types supported by the library.
public enum CType {
    INT(ValueLayout.JAVA_INT),
    LONG(ValueLayout.JAVA_LONG),
    FLOAT(ValueLayout.JAVA_FLOAT),
    DOUBLE(ValueLayout.JAVA_DOUBLE);
    // Stores the memory layout associated with the C type.
    private final ValueLayout layout;
    // Initializes a C type with its corresponding memory layout.
    CType(ValueLayout layout) {
        this.layout = layout;
    }
    // Returns the memory layout of the C type.
    public ValueLayout Layout() {
        return layout;
    }
}
