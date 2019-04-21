import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//to add 2 to PC
//int newPC = Integer.parseInt(registers.getRegister(0).getValue(), 2) + 2;
//String NewPC = Integer.toBinaryString(newPC);
//registers.getRegister(0).setValue(binaryto16(NewPC));

public class Cycles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Registers registers = new Registers();
		InstructionMemory instructionMemory = new InstructionMemory(2500);
		Control control = new Control();
		
		//////////////////////////JUST TO TEST///////////////////
//		registers.registers[3].setValue("0000000000000011");
//		registers.registers[4].setValue("0000000000000011");
		
		/////////////////////////////////////////////////////////
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
		System.out
				.println("ReadData1:" + ReadData1 + " ReadData2:" + ReadData2);
		// System.out.println(registers);
		// System.out.println(fetch.instruction);
		// System.out.println(registers.getRegister(0).getValue());
		
		if (instruction.substring(0, 4).equals("0100")||instruction.substring(0, 4).equals("0101"))
			control.LS.setValue("1");
		
		// Execute
		String SignExtendInput = instruction.substring(8, 16);
		String SignExtendOutPut = SignExtend(SignExtendInput);
		if (instruction.substring(0, 4).equals("0111"))
			control.ALUSrc.setValue("1");
		String ALUSrc = control.ALUSrc.getValue();
		String ALUSrcMux = mux(ReadData2, SignExtendOutPut, ALUSrc);
		String SignExtendOutPut2 = SignExtend(instruction.substring(12, 16));
		String LSMux = mux(ReadData1,SignExtendOutPut2,control.LS.getValue());	
		Execute execute = new Execute(instruction, LSMux, ALUSrcMux,
				control);
		System.out.println("Instruction: " + instruction + " is Executed");
		System.out.println("ALU Result: " + execute.executeResult + " Zero: "
				+ execute.zero + " Lessthan: " + execute.lessThan);
		
		//Memory
		
		
		
		
//		System.out.println(registers.toString());
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
