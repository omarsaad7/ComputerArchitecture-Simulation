


public class Fetch {
	String instruction;
	public Fetch(int PC,InstructionMemory instructionMemory) {
		instruction=instructionMemory.getInstruction(PC);
	}

	

}
