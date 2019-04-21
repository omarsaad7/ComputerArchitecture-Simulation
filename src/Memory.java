import java.util.HashMap;


public class Memory {
	String [] memory ;
	String readData="";
	
	public Memory(String Address,String WriteData,Control control) {
		// TODO Auto-generated constructor stub
		memory = new String[16392];
		initiatememory();
		int addres = Integer.parseInt(Address, 2);
		if(control.MemRead.equals("1"))
			readData=readData(addres);
		if(control.MemWrite.equals("1"))
			writeData(addres, WriteData);
	}
	
	
	
	public String readData(int address)
	{
		return memory[address/2];
	}
	
	public void writeData(int address, String value)
	{
		memory[address/2] = value;
	}
	
	public void initiatememory(){
		for(int i=0; i <memory.length;i++){
			memory[i]="0000000000000000";
		}

	}

}
