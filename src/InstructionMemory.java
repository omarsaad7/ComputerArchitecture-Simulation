public class InstructionMemory {

	String[] instructions;
	int numberOfInstructions;

	public InstructionMemory(int size) {
		instructions = new String[size];
	}

	public String getInstruction(int index) {
		return instructions[index / 2];
//		return Integer.toBinaryString(instructions[index / 2]);

	}

	public void setInstruction(int index, String value) {
		instructions[index / 2] = value;
//		instructions[numberOfInstructions]=value;
		numberOfInstructions++;
	}

	
	public int getNumberOfInstructions() {
		return numberOfInstructions;
	}

	public String toString() {
		String result = "";
		for(int i=0;i<numberOfInstructions;i++){
			String r = (instructions[i]);
			while(r.length() < 16)
				r = "0" + r;
			result=result+r+'\n';
		}
		return result;
	}

}
