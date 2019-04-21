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
		InstructionMemory instructionMemory = new InstructionMemory(500);
		Control control = new Control();
		// Getting all instructions in Instruction Memory
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
		System.out.println("Instruction: "+instruction+" is fetched");		
			//add PC path here
		
		
		
		//Decode
		Decode registersBlock=new Decode(instruction, "",registers);
		String ReadData1 = registersBlock.ReadData[0];
		String ReadData2 = registersBlock.ReadData[1];
		System.out.println("Instruction: "+instruction+" is Decoded");
		System.out.println("ReadData1:"+ReadData1+" ReadData2:"+ReadData2);
//		System.out.println(registers);
//		System.out.println(fetch.instruction);
//		System.out.println(registers.getRegister(0).getValue());
		
		
		//Execute 
		Execute execute =new Execute(instruction, ReadData1, ReadData2, control);
		
		
		
		
	}

	public static String binaryto16(String input) {
		while (input.length() < 16)
			input = "0" + input;
		return input;
	}

}
