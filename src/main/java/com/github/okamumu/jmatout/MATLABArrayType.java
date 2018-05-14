package com.github.okamumu.jmatout;

/**
 * An enumerate class to represent the type of array.
 *
 */
public enum MATLABArrayType {
	mxCELL_CLASS(1),
	mxSTRUCT_CLASS(2),
	mxOBJECT_CLASS(3),
	mxCHAR_CLASS(4),
	mxSPARSE_CLASS(5),
	mxDOUBLE_CLASS(6),
	mxSINGLE_CLASS(7),
	mxINT8_CLASS(8),
	mxUINT8_CLASS(9),
	mxINT16_CLASS(10),
	mxUINT16_CLASS(11),
	mxINT32_CLASS(12),
	mxUINT32_CLASS(13),
	mxINT64_CLASS(14),
	mxUINT64_CLASS(15),
	;

	private final int id;

	/**
	 * Constructor the array type object according to the following table:
	 * <table>
	 * <tr><th>ID</th><th>Label</th><td>Type</th></tr>
	 * <tr><th>1</th><th>mxCELL_CLASS</th><td>...</th></tr>
	 * </table>
	 * @param id An integer to represent the array type.
	 */
	private MATLABArrayType(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
}
