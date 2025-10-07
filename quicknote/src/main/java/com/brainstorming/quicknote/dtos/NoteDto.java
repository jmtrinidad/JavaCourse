package com.brainstorming.quicknote.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record NoteDto(
    long id,
    String title,
    String content,
    String type,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") LocalDateTime creationDate,
    boolean isPinned,
    boolean isArchived,
    String color) {}
