import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;

public class GUI extends JFrame implements ActionListener {
	JTextArea t;
	JButton b;
	JLabel l;
	JPanel p;
	JLabel backgrnd;
	String[] text;
	int line;
	ArrayList<String> binaryInstructions;
	JLabel result ;
	public GUI() {
		setTitle("Computer-Architecture");
		Image icon = Toolkit.getDefaultToolkit().getImage("src/icon.jpg");
		setIconImage(icon);
		b = new JButton("Run");
		b.setActionCommand("submit");
		b.addActionListener(this);
		b.setPreferredSize(new Dimension(100,30));
		l = new JLabel();
		p = new JPanel();
		binaryInstructions= new ArrayList<String>();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		p.add(b);
		result = new JLabel();
		result.setForeground(Color.WHITE);
		
		// p.add(l);

		// backgrnd = new JLabel(new ImageIcon("src/caa.jpg"));

		t = new JTextArea(10, 16);
		t.setPreferredSize(new Dimension(100, 200));
		// backgrnd.add(t);

		ImageIcon A2 = new ImageIcon("src/caimage.jpg");
		Icon s1 = resizeIcon(A2, 500, 550);
		setContentPane(new JLabel(s1));
		setLayout(new FlowLayout());

		setSize(500, 550);
		add(l);
		add(t);
		add(p);
		add (result);
		
		setLocationRelativeTo(null);

		setVisible(true);

		// backgrnd.setIcon(s1);

	}

	public static void main(String[] args) {

		GUI gui = new GUI();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("submit")) {

//			System.out.println("line count:" + t.getLineCount());
			text = new String[line];
			for (int i = 0; i < t.getLineCount(); i++) {
				text = t.getText().split("\\n");

				// if(text[i].length()!=16){
				// JOptionPane.showMessageDialog(this,
				// "invalid entry, must be 16 bits");
				// }
			}

			String s = "";
			for (int i = 0; i < t.getLineCount(); i++) {
				s = s + text[i];
//				System.out.println(text[i]);
				binaryInstructions.add(stringInstruction(text[i]));
			}
//			System.out.println(binaryInstructions.toString());
			Registers registers = new Registers();
			InstructionMemory instructionMemory = new InstructionMemory(2500);
			Control control = new Control();
			int index=0;
			for(int i=0;i<binaryInstructions.size();i++){
				instructionMemory.setInstruction(index, binaryInstructions.get(i));
				index += 2;
			}
			Stages.run(instructionMemory, control, registers);	
			result.setText(registers.showonGUI());
			
			
			
			t.setText("  ");
			binaryInstructions.clear();
		}
	}
