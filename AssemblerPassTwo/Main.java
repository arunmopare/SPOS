//package com.kshitijpatil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		FileReader file;
		BufferedReader reader;
		PassTwo assembler = new PassTwo();
		
		try {
			reader = new BufferedReader(new FileReader("SYMTAB.txt"));
			assembler.setSymbolTable(reader);
			reader = new BufferedReader(new FileReader("LITTAB.txt"));
			assembler.setLiteralTable(reader);
			reader = new BufferedReader(new FileReader("IC.txt"));
			String machineCode = assembler.generateMachineCode(reader);
			System.out.println(machineCode);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
