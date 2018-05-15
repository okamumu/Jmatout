package com.github.okamumu.jmatout;

import java.nio.ByteBuffer;

/**
 * A class to represent zero padding.
 *
 */
public class ZeroPadding implements ByteIF {

	private final int size;

	/**
	 * The method to generate ZeroPadding with any byte segment.
	 * @param totalBytes An integer for the total number of data bytes.
	 * @param segmentByte An integer for the segment.
	 * @return An object of ZeroPadding
	 */
	public static ZeroPadding create(int totalBytes, int segmentByte) {
		int size = (segmentByte - totalBytes % segmentByte) % segmentByte;
		return new ZeroPadding(size);
	}

	/**
	 * Constructor
	 * @param size An integer meaning the number of bytes
	 */
	public ZeroPadding(int size) {
		this.size = size;
	}

	@Override
	public int getByteNum() {
		return size;
	}

	@Override
	public void write(ByteBuffer dos) {
		for (int i=0; i<size; i++) {
			dos.put((byte) 0);
		}
	}

}
