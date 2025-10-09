package com.brainstorming.quicknote.controllers;

import com.brainstorming.quicknote.dtos.NoteDto;
import com.brainstorming.quicknote.models.Note;
import com.brainstorming.quicknote.services.NoteService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tools.jackson.dataformat.xml.XmlMapper;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

  private final NoteService noteService;
  private XmlMapper xmlMapper = new XmlMapper();

  @GetMapping("")
  private ResponseEntity<List<NoteDto>> getAll(Pageable pageable) {

    var notes = noteService.getAllNotes(pageable);

    var noteDtos = notes.stream().map(NoteDto::fromEntity).toList();

    return ResponseEntity.ok(noteDtos);
  }

  @GetMapping(
      value = "/{id}",
      produces = {"application/xml"})
  private ResponseEntity<String> getNoteById(@PathVariable Long id) {

    Optional<Note> noteOptional = noteService.getNoteById(id);

    if (noteOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Note note = noteOptional.get();

    NoteDto noteDto = NoteDto.fromEntity(note);
    String xml = xmlMapper.writeValueAsString(noteDto);

    return ResponseEntity.ok(xml);
  }

  @PostMapping("")
  private ResponseEntity<Void> createNote(
      @RequestBody NoteDto noteDto, UriComponentsBuilder uriBuilder) {

    Note note = noteDto.toEntity();

    noteService.save(note);

    URI location = uriBuilder.path("/notes/{id}").buildAndExpand(note.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{id}")
  private ResponseEntity<Void> udpate(@PathVariable Long id, @RequestBody NoteDto noteDto) {

    if (id <= 0) {
      return ResponseEntity.badRequest().build();
    }

    Optional<Note> noteOptional = noteService.getNoteById(id);

    if (noteOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Note note = noteDto.toEntity();

    note.setId(id);

    noteService.save(note);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  private ResponseEntity<Void> delete(@PathVariable Long id) {

    Optional<Note> noteOptional = noteService.getNoteById(id);

    if (noteOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Note note = noteOptional.get();

    noteService.delete(note);

    return ResponseEntity.noContent().build();
  }
}
