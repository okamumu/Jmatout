package com.github.okamumu.jmatout;

import java.nio.ByteBuffer;

/**
 * A class to represent a 2 byte field.
 *
 */

public class Byte2 implements ByteIF {

	private final short data;

	/**
	 * Constructor
	 * @param x A long integer
	 */
	public Byte2(long x) {
		data = (short) (x & 0x0000_0000_0000_ffffL);
	}

	/**
	 * Constructor
	 * @param x An integer
	 */
	public Byte2(int x) {
		data = (short) (x & 0x0000_ffff);
	}

	@Override
	public int getByteNum() {
		return 2;
	}

	@Override
	public void write(ByteBuffer dos) {
		dos.putShort(data);
	}

	@Override
	public String toString() {
		return String.format("0x%04x", data);
	}

}
