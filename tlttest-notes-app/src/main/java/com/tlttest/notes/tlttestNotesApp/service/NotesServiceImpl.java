package com.tlttest.notes.tlttestNotesApp.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tlttest.notes.tlttestNotesApp.enums.Tags;
import com.tlttest.notes.tlttestNotesApp.exception.NotesException;
import com.tlttest.notes.tlttestNotesApp.model.NotesDTO;
import com.tlttest.notes.tlttestNotesApp.repository.NotesRepository;

@Service
public class NotesServiceImpl implements NotesService {
	@Autowired
	private NotesRepository notesRepo;

	@Override
	public void createNotes(NotesDTO notes) throws ConstraintViolationException ,NotesException {
		if(notes.getTags()!=null && !notes.getTags().isEmpty() && !validateNotesTags(notes)) {
			throw new NotesException(NotesException.InvalidTagException(notes.getTags()));
		}
		Optional<NotesDTO> notesOptional = notesRepo.findByNotesTitle(notes.getTitle());
		if(notesOptional.isPresent()) {
			throw new NotesException(NotesException.NotesAlreadyExists());
		}else {
			notes.setCreatedAt(new Date(System.currentTimeMillis()));
			notesRepo.save(notes);
		}
	}

	@Override
	public Map<String, Integer> fetchStatsById(String id) {
		Optional<NotesDTO> notesOptional = notesRepo.findById(id);
		if(notesOptional.isPresent()) {
			NotesDTO notesDTO = notesOptional.get();
			String text = notesDTO.getText();
			Map<String,Integer>  unSortedMap = fetchWordFrequency(text);
			return fetchSortedMap(unSortedMap,Comparator.reverseOrder());
		}
		return null;
	}


	private Map<String, Integer> fetchSortedMap(Map<String, Integer> unSortedMap, Comparator<?> reverseOrder) {
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		return sortedMap;
	}

	private Map<String, Integer> fetchWordFrequency(String text) {
		String [] arr = text.split(" ");
		Map<String, Integer> map = new HashMap<>();

		for (int i=0 ; i < arr.length ; i++){
			if (!map.containsKey(arr[i])){
				map.put(arr[i],1);
			} else{
				map.put(arr[i],map.get(arr[i])+1);
			}
		}
		return map;
	}

	@Override
	public Map<String, Map<String, Integer>> fetchCompleteStats() {
		Map<String, Map<String, Integer>> finalMap = new HashMap<String, Map<String,Integer>>();
		List<NotesDTO> notesDto = notesRepo.findAll();
		Map<String,Integer> sortedMap = new HashMap<String, Integer>();
		if(notesDto.size()>0) {
			for(NotesDTO notes:notesDto) {
				String text = notes.getText();
				Map<String,Integer>  unSortedMap = fetchWordFrequency(text);
				sortedMap = fetchSortedMap(unSortedMap,Comparator.reverseOrder());
				finalMap.put(notes.getId(), sortedMap);
			}
			return finalMap;
		}else {
			return null;
		}
	}

	@Override
	public List<NotesDTO> getAllNotes() {
		List<NotesDTO> notes = notesRepo.findAll();
		if(notes.size()>0) {
			return notes;
		}else {
			return new ArrayList<NotesDTO>();
		}
	}

	@Override
	public NotesDTO getSingleNotes(String id) throws NotesException {
		Optional<NotesDTO> optionalNotes = notesRepo.findById(id);
		if(!optionalNotes.isPresent()) {
			throw new NotesException(NotesException.NotFoundException(id));
		}else {
			return optionalNotes.get();
		}
	}

	@Override
	public void updateNotes(String id, NotesDTO notesDto) throws NotesException {
		Optional<NotesDTO> notesOptional = notesRepo.findById(id);
		if(notesOptional.isPresent()) {
			NotesDTO notesToSave = notesOptional.get();
			if(notesDto.getTags()!=null && !notesDto.getTags().isEmpty() && !validateNotesTags(notesDto)) {
				throw new NotesException(NotesException.InvalidTagException(notesDto.getTags()));
			}
			notesToSave.setTags(notesDto.getTags());
			notesToSave.setText(notesDto.getText());
			notesToSave.setTitle(notesDto.getTitle());
			notesToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
			notesRepo.save(notesToSave);
		}else {
			throw new NotesException(NotesException.NotFoundException(id));
		}

	}
	private boolean validateNotesTags(NotesDTO notes) {
		boolean tagFlag = false;
		if(notes.getTags()!=null) {
			for(Tags testVal:Tags.values()) {
				if(testVal.getTag().equalsIgnoreCase(notes.getTags().trim())) {
					notes.setTags(testVal.getTag());
					tagFlag= true;
					break;
				}
			}

		}

		return tagFlag;
	}

	@Override
	public void deleteNotes(String id) throws NotesException {
		Optional<NotesDTO> optionalNotes = notesRepo.findById(id);
		if(!optionalNotes.isPresent()) {
			throw new NotesException(NotesException.NotFoundException(id));
		}
		else {
			notesRepo.deleteById(id);
		}
	
}

	@Override
	public List<NotesDTO> getSortedNotesByDate(Integer pageSize) throws NotesException {
		List<NotesDTO> notesDTO = notesRepo.sortAndLimit1(PageRequest.of(0, pageSize,Sort.by(Direction.DESC,"createdAt")));
		return notesDTO;
	}

	@Override
	public List<NotesDTO> findByNotesTag(String tags) throws NotesException {
		List<NotesDTO> notesDTO = notesRepo.findByNotesTag(tags,PageRequest.of(0, 5,Sort.by(Direction.DESC,"createdAt")));
		return notesDTO;
	}

}
