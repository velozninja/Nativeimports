package org.example.C_CPP.C;

import java.lang.foreign.ValueLayout;

public enum CType {
    INT(ValueLayout.JAVA_INT),
    LONG(ValueLayout.JAVA_LONG),
    FLOAT(ValueLayout.JAVA_FLOAT),
    DOUBLE(ValueLayout.JAVA_DOUBLE);
    private final ValueLayout layout;

    CType(ValueLayout layout) {
        this.layout = layout;
    }

    public ValueLayout Layout() {
        return layout;
    }
}
