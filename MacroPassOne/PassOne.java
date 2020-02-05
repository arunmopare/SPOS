//package com.kshitijpatil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PassOne {
	ArrayList<MNTEntry> MNT;
	ArrayList<String> MDT;
	ArrayList<String> PNTAB;
	HashMap<String, String> KPDTAB;
	
	public PassOne() {
		MNT = new ArrayList<MNTEntry>();
		MDT = new ArrayList<String>();
		PNTAB = new ArrayList<String>();
		KPDTAB = new HashMap<String, String>();
	}
	public MacroOutput getOutput(BufferedReader reader) throws IOException {
		String line;
		int mdtp = 0;
		int kpdtp = 0;
		int kp = 0;
		int pp = 0;
		StringBuilder icode = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			String parts[] = line.split("\\s+");
			
			// If macro definition encountered
			if (parts[0].equals("MACRO")) {
				PNTAB.clear();			// Separate PNTAB for each macro
				String prototype[] = reader.readLine().split("\\s+|,\\s+");
				MNTEntry entry = new MNTEntry(prototype[0], mdtp, kpdtp);
				// Processing the parameters
				for (int i = 1; i < prototype.length; i++) {
					if (prototype[i].contains("=")) {
						// keyword parameter						
						entry.incrKP();
						kpdtp++;
						String param[] = prototype[i].replace("&", "").split("=");
						KPDTAB.put(param[0], (param.length == 2) ? param[1] : "-");
						PNTAB.add(param[0]);
					} else {
						// Positional Parameter
						entry.incrPP();
						PNTAB.add(prototype[i].replace("&", ""));
					}
				}
				MNT.add(entry);
				
				
				// Processing macro instructions
				String instruction = reader.readLine();
				StringBuilder instrRow = new StringBuilder();
				int idx;
				while (!instruction.equals("MEND")) {
					String instr[] = instruction.split("\\s+|,\\s+");
					instrRow.append(mdtp).append(" ");
					instrRow.append(instr[0]).append("\t");
					instrRow.append(handleOperand(instr[1]));
					if (instr.length == 3)
						instrRow.append(",").append(handleOperand(instr[2]));
					instrRow.append("\n");
					mdtp++;
					instruction = reader.readLine();
				}
				MDT.add(instrRow.toString());
			} else {
				// TODO: CHECK #PP and #KP for macro call validation
				icode.append(line).append("\n");
			}
		}
		// Create output of Pass 1
		MacroOutput output = new MacroOutput();
		output.setIC(icode.toString());
		
		final StringBuilder mnt = new StringBuilder(); 
		MNT.forEach((entry) -> mnt.append(entry).append("\n"));
		output.setMNT(mnt.toString());
		
		final StringBuilder mdt = new StringBuilder();		
		MDT.forEach((entry) -> mdt.append(entry));
		output.setMDT(mdt.toString());
		
		final StringBuilder kpdtab = new StringBuilder();		
		KPDTAB.forEach((key,value) -> kpdtab.append(key).append("\t").append(value).append("\n"));
		output.setKPDTAB(kpdtab.toString());
		
		final StringBuilder pntab = new StringBuilder();		
		PNTAB.forEach((entry) -> pntab.append(entry).append("\n"));
		output.setPNTAB(pntab.toString());
		
		
		return output;
	}
	public String handleOperand(String operand) {
		int idx = PNTAB.indexOf(operand.replace("&", ""));
		String op;
		if (idx != -1)
			op = "(P," + (idx+1) + ")";
		else
			op = operand;
		return op;
	}
}
class MNTEntry {
	String name;
	int pp;
	int kp;
	int mdtp;
	int kpdtp;
	
	public MNTEntry(String name, int mdtp, int kpdtp) {
		this.name = name;
		this.pp = 0;
		this.kp = 0;
		this.mdtp = mdtp;
		this.kpdtp = kpdtp;
	}
	
	public void incrPP() {
		pp++;
	}
	
	public void incrKP() {
		kp++;
	}
	public MNTEntry(String name, int pp, int kp, int mdtp, int kpdtp) {
		this.name = name;
		this.pp = pp;
		this.kp = kp;
		this.mdtp = mdtp;
		this.kpdtp = kpdtp;
	}
	
	
	@Override
	public String toString() {
		return name + "  " + 
			   pp + "  " +
			   kp + "  " +
			   mdtp + "  " +
			   kpdtp;
	}
}
class MacroOutput {
	String IC;
	String MNT;
	String MDT;
	String KPDTAB;
	String PNTAB;
	public String getIC() {
		return IC;
	}
	public String getMNT() {
		return MNT;
	}
	public String getMDT() {
		return MDT;
	}
	public String getKPDTAB() {
		return KPDTAB;
	}
	public String getPNTAB() {
		return PNTAB;
	}
	public void setIC(String iC) {
		IC = iC;
	}
	public void setMNT(String mNT) {
		MNT = mNT;
	}
	public void setMDT(String mDT) {
		MDT = mDT;
	}
	public void setKPDTAB(String kPDTAB) {
		KPDTAB = kPDTAB;
	}
	public void setPNTAB(String pNTAB) {
		PNTAB = pNTAB;
	}
	
}
