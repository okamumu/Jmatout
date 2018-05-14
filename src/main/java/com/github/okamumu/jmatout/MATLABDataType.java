package com.github.okamumu.jmatout;

/**
 * Data type in MATLAB MAT
 *
 */
public enum MATLABDataType {
	miINT8(1),
	miUINT8(2),
	miINT16(3),
	miUINT16(4),
	miINT32(5),
	miUINT32(6),
	miSINGLE(7),
	miDOUBLE(9),
	miINT64(12),
	miUINT64(13),
	miMATRIX(14),
	miCOMPRESSED(15),
	miUTF16(16),
	miUTF32(17),
	miUTF64(18),
	;
	
	private final int id;
	
	/**
	 * Constructor the data type object according to the following table:
	 * <table>
	 * <tr><th>ID</th><th>Label</th><td>Type</th></tr>
	 * <tr><th>1</th><th>miINT8</th><td>char</th></tr>
	 * </table>
	 * @param id An integer to represent the data type.
	 */
	private MATLABDataType(int id) {
		this.id = id;
	}
	
	public final int getID() {
		return id;
	}
}

