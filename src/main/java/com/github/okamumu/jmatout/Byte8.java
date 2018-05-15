package com.github.okamumu.jmatout;

import java.nio.ByteBuffer;

/**
 * A class to represent 8 byte field
 *
 */
public class Byte8 implements ByteIF {

	private long data;

	/**
	 * Constructor
	 * @param x A long integer to represent 8 bytes
	 */
	public Byte8(long x) {
		data = x;
	}

	@Override
	public int getByteNum() {
		return 8;
	}

	@Override
	public void write(ByteBuffer dos) {
		dos.putLong(data);
	}

	@Override
	public String toString() {
		return String.format("0x%016x", data);
	}
}
