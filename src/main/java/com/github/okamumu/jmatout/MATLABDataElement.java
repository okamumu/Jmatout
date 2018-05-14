package com.github.okamumu.jmatout;

import java.nio.ByteBuffer;

/**
 * A class to represent a tag of data element.
 *
 */
public class MATLABDataElement implements ByteIF {

	private final ByteIF dataType;
	private final ByteIF numOfDataByte;
	
	/**
	 * Constructor for a tag.
	 * @param dataType An object of MATLABDataType
	 * @param numOfDataByte An long integer to represent the number of bytes of data
	 */
	public MATLABDataElement(MATLABDataType dataType, long numOfDataByte) {
		if (numOfDataByte <= 4) { // small data element
			this.dataType = new Byte2(dataType.getID());
			this.numOfDataByte = new Byte2(numOfDataByte);
		} else {
			this.dataType = new Byte4(dataType.getID());
			this.numOfDataByte = new Byte4(numOfDataByte);			
		}
	}

	/**
	 * Compute the size of array with padding (number of bytes)
	 * @param totalBytes An integer to represent the number of total bytes of data.
	 * @return the number of bytes required by the data array
	 */
	protected int computeSizeOfArrayWithPadding(int totalDataBytes) {
		int tmp = totalDataBytes + dataType.getByteNum() + numOfDataByte.getByteNum();
		return totalDataBytes + (8 - tmp % 8) % 8;
	}

	@Override
	public int getByteNum() {
		return dataType.getByteNum() + numOfDataByte.getByteNum();
	}

	@Override
	public void write(ByteBuffer dos) {
		dataType.write(dos);
		numOfDataByte.write(dos);
	}

	/**
	 * The method to generate MATLABString
	 * @param x A string character (do not use 2 byte characters such as UTF)
	 * @return MATLADataElement
	 */
	public static MATLABDataElement string(String x) {
		return MATLABString.create(x);
	}
	
	/**
	 * The method to generate MATLABIntArray
	 * @param x An array of integers
	 * @return MATLADataElement
	 */
	public static MATLABDataElement intArray(int[] x) {
		return MATLABIntArray.create(x);
	}

	/**
	 * The method to generate MATLABDoubleArray
	 * @param x An array of double
	 * @return MATLADataElement
	 */
	public static MATLABDataElement doubleArray(double[] x) {
		return MATLABDoubleArray.create(x);
	}

	/**
	 * The method to generate MATLABDoubleMatrix
	 * @param name A string of label of matrix
	 * @param dims An array of integers to represent the dimensions
	 * @param pr An double array for the elements
	 * @return MATLADataElement
	 */
	public static MATLABDataElement doubleMatrix(String name, int[] dims, double[] pr) {
		return MATLABDoubleMatrix.create(name, dims, pr);
	}

	/**
	 * Generate an object of MATLABDoubleSparseMatrix
	 * @param name A string of label of matrix
	 * @param dims An array of integers to represent the dimension
	 * @param nnz A value of non-zero elements
	 * @param ir An array of integers for rowptr
	 * @param jc An array of integers for colind
	 * @param pr An array of double for the values
	 * @return An object of MATLABDoubleSparseMatrix
	 */
	public static MATLABDataElement doubleSparseMatrix(String name, int[] dims, int nnz, int[] ir, int[] jc, double[] pr) {
		return MATLABDoubleSparseMatrix.create(name, dims, nnz, ir, jc, pr);
	}
}
