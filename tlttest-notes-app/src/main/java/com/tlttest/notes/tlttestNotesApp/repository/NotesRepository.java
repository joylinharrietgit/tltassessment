package com.tlttest.notes.tlttestNotesApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tlttest.notes.tlttestNotesApp.model.NotesDTO;
@Repository
public interface NotesRepository extends MongoRepository<NotesDTO, String> {
	@Query("{'title':?0}")
	Optional<NotesDTO> findByNotesTitle(String title);

	@Query("{}")
	List<NotesDTO> sortAndLimit1(Pageable pageable);

	@Query("{'tags':?0}")
	List<NotesDTO> findByNotesTag(String tags,Pageable pageable);
}
