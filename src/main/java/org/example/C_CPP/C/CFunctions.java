package org.example.C_CPP.C;

import java.lang.invoke.MethodHandle;

public class CFunctions {
    private final String name;



    private final CType returnType;
    private final CType[] parameters;
    private final MethodHandle Handle;

    public CFunctions(String name, CType returnType, MethodHandle handle, CType... parameters) {
        this.name = name;
        this.returnType = returnType;
        this.Handle = handle;
        this.parameters = parameters;
    }
    public String getName() {
        return name;
    }
    public CType getReturnType() {
        return returnType;
    }

    public CType[] getParameters() {
        return parameters;
    }
    public Object call(Object... args) throws Throwable {
        return Handle.invokeWithArguments(args);
    }
}
