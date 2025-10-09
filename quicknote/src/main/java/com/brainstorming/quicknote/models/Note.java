package com.brainstorming.quicknote.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tools.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.time.LocalDateTime;


@Entity
@Table(name = "notes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

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


