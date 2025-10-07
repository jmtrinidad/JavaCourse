package com.brainstorming.quicknote.controllers;

import com.brainstorming.quicknote.dtos.NoteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @GetMapping("")
    private ResponseEntity<NoteDto> getAll() {

        NoteDto note =
                new NoteDto(
                        1L,
                        "Grocery List",
                        "Milk, bread, eggs, cheese",
                        "text",
                        LocalDateTime.of(2025, 8, 12, 14, 30),
                        true,
                        false,
                        "yellow");

        return ResponseEntity.ok(note);
    }

    @GetMapping("/{id}")
    private ResponseEntity<NoteDto> getNoteById(@PathVariable Long id) {

        NoteDto note =
                new NoteDto(
                        1L,
                        "Grocery List",
                        "Milk, bread, eggs, cheese",
                        "text",
                        LocalDateTime.of(2025, 8, 12, 14, 30),
                        true,
                        false,
                        "yellow");

        return ResponseEntity.ok(note);
    }
}