//	Addi #temp0 2
//	Addi #temp1 3
//	add #temp0 #temp1
	private static Icon resizeIcon(ImageIcon icon, int resizedWidth,
			int resizedHeight) {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,
				java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	public static String stringInstruction(String s) {
		String opcode = "";
		String function = "";
		String rsd="";
		String rt="";
		String type="";
		String result="";
		String immediate="";
		String imm="";
		String address="";
		String immediateLS="";

		String[] inst = s.split(" ");

		switch (inst[0]) {
		case "add":
			opcode = "0000";
			function = "0011";
			type = "R";
			break;
		case "Seq":
			opcode = "0000";
			function = "0001";
			type = "R";
			break;
		case "Slt":
			opcode = "0000";
			function = "0010";
			type = "R";
			break;
		case "and":
			opcode = "0000";
			function = "0101";
			type = "R";
			break;
		case "sub":
			opcode = "0000";
			function = "0100";
			type = "R";
			break;
		case "or":
			opcode = "0000";
			function = "0110";
			type = "R";
			break;
		case "Mult":
			opcode = "0000";
			function = "1000";
			type = "R";
			break;
		case "Power":
			opcode = "0000";
			function = "1001";
			type = "R";
			break;
		case "SNE":
			opcode = "0000";
			function = "1010";
			type = "R";
			break;
		case "nor":
			opcode = "0000";
			function = "0111";
			type = "R";
			break;
		case "Addi":
			opcode = "0111";
			type = "I";
			immediate = inst[2];
			break;
		case "Jump":
			opcode = "0001";
			type = "J";
			address = inst[1];
			break;
		case "Jr":
			opcode = "0010";
			type = "Jr";
			break;
		case "Jal":
			opcode = "0011";
			type = "J";
			address = inst[1];
			break;
		case "LW":
			opcode = "0100";
			type = "LS";
			break;
		case "SW":
			opcode = "0101";
			type = "LS";
			break;

		}
		// J type (EXCEPT Jr)
		if (type.equals("J")) {

			int fromStrToInt = Integer.parseInt(address);
			String AddressBin = Integer.toBinaryString(fromStrToInt);
			while (AddressBin.length() < 12)
				AddressBin = "0" + AddressBin;

			return opcode + AddressBin;
		}

		// for all other types except J
		switch (inst[1]) {
		case "#S0":
			rsd = "1010";
			break;
		case "#S1":
			rsd = "1011";
			break;
		case "#S2":
			rsd = "1100";
			break;
		case "#S3":
			rsd = "1101";
			break;
		case "#temp0":
			rsd = "0011";
			break;
		case "#temp1":
			rsd = "0100";
			break;
		case "#temp2":
			rsd = "0101";
			break;
		case "#in0":
			rsd = "0110";
			break;
		case "#in1":
			rsd = "0111";
			break;
		case "#in2":
			rsd = "1000";
			break;
		case "#zero":
			rsd = "1001";
			break;
		}
		// Jr type
		if (type.equals("Jr")) {
			int fromStrToIntJr = Integer.parseInt(rsd);
			while (rsd.length() < 12) {
				rsd = rsd + "0";
			}
			return opcode + rsd;
		}
		// I type
		if (type.equals("I")) {

			int fromStrToInt = Integer.parseInt(immediate);
			String ImmediateBin = Integer.toBinaryString(fromStrToInt);
			while (ImmediateBin.length() < 8)
				ImmediateBin = "0" + ImmediateBin;

			return opcode + rsd + ImmediateBin;
		}
		// for R type
		if (type.equals("R")) {
			switch (inst[2]) {
			case "#S0":
				rt = "1010";
				break;
			case "#S1":
				rt = "1011";
				break;
			case "#S2":
				rt = "1100";
				break;
			case "#S3":
				rt = "1101";
				break;
			case "#temp0":
				rt = "0011";
				break;
			case "#temp1":
				rt = "0100";
				break;
			case "#temp2":
				rt = "0101";
				break;
			case "#in0":
				rt = "0110";
				break;
			case "#in1":
				rt = "0111";
				break;
			case "#in2":
				rt = "1000";
				break;
			case "#zero":
				rsd = "1001";
				break;
			}
			return opcode + rsd + rt + function;
		}

		// LS type
		if (type.equals("LS")) {
			String ss = inst[2];
			ss = ss.substring(0, ss.length() - 1);
			String[] arr = ss.split("<");

			switch (arr[1]) {
			case "#S0":
				rt = "1010";
				break;
			case "#S1":
				rt = "1011";
				break;
			case "#S2":
				rt = "1100";
				break;
			case "#S3":
				rt = "1101";
				break;
			case "#temp0":
				rt = "0011";
				break;
			case "#temp1":
				rt = "0100";
				break;
			case "#temp2":
				rt = "0101";
				break;
			case "#in0":
				rt = "0110";
				break;
			case "#in1":
				rt = "0111";
				break;
			case "#in2":
				rt = "1000";
				break;
			case "#zero":
				rt = "1001";
				break;
			}
			String immed = arr[0];

			int fromStrToInt = Integer.parseInt(immed);
			String ImmediateBinary = Integer.toBinaryString(fromStrToInt);
			while (ImmediateBinary.length() < 4)
				ImmediateBinary = "0" + ImmediateBinary;

			return opcode + rsd + rt + ImmediateBinary;

		}

		return result;
	}

}
