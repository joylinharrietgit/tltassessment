package com.tlttest.notes.tlttestNotesApp.enums;

public enum Tags {
	BUSINESS ("BUSINESS"),
	PERSONAL ("PERSONAL") ,
	IMPORTANT ("IMPORTANT");
	
	private String tags;
	
	private Tags(String tags) {
		this.tags = tags;
	}
	public String getTag() {
		return this.tags;
	}
}
