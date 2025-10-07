package com.brainstorming.quicknote;

import com.brainstorming.quicknote.dtos.NoteDto;
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

  @Autowired private JacksonTester<NoteDto> json;

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
}
