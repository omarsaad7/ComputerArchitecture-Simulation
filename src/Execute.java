
public class Execute {
	boolean zero;
	boolean lessThan;
	String executeResult="0000000000000000";
	public Execute(String instruction,String ReadData1,String ReadData2,Control control) {
		//OpCode,String ALUOp
		String OpCode = instruction.substring(0, 4);
		String ALUOp = instruction.substring(12, 16);
		ALUControl alucontrol = new ALUControl(OpCode, ALUOp);
		String ALUControlOutput = alucontrol.ALUControlOutput;
		ALU alu = new ALU(ReadData1,ReadData2,ALUControlOutput);	
		zero=alu.zero;
		lessThan=alu.lessthan;
		executeResult=alu.ALUResult;
		setControl(OpCode,ALUOp,control);
		
		
	}
	
	public void setControl(String OpCode,String ALUOp,Control control){
		if(ALUOp.equals("1010"))
			control.SkipNotEqual.setValue("1");
		
		if(ALUOp.equals("0001"))
			control.SkipEqual.setValue("1");
		
		if(ALUOp.equals("0010"))
			control.SkipLessThan.setValue("1");
		
		if(OpCode.equals("0001"))
			control.jump.setValue("1");
		if(OpCode.equals("0010"))
			control.jumpReturn.setValue("1");
		if(OpCode.equals("0011"))
			control.jal.setValue("1");
		if(OpCode.equals("0100"))
			control.MemRead.setValue("1");
		if(OpCode.equals("0101"))
			control.MemWrite.setValue("1");
		if (OpCode.equals("0100") || OpCode.equals("0111")
				|| OpCode.equals("0011")) 
			control.RegWrite.setValue("1");
		if(OpCode.equals("0100"))
			control.MemToReg.setValue("1");
		if (OpCode.equals("0000")) {
			switch (ALUOp) {
			case "0011":
			case "0100":
			case "0101":
			case "0110":
			case "0111":
			case "1000":
			case "1001":
				control.RegWrite.setValue("1");
			}
		}
	}

}
