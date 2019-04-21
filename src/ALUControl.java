public class ALUControl {
	String ALUControlOutput;

	public ALUControl(String OpCode, String ALUOp) {
		// TODO Auto-generated constructor stub
		if (OpCode.equals("0000")) {
			switch (ALUOp) {
			case "0011":
				ALUControlOutput = "0011";
				break;// add
			case "0100":// subtract
			case "0010":// subtract
			case "1010":// subtract
			case "0001":
				ALUControlOutput = "0100";
				break;// subtract
			case "0101":
				ALUControlOutput = "0101";
				break;// and
			case "0110":
				ALUControlOutput = "0110";
				break;// or
			case "0111":
				ALUControlOutput = "0111";
				break;// nor
			case "1000":
				ALUControlOutput = "1000";
				break;// mult
			case "1001":
				ALUControlOutput = "1001";
				break;// power
			default:
				ALUControlOutput = "0000";
			}
		}
		if (OpCode.equals("0111")||OpCode.equals("0100")||OpCode.equals("0101")) {
			ALUControlOutput = "0011";
		}
	}

}
