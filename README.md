# Jmatout

This is a Java library to output the MAT file of MATLAB.

## Introduction

This library provides the functions to write MATLAB matrix according to MATLAB level 5 MAT-File format.
The current version handles the numeric array with real values, and the sparse array with real values.
The real values are presented by double-type variables.

See http://www.mathworks.com/help/pdf_doc/matlab/matfile_format.pdf in the detail of MAT-File formant.

Note that this library cannot read MAT-file, that only provides the write methods.

## Build

This library is build by Gradle. To obtain the jar file, please type the following command
```
gradle jar
```
The compiled jar file is found in `build/libs` directory.

## API Document

API Document is available in https://okamumu.github.io/Jmatout/javadoc/

## Example

In this example, we write the following two matrices;

```
A = [1, 2, 3, 4, 5; 6, 7, 8, 9, 10] # 2x5 matrix
B = sparse([1, 0, 0; 0, 2, 0; 0, 0, 3])  # 3x3 matrix
```

The corresponding Java codes are

```java
// create MATLABMatFile object with LittleEndian
MATLABMatFile matfile = new MATLABMatFile(MATLABEndian.LittleEndian);

// create and add DataElement; 2x5 matrix labeled by A. Note that the values are given by column major.
matfile.addDataElement(MATLABDataElement.doubleMatrix("A", new int[] {2,5}, new double[] {1,2,3,4,5,6,7,8,9,10}));

// create and add DataElement; 3x3 sparse matrix, which is CSR (compressed sparse row)
matfile.addDataElement(MATLABDataElement.doubleSparseMatrix("B", new int[] {3,3}, 3, new int[] {0,1,2}, new int[] {0,1,2,3}, new double[] {1,2,3}));

try {
  // write the two matrices into the file "test1.mat"
  matfile.writeToFile(Paths.get("test1.mat").toFile());
} catch (IOException e) {
  e.printStackTrace();
}
```
