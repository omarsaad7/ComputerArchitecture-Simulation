public class WriteBack {

	public WriteBack(Control control, String ReadData, String ALUResult,
			String pc, String WriteRegister, Registers registers) {
		// TODO Auto-generated constructor stub
		String MemtoRegMux = Cycles.mux(ALUResult, ReadData,
				control.MemToReg.getValue());
		String WriteData = Cycles.mux(MemtoRegMux, pc, control.jal.getValue());
		int reg = Integer.parseInt(WriteRegister, 2);
		if (control.RegWrite.equals("1"))
			registers.writeRegister(reg, WriteData);
	}

}
