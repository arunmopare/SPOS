//package com.kshitijpatil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class PassTwo {
	ArrayList<OperandEntry> SYMTAB;
	ArrayList<OperandEntry> LITTAB;
	StringBuilder code;
	
	private static final String IMPERATIVE = "IS";
	private static final String DECLARATIVE = "DL";
	private static final String SYMBOL = "S";
	private static final String LITERAL = "L";
	private static final String CONSTANT = "C";
	private static final String ASSM_DIRECTIVE = "AD";
	
	
	public PassTwo() {
		SYMTAB = new ArrayList<>();
		LITTAB = new ArrayList<>();
		code = new StringBuilder();
	}
	
	public void setSymbolTable(BufferedReader reader) throws NumberFormatException, IOException {
		assignTo(reader, SYMTAB);
	}
	
	public void setLiteralTable(BufferedReader reader) throws NumberFormatException, IOException {
		assignTo(reader, LITTAB);
	}

	private void assignTo(BufferedReader reader, ArrayList<OperandEntry> table) throws NumberFormatException, IOException {
		String line;
		while((line = reader.readLine()) != null) {
			String parts[] = line.split("\\s+");
			
			table.add(new OperandEntry(parts[1], 
								Integer.parseInt(parts[2]),
								Integer.parseInt(parts[0])));
		}
	}
	
	public String generateMachineCode(BufferedReader reader) throws IOException {
		String line;
		while ((line = reader.readLine()) != null) {
			String parts[] = line.split("\\s+");
			
			// Assembler directive, no output in pass 2
			if (parts[1].contains(ASSM_DIRECTIVE)) {
				code.append("\n");
				continue;
			}
			
			// Imperative statement
			if (parts[1].contains(IMPERATIVE)) {
				code.append(parts[0]).append("  ");				// Location counter
				int opcode = Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
				code.append("+" + String.format("%02d", opcode)).append("  ");			// Opcode
				
				switch (parts.length) {
					case 3:
						// handle operand-1 as operand-2
						code.append("00").append("  ");		// empty operand-1
						handleOperandTwo(parts[2]);
						break;
					case 4:
						// Normal Imperative statement
						int regNo = Integer.parseInt(parts[2].replaceAll("[^0-9]", ""));
						code.append(String.format("%02d", regNo)).append("  ");	// register
						handleOperandTwo(parts[3]);
						break;
					case 2:
						// STOP statement
						code.append("00").append("  ").append("000").append("\n");
				}
			}
			
			// Declarative statement
			if (parts[1].contains(DECLARATIVE)) {
				code.append(parts[0]).append("  ");				// Location counter
				code.append("00").append("  ").append("00").append("  ");
				handleOperandTwo(parts[2]);
			}
		}
		
		
		return code.toString();
	}

	private void handleOperandTwo(String operand) {
		int value = Integer.parseInt(operand.replaceAll("[^0-9]", ""));
		String output = "000";
		if (operand.contains(SYMBOL)) {
			output = String.format("%03d", SYMTAB.get(value).getAddress());
		} else if (operand.contains(LITERAL)) {
			output = String.format("%03d", LITTAB.get(value).getAddress());
		} else if (operand.contains(CONSTANT)) {
			output = String.format("%03d", value);
		}
		code.append(output).append("\n");
	}
}
