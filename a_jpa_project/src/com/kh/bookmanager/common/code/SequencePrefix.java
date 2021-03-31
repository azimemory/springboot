package com.kh.bookmanager.common.code;

public enum SequencePrefix {
	
	SC_BK_IDX("BK"),
	SC_BOARD_IDX("BD"),
	SC_FILE_IDX("FL");
	
	public String prefix;
	private SequencePrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String toString() {
		return prefix;
	}
}
