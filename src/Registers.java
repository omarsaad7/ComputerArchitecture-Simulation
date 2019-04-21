public class Registers {

	Register[] registers;

	/**
	 * Constructs a new 16-register file
	 */
	public Registers() {
		registers = new Register[16];
		registers[0] = new Register("#PC", 16);
		registers[1] = new Register("#out0", 16);
		registers[2] = new Register("#out1", 16);
		registers[3] = new Register("#temp0", 16);
		registers[4] = new Register("#temp1", 16);
		registers[5] = new Register("#temp2", 16);
		registers[6] = new Register("#in0", 16);
		registers[7] = new Register("#in1", 16);
		registers[8] = new Register("#in2", 16);
		registers[9] = new Register("#zero", 16);
		registers[10] = new Register("#S0", 16);
		registers[11] = new Register("#S1", 16);
		registers[12] = new Register("#S2", 16);
		registers[13] = new Register("#S3", 16);
		registers[14] = new Register("#ra", 16);
		registers[15] = new Register("#Sp", 16);
	}

	/**
	 * Read the register at the specified index
	 * 
	 * @param index
	 *            the index of the register to be read
	 * @return the register at the given index
	 */
	public Register getRegister(int index) {
		return registers[index];
	}

	/**
	 * Write the passed value to the register at the specified index
	 * 
	 * @param index
	 *            the index of the register to write at
	 * @param value
	 *            the value to be written
	 */
	public void writeRegister(int index, String value) {
		registers[index].setValue(value);
	}

	/**
	 * Returns a string representation of the register file
	 */
	public String toString() {
		String r = "";
		for (int i = 0; i < 16; ++i)
			r += registers[i].toString() + '\n';
		return r;
	}

}