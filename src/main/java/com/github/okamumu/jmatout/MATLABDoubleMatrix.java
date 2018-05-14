package com.github.okamumu.jmatout;

import java.nio.ByteBuffer;

/**
 * A class for double matrix
 *
 */
public class MATLABDoubleMatrix extends MATLABDataElement {
	private final int dataLength;
	private final MATLABArrayFlags arrayFlags;
	private final MATLABIntArray dimensionArray;
	private final MATLABString arrayName;
	private final MATLABDoubleArray realValue;
	
	/**
	 * Generate an object of MATLABDoubleMatrix
	 * @param name A string of label of matrix
	 * @param dims An array of integers to represent the dimensions
	 * @param pr An double array for the elements
	 */
	public static MATLABDoubleMatrix create(String name, int[] dims, double[] pr) {
		MATLABArrayFlags arrayFlags = MATLABArrayFlags.create(MATLABArrayType.mxDOUBLE_CLASS, false, false, false);
		MATLABIntArray dimensionArray = MATLABIntArray.create(dims);
		MATLABString arrayName = MATLABString.create(name);
		MATLABDoubleArray realValue = MATLABDoubleArray.create(pr);
		return new MATLABDoubleMatrix(arrayFlags, dimensionArray, arrayName, realValue,
				arrayFlags.getByteNum() + dimensionArray.getByteNum() + arrayName.getByteNum() + realValue.getByteNum());
	}
	
	/**
	 * Constructor
	 */
	private MATLABDoubleMatrix(
			MATLABArrayFlags arrayFlags,
			MATLABIntArray dimensionArray, 
			MATLABString arrayName,
			MATLABDoubleArray realValue,
			int dataLength) {
		super(MATLABDataType.miMATRIX, dataLength);
		this.arrayFlags = arrayFlags;
		this.dimensionArray = dimensionArray;
		this.arrayName = arrayName;
		this.realValue = realValue;
		this.dataLength = dataLength;
	}

	@Override
	public int getByteNum() {
		return super.getByteNum() + dataLength;
	}

	@Override
	public void write(ByteBuffer dos) {
		super.write(dos);
		arrayFlags.write(dos);
		dimensionArray.write(dos);
		arrayName.write(dos);
		realValue.write(dos);
	}
}
