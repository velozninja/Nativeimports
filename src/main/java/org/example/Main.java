package org.example;

import org.example.C_CPP.C.CFunctions;
import org.example.C_CPP.C.CLibrary;
import org.example.C_CPP.C.CNative;
import org.example.C_CPP.C.CType;


import java.lang.foreign.*;


public class Main {


    public static void main(String[] args) throws Throwable {
        CNative Cnative = new CNative();
        // Creates a CNative instance to access the native library functionality.

        // Loads the Soma.dll file.
        CLibrary lib = Cnative.loader("Soma.dll");

       // Creates a CFunctions object for the "soma" function.
        CFunctions func = Cnative.loaderf(
                lib,
                "soma",
                CType.INT,
                CType.INT,
                CType.INT
        );

        // Calls the native function with two integer parameters.
        int result = (int) func.call(10, 20);

       // Prints the result returned by the native function.
        System.out.println(result);



    }



}
