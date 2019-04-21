public class ALU  {
	boolean zero;
	boolean lessthan;
	String ALUResult="0000000000000000";

	public ALU(String ReadData1, String ReadData2, String ALUControl) {
		// TODO Auto-generated constructor stub
		this.zero = false;
		this.lessthan = false;
		switch (ALUControl) {
		case "0011":
			ALUResult=add(ReadData1,ReadData2);
			break;// add
		case "0100":
			ALUResult=subtract(ReadData1,ReadData2);
			break;// subtract
		case "0101":
			ALUResult=and(ReadData1,ReadData2);
			break;// and
		case "0110":
			ALUResult=or(ReadData1,ReadData2);
			break;// or
		case "0111":
			ALUResult=nor(ReadData1,ReadData2);
			break;// nor
		case "1000":
			ALUResult=mult(ReadData1,ReadData2);
			break;// mult
		case "1001":
			ALUResult=power(ReadData1,ReadData2);
			break;//power
		}
	}

	

	public String add(String ReadData1, String ReadData2) {
		int data1 = Integer.parseInt(ReadData1, 2);
		int data2 = Integer.parseInt(ReadData2, 2);
		int result = data1 + data2;
		String res = Integer.toBinaryString(result);
		res = binaryto16(res);
		return res;
	}

	public String subtract(String ReadData1, String ReadData2) {
		int data1 = Integer.parseInt(ReadData1, 2);
		int data2 = Integer.parseInt(ReadData2, 2);
		int result = data1 - data2;
		if(result==0)
			zero=true;
		if(data1<data2)
			lessthan=true;
		String res = Integer.toBinaryString(result);
		res = binaryto16(res);
		return res;
	}

	public String mult(String ReadData1, String ReadData2) {
		int data1 = Integer.parseInt(ReadData1, 2);
		int data2 = Integer.parseInt(ReadData2, 2);
		int result = data1 * data2;
		String res = Integer.toBinaryString(result);
		res = binaryto16(res);
		return res;
	}

	public String power(String ReadData1, String ReadData2) {
		int data1 = Integer.parseInt(ReadData1, 2);
		int data2 = Integer.parseInt(ReadData2, 2);
		int result = (int) Math.pow(data1, data2);
		String res = Integer.toBinaryString(result);
		res = binaryto16(res);
		return res;
	}

	public String and(String ReadData1, String ReadData2) {
		boolean[] data1 = new boolean[ReadData1.length()];
		boolean[] data2 = new boolean[ReadData2.length()];
		for (int i = 0; i < ReadData1.length(); i++) {
			if (ReadData1.charAt(i) == '0')
				data1[i] = false;
			else
				data1[i] = true;
			if (ReadData2.charAt(i) == '0')
				data2[i] = false;
			else
				data2[i] = true;
		}
		String result = "";
		for (int i = 0; i < data1.length; i++)
			if (data1[i] && data2[i])
				result = result + "1";
			else
				result = result + "0";
		result = binaryto16(result);
		return result;

	}

	public String or(String ReadData1, String ReadData2) {
		boolean[] data1 = new boolean[ReadData1.length()];
		boolean[] data2 = new boolean[ReadData2.length()];
		for (int i = 0; i < ReadData1.length(); i++) {
			if (ReadData1.charAt(i) == '0')
				data1[i] = false;
			else
				data1[i] = true;
			if (ReadData2.charAt(i) == '0')
				data2[i] = false;
			else
				data2[i] = true;
		}
		String result = "";
		for (int i = 0; i < data1.length; i++)
			if (data1[i] || data2[i])
				result = result + "1";
			else
				result = result + "0";
		result = binaryto16(result);
		return result;
	}

	public String nor(String ReadData1, String ReadData2) {
		String or = or(ReadData1, ReadData2);
		String result = "";
		for (int i = 0; i < or.length(); i++) {
			if (or.charAt(i) == '0')
				result = result + "1";
			else
				result = result + "0";
		}
		return result;
	}

	public static String binaryto16(String input) {
		while (input.length() < 16)
			input = "0" + input;
		if (input.length() > 16)
			input = input.substring(0, 15);
		return input;
	}

}
