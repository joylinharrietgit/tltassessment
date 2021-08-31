package com.tlttest.notes.tlttestNotesApp.resource;

import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tlttest.notes.tlttestNotesApp.exception.NotesException;
import com.tlttest.notes.tlttestNotesApp.model.NotesDTO;
import com.tlttest.notes.tlttestNotesApp.service.NotesService;


@RestController
public class NotesController {

	@Autowired
	private NotesService notesService;

	/**
	 * Creates the notes based on the input given
	 * @param notes
	 * @return 
	 */
	@PostMapping("/notes")
	public ResponseEntity<?> createTodo(@RequestBody NotesDTO notes){
		try {
			
			notesService.createNotes(notes);
			return new ResponseEntity<NotesDTO>(notes,HttpStatus.OK);
		}catch(ConstraintViolationException ex) {
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(NotesException ex) {
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
		}
	}
	@GetMapping("/notes")
	public ResponseEntity<?> getAllNotes(){
		List<NotesDTO> notesDto = notesService.getAllNotes();
		return new ResponseEntity<List<NotesDTO>>(notesDto,notesDto.size()>0?HttpStatus.OK:HttpStatus.NOT_FOUND);
	}
	@GetMapping("/notes/createdDate/{pageSize}")
	public ResponseEntity<?> getSortedNotes(@PathVariable("pageSize")Integer pageSize){
		try {
		List<NotesDTO> notesDto = notesService.getSortedNotesByDate(pageSize);
		return new ResponseEntity<List<NotesDTO>>(notesDto,HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/notesTags/{tags}")
	public ResponseEntity<?> getNotesPerTag(@PathVariable("tags")String  tags){
		try {
		List<NotesDTO> notesDto = notesService.findByNotesTag(tags);
		return new ResponseEntity<List<NotesDTO>>(notesDto,HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/notes/{id}")
	public ResponseEntity<?> getSingleToDo(@PathVariable("id")String id){
		try {
			return new ResponseEntity<>(notesService.getSingleNotes(id),HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/stats/{id}")
	public ResponseEntity<?> getSingleStats(@PathVariable("id")String id){
		Map<String,Integer> map = notesService.fetchStatsById(id);
		if(map!=null && !map.isEmpty()) {
			return new ResponseEntity<Map<String,Integer>>(map,HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Notes not found with specified id",HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/stats")
	public ResponseEntity<?> getCompleteStats(){
		Map<String,Map<String,Integer>> map = notesService.fetchCompleteStats();
		if(map!=null && !map.isEmpty()) {
			return new ResponseEntity<Map<String,Map<String,Integer>>>(map,HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Notes not found ",HttpStatus.NOT_FOUND);
		}

	}


	@PutMapping("/notes/{id}")
	public ResponseEntity<?> updateNotesById(@PathVariable("id")String id,@RequestBody NotesDTO notesDto){
		try {
			notesService.updateNotes(id, notesDto);
			return new ResponseEntity<>("Updated notes with id successfully "+id,HttpStatus.OK);
		}catch(ConstraintViolationException ex) {
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(NotesException ex) {
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
		}

	}
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?>	deleteById(@PathVariable("id") String id){
		try {
			notesService.deleteNotes(id);
			return new ResponseEntity<>("Successfully deleted with id "+id,HttpStatus.OK);
		}catch(NotesException ex) {
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
		}
	}

}
