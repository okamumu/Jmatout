package com.github.okamumu.jmatout;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * A class to represent int array
 *
 */
public class MATLABIntArray extends MATLABDataElement {

	private final int dataLength;
	private final int[] data;
	private final ZeroPadding zero;
	
	/**
	 * The method to generate MATLABIntArray
	 * @param x An array of int
	 * @return An object of MATLABIntArray
	 */
	public static MATLABIntArray create(int[] x) {
		return new MATLABIntArray(x, x.length * 4);
	}

	/**
	 * Constructor
	 * @param x An array of int
	 * @param dataLength An integer for the number of bytes of data
	 */
	private MATLABIntArray(int[] x, int dataLength) {
		super(MATLABDataType.miINT32, dataLength);
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
		for (int x : data) {
			dos.putInt(x);
		}
		zero.write(dos);
	}
}
