# NativeImports
It is a Java FFI library that makes it easier to import C functions.
**This library works only in Maven projects.**

## Installation

Add this to the pom.xml file.

```xml
<dependency>
    <groupId>io.github.velozninja</groupId>
    <artifactId>NativeImports</artifactId>
    <version>1.0.1</version>
</dependency>

````
---
## How do I use it?
Use the CNative class to utilize the feature provided by the library. **This library supports only .dll files.***

### Supported types
- INT
- LONG
- FLOAT
- DOUBLE

### How to use the methods?
To use the methods of the class containing the main resources (CNative), refer to the topics below for guidance.

#### loader

````java
        CNative Cnative = new CNative();

        CLibrary lib = Cnative.loader("C:example\users\user\desktop\file.dll");
````
or if the file is in the same directory as the pom.xml
````java
        CNative Cnative = new CNative();

        CLibrary lib = Cnative.loader("file.dll");

````
---
#### loaderf
````java

        CFunctions func = Cnative.loaderf(lib,"function_name", CType.INT, CType.INT, CType.INT);

       
````
The first parameter represents the library or file to which the function belongs; the second is the function name;
the third is the function type (note: this type must belong to the `CType` class; otherwise, the method will not work);
and the subsequent parameters represent the method's parameter types (note: these must also belong to the `CType` class, and there is no limit on the number of parameters).
---
#### call
````java
 int result = (int) func.call("parameters");

````
This method receives the function parameters and must undergo type conversion to a Java primitive type for the return value.

---
#### example
````java
package org.example;

import org.example.C_CPP.C.CFunctions;
import org.example.C_CPP.C.CLibrary;
import org.example.C_CPP.C.CNative;
import org.example.C_CPP.C.CType;


import java.lang.foreign.*;


public class Main {


    public static void main(String[] args) throws Throwable {

        CNative Cnative = new CNative();

        CLibrary lib = Cnative.loader("math.dll");
        CFunctions func = Cnative.loaderf(lib,"sum", CType.INT, CType.INT, CType.INT);

        int result = (int) func.call(10,20);

        System.out.println(result);


    }



}


````
---
The result will display the value 30 in the console.

---
````terminaloutput
30
````






