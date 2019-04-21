
public class Control {
		Register RegWrite ;
		Register  MemToReg;
		Register  SkipEqual;
		Register  SkipNotEqual;
		Register  SkipLessThan;
		Register  jump;
		Register  jumpReturn;
		Register  jal;
		Register  MemRead;
		Register  MemWrite;
		Register  ALUOp;
		Register  ALUSrc;
	public Control() {
		// TODO Auto-generated constructor stub
		RegWrite=new Register("RegWrite", (1));
		MemToReg=new Register("MemToReg", (1));
		SkipEqual=new Register("SkipEqual", (1));
		SkipNotEqual=new Register("SkipNotEqual", (1));
		SkipLessThan=new Register("SkipLessThan", (1));
		jump=new Register("jump", (1));
		jumpReturn=new Register("jumpReturn", (1));
		jal=new Register("jumpAndLink", (1));
		MemRead=new Register("MemRead", (1));
		MemWrite=new Register("MemWrite", (1));
		ALUOp=new Register("ALUOp", (4));
		ALUSrc=new Register("ALUSrc", (1));
	}

}
