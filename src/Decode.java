
public class Decode {
	String[] ReadData;
//	Registers registers;
	public Decode(String instruction,String WriteData,Registers registers) {
		// TODO Auto-generated constructor stub
		ReadData = new String[2];
		String ReadRegister1 = instruction.substring(4, 8);
		String ReadRegister2 = instruction.substring(8, 12);
		String WriteRegister = instruction.substring(8,12);
		int index1=Integer.parseInt(ReadRegister1,2);
		int index2=Integer.parseInt(ReadRegister2,2);
//		System.out.println(ReadRegister1+" "+ReadRegister2);
		ReadData[0]=registers.getRegister(index1).getValue();
		ReadData[1]=registers.getRegister(index2).getValue();
	}
}
