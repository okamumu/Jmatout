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
	private final short version;
	private final MATLABEndian endian;

	/**
	 * Constructor
	 * @param endian An object of MATLABEndian to indicate the endian.
	 */
	public MATLABHeader(MATLABEndian endian) {
		text = "MATLAB 5.0 MAT-file, Platform: " + System.getProperty("os.name")
			+ ", CREATED on: ";
		version = 0x0100;
		this.endian = endian;
	}
	
	@Override
	public void write(ByteBuffer dos) {
		Date date = new Date();
		byte[] desc = Arrays.copyOf((text + date.toString()).getBytes(), 116);
		dos.put(desc);
		dos.putInt(0); // subsys data offset
		dos.putInt(0); // subsys data offset
		dos.putShort(version);
		dos.put(endian.getMATLABIndicator());
	}

	@Override
	public int getByteNum() {
		return 128;
	}
}
