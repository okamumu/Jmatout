package com.github.okamumu.jmatout;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

/**
 * A class for the header of MAT-file
 *
 */
public class MATLABHeader implements ByteIF {

	private final String text;
	private final Date date;
	private final short version;
	private final String endian;

	/**
	 * Constructor
	 */
	public MATLABHeader() {
		text = "MATLAB 5.0 MAT-file, Platform: " + System.getProperty("os.name")
			+ ", CREATED on: ";
		date = new Date();
		version = 0x0100;
		endian = "MI";
	}

	@Override
	public void write(ByteBuffer dos) {
		byte[] desc = Arrays.copyOf((text + date.toString()).getBytes(), 116);
		dos.put(desc);
		dos.putInt(0); // subsys data offset
		dos.putInt(0); // subsys data offset
		dos.putShort(version);
		dos.put(endian.getBytes());
	}

	@Override
	public int getByteNum() {
		return 128;
	}
}
