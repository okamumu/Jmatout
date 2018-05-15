package com.github.okamumu.jmatout;

import java.nio.ByteBuffer;

/**
 * A class to represent double array
 *
 */
public class MATLABDoubleArray extends MATLABDataElement {

	private final int dataLength;
	private final double[] data;
	private final ZeroPadding zero;
	
	/**
	 * The method to generate MATLABDoubleArray
	 * @param x An array of double
	 * @return An object of MATLABDoubleArray
	 */
	public static MATLABDoubleArray create(double[] x) {
		return new MATLABDoubleArray(x, x.length * 8);
	}

	/**
	 * Constructor
	 * @param x An array of double
	 * @param dataLength An integer for the number of bytes of data
	 */
	private MATLABDoubleArray(double[] x, int dataLength) {
		super(MATLABDataType.miDOUBLE, dataLength);
		this.dataLength = dataLength;
		data = x;
		zero = super.createZeroPadding(dataLength);
	}
	
	@Override
	public int getByteNum() {
		return super.getByteNum() + dataLength + zero.getByteNum();
	}

	@Override
	public void write(ByteBuffer dos) {
		super.write(dos);
		for (double x : data) {
			dos.putDouble(x);
		}
		zero.write(dos);
	}
}
