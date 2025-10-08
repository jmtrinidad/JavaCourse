package com.brainstorming.quicknote;

import com.brainstorming.quicknote.dtos.NoteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class QuickNoteTest {

  private NoteDto[] notes;

  // ESTA ES LA FORMA DE INYECTAR DEPENDENCIAS EN UNA CLASE DE PRUEBA DE SPRING
  // EL FRAMEWORK DE SPRING SE ENCARGA DE PROPORCIONAR UNA INSTANCIA DE JACKSONTESTER
  // CONFIGURADA CORRECTAMENTE PARA SERIALIZAR Y DESERIALIZAR OBJETOS NoteDto
  // ESTO PERMITE UTILIZAR JACKSONTESTER EN LAS PRUEBAS SIN NECESIDAD DE CONFIGURARLO MANUALMENTE
  // LO CUAL SIMPLIFICA EL CODIGO DE LA PRUEBA Y REDUCE LA POSIBILIDAD DE ERRORES DE CONFIGURACION
  // ASEGURANDO QUE LAS PRUEBAS SEAN MAS CONFIABLES
  // EN RESUMEN, @AUTOWIRED PERMITE QUE SPRING INYECTE AUTOMATICAMENTE UNA INSTANCIA DE
  // JACKSONTESTER
  // EN LA CLASE DE PRUEBA, FACILITANDO ASI LAS PRUEBAS DE SERIALIZACION Y DESERIALIZACION DE
  // OBJETOS NoteDto
  // EN EL CONTEXTO DE UNA APLICACION SPRING
  @Autowired private JacksonTester<NoteDto[]> jsonArray;

  @Autowired private JacksonTester<NoteDto> json;

  @BeforeEach
  void setup() {
    notes =
        new NoteDto[] {
          new NoteDto(
              1L,
              "Grocery List",
              "Milk, bread, eggs, cheese",
              "text",
              LocalDateTime.of(2025, 8, 12, 14, 30),
              true,
              false,
              "yellow"),
          new NoteDto(
              2L,
              "Buy groceries",
              "Milk, bread, eggs",
              "text",
              LocalDateTime.of(2025, 8, 16, 10, 0),
              false,
              false,
              "white"),
          new NoteDto(
              3L,
              "Gym workout",
              "30 minutes cardio, 20 minutes weights",
              "text",
              LocalDateTime.of(2025, 8, 15, 8, 0),
              true,
              false,
              "yellow"),
          new NoteDto(
              4L,
              "Meeting notes",
              "Discuss project milestones and deadlines",
              "text",
              LocalDateTime.of(2025, 8, 14, 9, 0),
              false,
              false,
              "white"),
          new NoteDto(
              5L,
              "Shopping list",
              "To buy: apples, bananas, oranges",
              "text",
              LocalDateTime.of(2025, 8, 13, 11, 0),
              false,
              false,
              "white"),
          new NoteDto(
              6L,
              "Project ideas",
              "Brainstorming session notes",
              "text",
              LocalDateTime.of(2025, 8, 17, 12, 0),
              false,
              false,
              "white"),
          new NoteDto(
              7L,
              "Book recommendations",
              "1. The Great Gatsby. To Kill a Mockingbird. 1984",
              "text",
              LocalDateTime.of(2025, 8, 18, 9, 0),
              false,
              false,
              "white")
        };
  }

  @Test
  void firstTest() {
    assertThat(7).isEqualTo(7);
  }

  @Test
  void noteSerializationTest() throws IOException {

    NoteDto note =
        new NoteDto(
            74251721L,
            "Grocery List",
            "Milk, bread, eggs, cheese",
            "text",
            LocalDateTime.of(2025, 8, 12, 14, 30),
            true,
            false,
            "yellow");

    JsonContent<NoteDto> jsonContent = json.write(note);

    assertThat(jsonContent).isStrictlyEqualToJson("expected.json");
    assertThat(jsonContent).hasJsonPathNumberValue("$.id");
    assertThat(jsonContent).extractingJsonPathNumberValue("$.id").isEqualTo(74251721);
    assertThat(jsonContent).hasJsonPathStringValue("$.title");
    assertThat(jsonContent).extractingJsonPathStringValue("$.title").isEqualTo("Grocery List");
    assertThat(jsonContent).hasJsonPathStringValue("$.content");
    assertThat(jsonContent)
        .extractingJsonPathStringValue("$.content")
        .isEqualTo("Milk, bread, eggs, cheese");
    assertThat(jsonContent).hasJsonPathStringValue("$.type");
    assertThat(jsonContent).extractingJsonPathStringValue("$.type").isEqualTo("text");
    assertThat(jsonContent).hasJsonPathStringValue("$.creationDate");
    assertThat(jsonContent)
        .extractingJsonPathStringValue("$.creationDate")
        .isEqualTo("2025-08-12T14:30:00Z");
  }

  @Test
  void noteDesealizationTest() throws IOException {

    String expected =
        """
                    {
                        "id": 74251721,
                        "title": "Grocery List",
                        "content": "Milk, bread, eggs, cheese",
                        "type": "text",
                        "creationDate": "2025-08-12T14:30:00Z",
                        "isPinned": true,
                        "isArchived": false,
                        "color": "yellow"
                    }
                """;

    NoteDto note =
        new NoteDto(
            74251721L,
            "Grocery List",
            "Milk, bread, eggs, cheese",
            "text",
            LocalDateTime.of(2025, 8, 12, 14, 30),
            true,
            false,
            "yellow");

    assertThat(json.parse(expected)).isEqualTo(note);

    assertThat(json.parseObject(expected).id()).isEqualTo(74251721L);
    assertThat(json.parseObject(expected).title()).isEqualTo("Grocery List");
    assertThat(json.parseObject(expected).content()).isEqualTo("Milk, bread, eggs, cheese");
    assertThat(json.parseObject(expected).type()).isEqualTo("text");
    assertThat(json.parseObject(expected).creationDate())
        .isEqualTo(LocalDateTime.of(2025, 8, 12, 14, 30));
    assertThat(json.parseObject(expected).isPinned()).isTrue();
    assertThat(json.parseObject(expected).isArchived()).isFalse();
    assertThat(json.parseObject(expected).color()).isEqualTo("yellow");
  }

  @Test
  void noteListSerializationTest() throws IOException {

    assertThat(jsonArray.write(notes)).isStrictlyEqualToJson("list.json");
  }

  @Test
  void noteListDeserializationTest() throws IOException {

    String expected =
        """
          [
          {
            "id": 1,
            "title": "Grocery List",
            "content": "Milk, bread, eggs, cheese",
            "type": "text",
            "creationDate": "2025-08-12T14:30:00Z",
            "isPinned": true,
            "isArchived": false,
            "color": "yellow"
          },
          {
            "id": 2,
            "title": "Buy groceries",
            "content": "Milk, bread, eggs",
            "type": "text",
            "creationDate": "2025-08-16T10:00:00Z",
            "isPinned": false,
            "isArchived": false,
            "color": "white"
          },
          {
            "id": 3,
            "title": "Gym workout",
            "content": "30 minutes cardio, 20 minutes weights",
            "type": "text",
            "creationDate": "2025-08-15T08:00:00Z",
            "isPinned": true,
            "isArchived": false,
            "color": "yellow"
          },
          {
            "id": 4,
            "title": "Meeting notes",
            "content": "Discuss project milestones and deadlines",
            "type": "text",
            "creationDate": "2025-08-14T09:00:00Z",
            "isPinned": false,
            "isArchived": false,
            "color": "white"
          },
          {
            "id": 5,
            "title": "Shopping list",
            "content": "To buy: apples, bananas, oranges",
            "type": "text",
            "creationDate": "2025-08-13T11:00:00Z",
            "isPinned": false,
            "isArchived": false,
            "color": "white"
          },
          {
            "id": 6,
            "title": "Project ideas",
            "content": "Brainstorming session notes",
            "type": "text",
            "creationDate": "2025-08-17T12:00:00Z",
            "isPinned": false,
            "isArchived": false,
            "color": "white"
          },
          {
            "id": 7,
            "title": "Book recommendations",
            "content": "1. The Great Gatsby. To Kill a Mockingbird. 1984",
            "type": "text",
            "creationDate": "2025-08-18T09:00:00Z",
            "isPinned": false,
            "isArchived": false,
            "color": "white"
          }
        ]
 """;

    assertThat(jsonArray.parse(expected)).isEqualTo(notes);
  }
}
