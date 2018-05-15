package com.github.okamumu.jmatout;

import java.nio.ByteOrder;

/**
 * The enumerate class to indicate the endian.
 *
 */
public enum MATLABEndian {
	BigEndian("MI", ByteOrder.BIG_ENDIAN),
	LittleEndian("IM", ByteOrder.LITTLE_ENDIAN),
	;

	private final byte[] endian;
	private final ByteOrder order;

	private MATLABEndian(String endian, ByteOrder order) {
		this.endian = endian.getBytes();
		this.order = order;
	}

	/**
	 * The method to get the bye sequence to indicate big/little endian in MATLAB.
	 * "MI" indicates the big endian.
	 * "IM" indicates the little endian.
	 * @return the byte sequence.
	 */
	public byte[] getMATLABIndicator() {
		return endian;
	}

	/**
	 * The method to get the indicator for ByteBuffer.
	 * @return An object of enumerate class of ByteOrder
	 */
	public ByteOrder getByteBufferIndicator() {
		return order;
	}
}
