package org.example;

import org.example.C_CPP.C.CFunctions;
import org.example.C_CPP.C.CLibrary;
import org.example.C_CPP.C.CNative;
import org.example.C_CPP.C.CType;

import javax.sound.midi.MetaEventListener;
import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class Main {


    public static void main(String[] args) throws Throwable {

        CNative Cnative = new CNative();

        CLibrary lib = Cnative.loader("Soma.dll");
        CFunctions func = Cnative.loaderf(lib,"soma", CType.INT, CType.INT, CType.INT);

        int result = (int) func.call(10,20);

        System.out.println(result);


    }



}
