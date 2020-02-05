//package com.kshitijpatil;

import java.util.HashMap;
public class OpcodeTable {
	private final HashMap<String, OpcodeEntry> OPTAB; 

	public OpcodeTable() {
		OPTAB = new HashMap<>();
		OPTAB.put("STOP", new OpcodeEntry("IS", 0));
		OPTAB.put("ADD", new OpcodeEntry("IS", 1));
		OPTAB.put("SUB", new OpcodeEntry("IS", 2));
		OPTAB.put("MULT", new OpcodeEntry("IS", 3));
		OPTAB.put("MOVER", new OpcodeEntry("IS", 4));
		OPTAB.put("MOVEM", new OpcodeEntry("IS", 5));
		OPTAB.put("COMP", new OpcodeEntry("IS", 6));
		OPTAB.put("BC", new OpcodeEntry("IS", 7));
		OPTAB.put("DIV", new OpcodeEntry("IS", 8));
		OPTAB.put("READ", new OpcodeEntry("IS", 9));
		OPTAB.put("PRINT", new OpcodeEntry("IS", 10));
		OPTAB.put("START", new OpcodeEntry("AD", 1));
		OPTAB.put("END", new OpcodeEntry("AD", 2));
		OPTAB.put("ORIGIN", new OpcodeEntry("AD", 3));
		OPTAB.put("EQU", new OpcodeEntry("AD", 4));
		OPTAB.put("LTORG", new OpcodeEntry("AD", 5));
		OPTAB.put("DS", new OpcodeEntry("DL", 1));
		OPTAB.put("DC", new OpcodeEntry("DL", 2));
		OPTAB.put("AREG", new OpcodeEntry("RG", 1));
		OPTAB.put("BREG", new OpcodeEntry("RG", 2));
		OPTAB.put("CREG", new OpcodeEntry("RG", 3));
		OPTAB.put("EQ", new OpcodeEntry("CC", 1));
		OPTAB.put("LT", new OpcodeEntry("CC", 2));
		OPTAB.put("GT", new OpcodeEntry("CC", 3));
		OPTAB.put("LE", new OpcodeEntry("CC", 4));
		OPTAB.put("GE", new OpcodeEntry("CC", 5));
		OPTAB.put("NE", new OpcodeEntry("CC", 6));
		OPTAB.put("ANY", new OpcodeEntry("CC", 7));
	}	
	public boolean containsKey(String key) {
		return OPTAB.containsKey(key);
	}
	public OpcodeEntry get(String key) {
		return OPTAB.get(key);
	}
}

class OpcodeEntry {
	String className;
	int opcode;
	
	public OpcodeEntry(String className, int opcode) {
		this.className = className;
		this.opcode = opcode;
	}
	
	public String getClassName() {
		return className;
	}
	
	public int getOpcode() {
		return opcode;
	}
	
	public boolean isDeclarative() {
		return className.equals("DL");
	}
	
	public boolean isAssemblerDirective() {
		return className.equals("AD");
	}
	
	public boolean isImperative() {
		return className.equals("IS");
	}
	
	@Override
	public String toString() {
		if(className.equals("RG")) {
			return "(" + opcode + ")";
		}
		return "(" + className + "," + String.format("%02d", opcode) + ")";
	}
}
