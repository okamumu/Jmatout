package com.github.okamumu.jmatout;

import java.nio.ByteBuffer;

/**
 * A class to represent the header of arrays.
 *
 */
public class MATLABArrayFlags extends MATLABDataElement {

	private static final int complexBit = 0x0000_0700;
	private static final int globalBit = 0x0000_0400;
	private static final int logicalBit = 0x0000_2000;

	private final Byte4 flag1;
	private final Byte4 flag2;

	/**
	 * Generate an object of array flags.
	 * @param type An object of ArrayType
	 * @param complex A boolean to use complex
	 * @param global A boolean to use global
	 * @param logical A boolean to use logical
	 * @return An object of MATLABArrayFlags
	 */
	public static MATLABArrayFlags create(MATLABArrayType type, boolean complex, boolean global, boolean logical) {
		return new MATLABArrayFlags(type, complex, global, logical);
	}

	/**
	 * Generate an object of array flags.
	 * @param nnz An integer value (it is used to represent the NNZ of sparse matrix)
	 * @param type An object of ArrayType
	 * @param complex A boolean to use complex
	 * @param global A boolean to use global
	 * @param logical A boolean to use logical
	 * @return An object of MATLABArrayFlags
	 */
	public static MATLABArrayFlags create(int nnz, MATLABArrayType type, boolean complex, boolean global, boolean logical) {
		return new MATLABArrayFlags(nnz, type, complex, global, logical);
	}

	/**
	 * Constructor
	 * @param type An object of ArrayType
	 * @param complex A boolean to use complex
	 * @param global A boolean to use global
	 * @param logical A boolean to use logical
	 */
	public MATLABArrayFlags(MATLABArrayType type, boolean complex, boolean global, boolean logical) {
		super(MATLABDataType.miUINT32, 8);
		int flag = type.getID();
		flag |= complex?complexBit:0;
		flag |= global?globalBit:0;
		flag |= logical?logicalBit:0;
		flag1 = new Byte4(flag);
		flag2 = new Byte4(0);
	}

	/**
	 * Constructor
	 * @param x An integer
	 * @param type An object of ArrayType
	 * @param complex A boolean to use complex
	 * @param global A boolean to use global
	 * @param logical A boolean to use logical
	 */
	public MATLABArrayFlags(int x, MATLABArrayType type, boolean complex, boolean global, boolean logical) {
		super(MATLABDataType.miUINT32, 8);
		int flag = type.getID();
		flag |= complex?complexBit:0;
		flag |= global?globalBit:0;
		flag |= logical?logicalBit:0;
		flag1 = new Byte4(flag);
		flag2 = new Byte4(x);
	}
	
	@Override
	public int getByteNum() {
		return super.getByteNum() + 8;
	}

	@Override
	public void write(ByteBuffer dos) {
		super.write(dos);
		flag1.write(dos);
		flag2.write(dos);
	}
}
