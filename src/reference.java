//
//public class reference {
//	static String opcode;
//	static String function;
//	static String rsd;
//	static String rt;
//	static String type;
//	static String result;
//	static String immediate;
//	static String imm;
//	static String address;
//	static String immediateLS;
//	
//	public static void main(String[]args){
//		//R type
//	    System.out.println(Method("add #S0 #in0")); //done
//		System.out.println(Method("sub #S1 #S2")); //done
//		System.out.println(Method("Slt #in2 #S3")); //done
//		System.out.println(Method("and #in1 #in0")); //done
//		System.out.println(Method("or #S0 #S1"));//done
//		System.out.println(Method("nor #S0 #temp0")); //done
//		System.out.println(Method("Mult #in0 #S2"));//done
//		System.out.println(Method("Power #zero #S1")); //done
//		System.out.println(Method("SNE #zero #temp2")); //done
//		
//      //I type
//		System.out.println(Method("Addi #in0 3")); //done
//		
//	//J type
//		System.out.println(Method("Jump 10")); //done
//		System.out.println(Method("Jr #S1"));  //done
//		System.out.println(Method("Jr #in1")); //done
//		System.out.println(Method("Jal 15"));  //done
//		
//		//LS type
//		System.out.println(Method("LW #S0 0<#S2>")); //done
//		System.out.println(Method("SW #S2 4<#S0>")); //done
//
//
//	}
// public static String Method(String s){
//
//	String [] inst = s.split(" ");
//
//	switch(inst[0]){
//	case "add": opcode = "0000"; function="0011"; type = "R"; break; 
//	case "Seq":  opcode = "0000"; function="0001";type = "R";break;
//	case "Slt": opcode = "0000"; function="0010";type = "R";break;
//	case "and":  opcode = "0000"; function="0101";type = "R";break;
//	case "sub":  opcode = "0000"; function="0100";type = "R";break;
//	case "or":  opcode = "0000"; function="0110";type = "R";break;
//	case "Mult": opcode = "0000"; function="1000";type = "R";break;
//	case "Power": opcode = "0000"; function="1001";type = "R";break;
//	case "SNE":  opcode = "0000"; function="1010";type = "R";break;
//	case "nor": opcode = "0000"; function="0111";type = "R";break;
//	case "Addi": opcode = "0111"; type = "I"; immediate = inst[2]; break;
//	case "Jump": opcode = "0001";type = "J"; address = inst[1];break;
//	case "Jr": opcode = "0010";type = "Jr";break;
//	case "Jal": opcode = "0011";type = "J"; address = inst[1]; break;
//	case "LW": opcode= "0100";type = "LS";break;
//	case "SW": opcode = "0101";type = "LS"; break;
//	
//	}
//	// J type (EXCEPT Jr)
//			if (type.equals("J")) {
//				
//				int fromStrToInt = Integer.parseInt(address);
//				String AddressBin = Integer.toBinaryString(fromStrToInt);
//				while (AddressBin.length() < 12)
//					AddressBin = "0" + AddressBin;
//				
//				return opcode + AddressBin;
//			}
//			
//	//for all other types except J
//	switch(inst[1]){
//	case "#S0": rsd="1010";break;
//	case "#S1": rsd="1011";break;
//	case "#S2": rsd="1100";break;
//	case "#S3": rsd="1101";break;
//	case "#temp0": rsd="0011";break;
//	case "#temp1": rsd="0100";break;
//	case "#temp2": rsd="0101";break;
//	case "#in0": rsd="0110";break;
//	case "#in1": rsd="0111";break;
//	case "#in2": rsd="1000";break;
//	case "#zero": rsd="1001";break;
//	}
//	//Jr type
//	if(type.equals("Jr")){
//		int fromStrToIntJr = Integer.parseInt(rsd);	
//		while (rsd.length() < 12){
//			rsd = rsd + "0";
//		}
//		return opcode + rsd;
//	}
//	// I type
//			if (type.equals("I")) {
//
//				int fromStrToInt = Integer.parseInt(immediate);
//				String ImmediateBin = Integer.toBinaryString(fromStrToInt);
//				while (ImmediateBin.length() < 8)
//					ImmediateBin = "0" + ImmediateBin;
//				
//
//				return opcode + rsd + ImmediateBin;
//			}
//	//for R type
//	if(type.equals("R")){
//	switch(inst[2]){
//	case "#S0": rt="1010";break;
//	case "#S1": rt="1011";break;
//	case "#S2": rt="1100";break;
//	case "#S3": rt="1101";break;
//	case "#temp0": rt="0011";break;
//	case "#temp1":rt = "0100";break;
//	case "#temp2":rt = "0101";break;
//    case "#in0":rt = "0110";break;
//    case "#in1":rt = "0111";break;
//	case "#in2":rt = "1000";break;
//	case "#zero": rsd="1001";break;
//			}
//			return opcode + rsd + rt + function;
//		}
//
//
//			
//		// LS type
//		if (type.equals("LS")) {
//			String ss = inst[2];
//			ss= ss.substring(0, ss.length()-1);
//			String[] arr= ss.split("<");
//			
//			switch(arr[1]){
//			case "#S0": rt="1010";break;
//			case "#S1": rt="1011";break;
//			case "#S2": rt="1100";break;
//			case "#S3": rt="1101";break;
//			case "#temp0": rt="0011";break;
//			case "#temp1":rt = "0100";break;
//			case "#temp2":rt = "0101";break;
//		    case "#in0":rt = "0110";break;
//		    case "#in1":rt = "0111";break;
//			case "#in2":rt = "1000";break;
//			case "#zero": rt="1001";break;
//					}
//			String immed = arr[0];
//			
//		    int fromStrToInt = Integer.parseInt(immed);
//			String ImmediateBinary = Integer.toBinaryString(fromStrToInt);
//			while (ImmediateBinary.length() < 4)
//				ImmediateBinary = "0" + ImmediateBinary;
//				
//
//				return opcode + rsd + rt + ImmediateBinary;
//				
//			
//		}
//
//		
//
//		return result;
//	}
//
//}
