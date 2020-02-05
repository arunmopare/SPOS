//package com.kshitijpatil;

public class OperandEntry {
	private String literal;
	private int address;
	private int index;

	public OperandEntry(String literal, int address) {
		this.literal = literal;
		this.address = address;
		index = 0;
	}

	public OperandEntry(String literal, int address, int index) {
		this.literal = literal;
		this.address = address;
		this.index = index;
	}

	public String getLiteral() {
		return literal;
	}

	public int getAddress() {
		return address;
	}

	public int getIndex() {
		return index;
	}

	public OperandEntry setAddress(int address) {
		this.address = address;
		return this;
	}

	public OperandEntry setIndex(int index) {
		this.index = index;
		return this;
	}

	@Override
	public String toString() {
		if (literal.contains("="))
			return "(L," + index + ")";
		else
			return "(S," + index + ")";
	}
}
