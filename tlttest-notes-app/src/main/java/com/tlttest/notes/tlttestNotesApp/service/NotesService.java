package com.tlttest.notes.tlttestNotesApp.service;

import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import com.tlttest.notes.tlttestNotesApp.exception.NotesException;
import com.tlttest.notes.tlttestNotesApp.model.NotesDTO;

public interface NotesService {

	public void createNotes(NotesDTO notes) throws ConstraintViolationException ,NotesException;

	public Map<String, Integer> fetchStatsById(String id);

	public Map<String, Map<String, Integer>> fetchCompleteStats();
	
	public List<NotesDTO> getAllNotes(); 	
	
	public NotesDTO getSingleNotes(String id) throws NotesException; 
	
	public void updateNotes(String id,NotesDTO notesDto) throws NotesException;
	
	public void deleteNotes(String id) throws NotesException;
	
	public List<NotesDTO> getSortedNotesByDate(Integer pageSize) throws NotesException;
	
	public List<NotesDTO> findByNotesTag(String tags) throws NotesException;
	
}
