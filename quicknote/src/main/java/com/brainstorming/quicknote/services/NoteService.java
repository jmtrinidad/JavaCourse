package com.brainstorming.quicknote.services;

import com.brainstorming.quicknote.models.Note;
import com.brainstorming.quicknote.repositories.NoteRepository;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteRepository noteRepository;

  public Optional<Note> getNoteById(Long id) {

    if (id == null || id <= 0) {
      return Optional.empty();
    }

    return noteRepository.findById(id);
  }

  public void save(Note note) {

    noteRepository.save(note);
  }

  public List<Note> getAllNotes(Pageable pageable) {

    return noteRepository
        .findAll(
            PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                // Sort.by(Sort.Direction.DESC, "title")
                pageable.getSort()))
        .getContent();
  }

  public void delete(Note note) {

    noteRepository.delete(note);
  }
}
