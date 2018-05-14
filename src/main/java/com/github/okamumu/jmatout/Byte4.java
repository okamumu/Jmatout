package com.github.okamumu.jmatout;

import java.nio.ByteBuffer;

/**
 * A class to represent a 4 byte field.
 *
 */

public final class Byte4 implements ByteIF {

	private final int data;

	/**
	 * Constructor
	 * @param x A long integer
	 */
	public Byte4(long x) {
		data = (int) (x & 0x00000000ffffffffL);
	}

	/**
	 * Constructor
	 * @param x An integer
	 */
	public Byte4(int x) {
		data = x;
	}

	/**
	 * Get a value as int
	 * @return A value as int
	 */
	public int getInt() {
		return data;
	}

	@Override
	public int getByteNum() {
		return 4;
	}

	@Override
	public void write(ByteBuffer dos) {
		dos.putInt(data);
	}

	@Override
	public String toString() {
		return String.format("0x%08x", data);
	}
}
