package com.github.okamumu.jmatout;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.Arrays;

import org.junit.Test;

public class TestMain {

	@Test
	public void test01() {
		MATLABDataElement da = MATLABDataElement.doubleMatrix("A", new int[] {2,5}, new double[] {1,2,3,4,5,6,7,8,9,10});
		MATLABDataElement sa = MATLABDataElement.doubleSparseMatrix("B", new int[] {3,3}, 3, new int[] {0,1,2}, new int[] {0,1,2,3}, new double[] {1,2,3});
		MATLABHeader header = new MATLABHeader();

		ByteBuffer buf_da = ByteBuffer.allocate(da.getByteNum());
		System.out.println("allocate for da: " + da.getByteNum());
		buf_da.order(ByteOrder.LITTLE_ENDIAN);
		da.write(buf_da);
		ByteBuffer buf_sa = ByteBuffer.allocate(sa.getByteNum());
		buf_sa.order(ByteOrder.LITTLE_ENDIAN);
		sa.write(buf_sa);
		ByteBuffer buf_header = ByteBuffer.allocate(header.getByteNum());
		buf_header.order(ByteOrder.LITTLE_ENDIAN);
		header.write(buf_header);
				
		System.out.println(Arrays.toString(buf_header.array()));
		
		FileOutputStream ostream;
		try {
			ostream = new FileOutputStream("test2.mat");
			FileChannel fc = ostream.getChannel();
			buf_header.flip();
			fc.write(buf_header);
			buf_da.flip();
			fc.write(buf_da);
			buf_sa.flip();
			fc.write(buf_sa);
			fc.close();
//			ostream.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
