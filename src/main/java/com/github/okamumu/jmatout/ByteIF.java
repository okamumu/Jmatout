package com.github.okamumu.jmatout;

import java.nio.ByteBuffer;

public interface ByteIF {

	/**
	 * Get the size as bytes
	 * @return An integer for the size of bytes
	 */
	int getByteNum();
	
	/**
	 * Write a value to the buffer (file).
	 * @param dos A bytebuffer object
	 */
	void write(ByteBuffer dos);

}
