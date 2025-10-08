package com.brainstorming.quicknote.repositories;

import com.brainstorming.quicknote.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
