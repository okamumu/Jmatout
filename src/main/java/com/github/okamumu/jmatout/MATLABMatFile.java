package com.github.okamumu.jmatout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for output of mat-file
 *
 */
public class MATLABMatFile {
	
	private final MATLABHeader header;
	private final List<MATLABDataElement> elements;
	private final MATLABEndian endian;

	/**
	 * Constructor
	 * @param endian An object of MATLABEndian
	 */
	public MATLABMatFile(MATLABEndian endian) {
		this.endian = endian;
		header = new MATLABHeader(endian);
		elements = new ArrayList<MATLABDataElement>();
	}

	/**
	 * The method to add an object of DataElement
	 * @param element An object of MATLABDataElement
	 */
	public void addDataElement(MATLABDataElement element) {
		elements.add(element);
	}

	/**
	 * The method to write the binary to the file.
	 * @param file An object of File.
	 * @throws IOException An exception when the given file is not opened and the data could not write to the file.
	 */
	public void writeToFile(File file) throws IOException {
		FileOutputStream ostream = new FileOutputStream(file);
		FileChannel outChannel = ostream.getChannel();
		ByteBuffer buf;
		buf = ByteBuffer.allocate(header.getByteNum());
		buf.order(endian.getByteBufferIndicator());
		header.write(buf);
		buf.flip();
		outChannel.write(buf);
		for (MATLABDataElement elem : elements) {
			buf = ByteBuffer.allocate(elem.getByteNum());
			buf.order(endian.getByteBufferIndicator());
			elem.write(buf);
			buf.flip();
			outChannel.write(buf);			
		}
		outChannel.close();
		ostream.close();
	}
}
