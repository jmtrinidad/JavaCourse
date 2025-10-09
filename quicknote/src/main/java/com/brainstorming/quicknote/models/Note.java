package com.brainstorming.quicknote.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.*;

@Entity
@Table(name = "notes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  String title;
  String content;
  String type;

  @Column(name = "creation_date")
  LocalDateTime creationDate;

  @Column(name = "is_pinned")
  boolean isPinned;

  @Column(name = "is_archived")
  boolean isArchived;

  String color;
}
