import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//to add 2 to PC
//int newPC = Integer.parseInt(registers.getRegister(0).getValue(), 2) + 2;
//String NewPC = Integer.toBinaryString(newPC);
//registers.getRegister(0).setValue(binaryto16(NewPC));

public class Cycles {
	static String pcc;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Registers registers = new Registers();
		InstructionMemory instructionMemory = new InstructionMemory(2500);
		Control control = new Control();

		// ////////////////////////JUST TO TEST///////////////////
		 registers.registers[3].setValue("0000000000001100");
		 registers.registers[4].setValue("0000000000001000");

		// ///////////////////////////////////////////////////////
		// Getting all instructions from txt file in Instruction Memory
		BufferedReader reader;
		try {

			reader = new BufferedReader(new FileReader("src/instructions.txt"));
			String line = reader.readLine();
			int index = Integer
					.parseInt(registers.getRegister(0).getValue(), 2);
			while (line != null) {
				instructionMemory.setInstruction(index, line);
				index += 2;
				// read next line
				line = reader.readLine();
			}
			// System.out.println(instructionMemory);
			// System.out.println(registers.getRegister(0).getValue());
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Fetch
		Fetch fetch = new Fetch(Integer.parseInt(registers.getRegister(0)
				.getValue(), 2), instructionMemory);
		String instruction = fetch.instruction;
		System.out.println("Instruction: " + instruction + " is fetched");
		// add PC path here

		// Decode
		Decode registersBlock = new Decode(instruction, "", registers);
		String ReadData1 = registersBlock.ReadData[0];
		String ReadData2 = registersBlock.ReadData[1];
		System.out.println("Instruction: " + instruction + " is Decoded");
		// System.out.println(registers);
		// System.out.println(fetch.instruction);
		// System.out.println(registers.getRegister(0).getValue());
		if (instruction.substring(0, 4).equals("0100")
				|| instruction.substring(0, 4).equals("0101"))
			control.LS.setValue("1");

		// Execute
		String SignExtendInput = instruction.substring(8, 16);
		String SignExtendOutPut = SignExtend(SignExtendInput);
		if (instruction.substring(0, 4).equals("0111"))
			control.ALUSrc.setValue("1");
		String ALUSrc = control.ALUSrc.getValue();
		String ALUSrcMux = mux(ReadData2, SignExtendOutPut, ALUSrc);
		String SignExtendOutPut2 = SignExtend(instruction.substring(12, 16));
		String LSMux = mux(ReadData1, SignExtendOutPut2, control.LS.getValue());
		Execute execute = new Execute(instruction, LSMux, ALUSrcMux, control);
		System.out.println("Instruction: " + instruction + " is Executed");
		System.out.println("ALU Result: " + execute.executeResult + " Zero: "
				+ execute.zero + " Lessthan: " + execute.lessThan);

		// Setting PC
		String pc = pc(registers, control, execute.zero, execute.lessThan,
				instruction, ReadData1);
		registers.registers[0].setValue(pc);

		// Memory
		Memory memory = new Memory(execute.executeResult, ReadData2, control);
		System.out.println("Instruction: " + instruction + " is Memorized");
		
		
		if(instruction.substring(0, 4).equals("0100"))
			control.MemToReg.setValue("1");
			
		
		
		//WriteBack
		String WriteRegister=instruction.substring(4, 8);
		WriteBack writeBack = new WriteBack(control, memory.readData, execute.executeResult,pcc , WriteRegister, registers);
		System.out.println("Instruction: " + instruction + " is WrittenBack");
		//print instruction and registers info after finishing its 5 stages 
		 System.out.println(registers);
	}

	public static String pc(Registers registers, Control control, boolean zero,
			boolean lessthan, String instruction, String ReadData1) {
		int pc = Integer.parseInt(registers.getRegister(0).getValue(), 2);
		pc = pc + 2;
		int pc2 = pc + 2;
		pcc = Integer.toBinaryString(pc);
		String pcc2 = Integer.toBinaryString(pc2);
		String skipControl = skipcontrol(control, zero, lessthan);
		String skipMux = mux(pcc, pcc2, skipControl);
		String ins = instruction.substring(0, 12);
		int inst = Integer.parseInt(ins, 2);
		inst = inst * 2;
		String instr = Integer.toBinaryString(inst);
		while (instr.length() < 13)
			instr = "0" + instr;
		while (pcc.length() < 16)
			pcc = "0" + pcc;
		instr = pcc.substring(13, 16) + instr;
		String jumpmux = mux(skipMux, instr, control.jump.getValue());
		String jumpreturnmux = mux(jumpmux, ReadData1,
				control.jumpReturn.getValue());
		System.out.println(ReadData1 );
		while (jumpreturnmux.length() < 16)
			jumpreturnmux = "0" + jumpreturnmux;
		return jumpreturnmux;

	}

	public static String skipcontrol(Control control, boolean zero,
			boolean lessthan) {
		String skipequal = control.SkipEqual.getValue();
		String skipNotequal = control.SkipNotEqual.getValue();
		String skiplessthan = control.SkipLessThan.getValue();
		boolean notzero = !zero;
		String output = "0";
		if (zero && skipequal.equals("1"))
			output = "1";
		if (notzero && skipNotequal.equals("1"))
			output = "1";
		if (lessthan && skiplessthan.equals("1"))
			output = "1";
		
		return output;
	}

	public static String binaryto16(String input) {
		while (input.length() < 16)
			input = "0" + input;
		return input;
	}

	public static String SignExtend(String input) {
		if (input.charAt(0) == '0') {
			while (input.length() < 16)
				input = "0" + input;
		} else {
			while (input.length() < 16)
				input = "1" + input;
		}
		return input;
	}

	public static String mux(String in0, String in1, String selection) {

		if (selection.equals("0")) {
			return in0;
		} else
			return in1;

	}

}
