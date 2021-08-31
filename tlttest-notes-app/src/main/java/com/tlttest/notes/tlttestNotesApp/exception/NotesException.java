package com.tlttest.notes.tlttestNotesApp.exception;

public class NotesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotesException(String message) {
		super(message);
	}
	public static String NotFoundException(String id) {
		return "Notes with id "+ id + "not found";
	}
	public static String NotesAlreadyExists() {
		return "Notes with given title already exists";
	}
	public static String InvalidTagException(String tag) {
		return "InvalidTagException for tag "+tag;
	}
}
