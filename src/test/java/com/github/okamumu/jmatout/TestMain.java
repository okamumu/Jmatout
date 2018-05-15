package com.github.okamumu.jmatout;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;

public class TestMain {

	@Test
	public void testByte2() {
		Byte2 x = new Byte2(100);
		assertEquals("Test for getByte", 2, x.getByteNum());
		assertEquals("Test for toString", "0x0064", x.toString());
	}

	@Test
	public void testByte4() {
		Byte4 x = new Byte4(100);
		assertEquals("Test for getByte", 4, x.getByteNum());
		assertEquals("Test for toString", "0x00000064", x.toString());
	}

	@Test
	public void testByte8() {
		Byte8 x = new Byte8(100);
		assertEquals("Test for getByte", 8, x.getByteNum());
		assertEquals("Test for toString", "0x0000000000000064", x.toString());
	}

	@Test
	public void test01() {
		MATLABMatFile matfile = new MATLABMatFile(MATLABEndian.LittleEndian);
		matfile.addDataElement(MATLABDataElement.doubleMatrix("A", new int[] {2,5}, new double[] {1,2,3,4,5,6,7,8,9,10}));
		matfile.addDataElement(MATLABDataElement.doubleSparseMatrix("B", new int[] {3,3}, 3, new int[] {0,1,2}, new int[] {0,1,2,3}, new double[] {1,2,3}));

		try {
			matfile.writeToFile(Paths.get("test1.mat").toFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("error");
		}
	}

	@Test
	public void test02() {
		MATLABMatFile matfile = new MATLABMatFile(MATLABEndian.BigEndian);
		matfile.addDataElement(MATLABDataElement.doubleMatrix("A", new int[] {2,5}, new double[] {1,2,3,4,5,6,7,8,9,10}));
		matfile.addDataElement(MATLABDataElement.doubleSparseMatrix("B", new int[] {3,3}, 3, new int[] {0,1,2}, new int[] {0,1,2,3}, new double[] {1,2,3}));

		try {
			matfile.writeToFile(Paths.get("test2.mat").toFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("error");
		}
	}
}
