package com.github.okamumu.jmatout;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * A class for MATLAB String for characters
 *
 */
public class MATLABString extends MATLABDataElement {

	private final byte[] data;

	/**
	 * The method to generate MATLABString
	 * @param x A string character (do not use 2 byte characters such as UTF)
	 * @return An object of MATLABString
	 */
	public static MATLABString create(String x) {
		return new MATLABString(x, x.length());
	}

	/**
	 * Constructor
	 * @param x A string character (do not use 2 byte characters such as UTF)
	 * @param dataLength An integer for the number of bytes of data
	 */
	private MATLABString(String x, int dataLength) {
		super(MATLABDataType.miINT8, dataLength);
		data = Arrays.copyOf(x.getBytes(), computeSizeOfArrayWithPadding(dataLength));
	}
	
	@Override
	public int getByteNum() {
		return super.getByteNum() + data.length;
	}

	@Override
	public void write(ByteBuffer dos) {
		super.write(dos);
		for (byte b : data) {
			dos.put(b);
		}
	}
}
