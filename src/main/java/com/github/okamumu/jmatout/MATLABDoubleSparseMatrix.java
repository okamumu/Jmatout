package com.github.okamumu.jmatout;

import java.nio.ByteBuffer;

/**
 * A class for sparse matrix.
 * The format of sparse matrix is CSR (compressed sparse row) with 0 origin.
 *
 */
public class MATLABDoubleSparseMatrix extends MATLABDataElement {

	private final int dataLength;
	private final MATLABArrayFlags arrayFlags;
	private final MATLABIntArray dimensionArray;
	private final MATLABString arrayName;
	private final MATLABIntArray ir;
	private final MATLABIntArray jc;
	private final MATLABDoubleArray pr;

	/**
	 * Generate an object of MATLABDoubleSparseMatrix
	 * @param name A string of label of matrix
	 * @param dims An array of integers to represent the dimension
	 * @param nnz A value of non-zero elements
	 * @param ir An array of integers for rowptr
	 * @param jc An array of integers for colind
	 * @param pr An array of double for the values
	 * @return An object of MATLABDoubleSparseMatrix
	 */
	public static MATLABDoubleSparseMatrix create(String name, int[] dims, int nnz, int[] ir, int[] jc, double[] pr) {
		MATLABArrayFlags arrayFlags = MATLABArrayFlags.create(nnz, MATLABArrayType.mxSPARSE_CLASS, false, false, false);
		MATLABIntArray dimensionArray = MATLABIntArray.create(dims);
		MATLABString arrayName = MATLABString.create(name);
		MATLABIntArray rowptr = MATLABIntArray.create(ir);
		MATLABIntArray colind = MATLABIntArray.create(jc);
		MATLABDoubleArray realValue = MATLABDoubleArray.create(pr);
		return new MATLABDoubleSparseMatrix(arrayFlags, dimensionArray, arrayName,
				rowptr, colind, realValue,
				arrayFlags.getByteNum() + dimensionArray.getByteNum() + arrayName.getByteNum()
				+ rowptr.getByteNum() + colind.getByteNum() + realValue.getByteNum());
	}

	private MATLABDoubleSparseMatrix(
			MATLABArrayFlags arrayFlags,
			MATLABIntArray dimensionArray, 
			MATLABString arrayName,
			MATLABIntArray ir,
			MATLABIntArray jc,
			MATLABDoubleArray pr,
			int dataLength) {
		super(MATLABDataType.miMATRIX, dataLength);
		this.arrayFlags = arrayFlags;
		this.dimensionArray = dimensionArray;
		this.arrayName = arrayName;
		this.ir = ir;
		this.jc = jc;
		this.pr = pr;
		this.dataLength = dataLength;
	}

	@Override
	public int getByteNum() {
		return super.getByteNum() + dataLength;
	}
	
	@Override
	public void write(ByteBuffer dos) {
		super.write(dos);
		arrayFlags.write(dos);
		dimensionArray.write(dos);
		arrayName.write(dos);
		ir.write(dos);
		jc.write(dos);
		pr.write(dos);
	}
}
