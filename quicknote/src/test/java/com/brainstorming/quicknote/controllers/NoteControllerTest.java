package com.brainstorming.quicknote.controllers;

import java.net.URI;
import static org.assertj.core.api.Assertions.assertThat;

import com.brainstorming.quicknote.dtos.NoteDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteControllerTest {
  @Autowired private TestRestTemplate restTemplate;

  @Test
  void shouldReturnNote() {
    ResponseEntity<String> response = restTemplate.getForEntity("/notes/1", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    DocumentContext documentContext = JsonPath.parse(response.getBody());

    Long id = documentContext.read("$.id", Long.class);
    assertThat(id).isNotNull();
    assertThat(id).isEqualTo(1L);

    String title = documentContext.read("$.title", String.class);
    assertThat(title).isNotNull();
  }

  @Test
  void shouldNotReturnNoteWithAndUnknownId() {
    ResponseEntity<String> response = restTemplate.getForEntity("/notes/5555", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(response.getBody()).isBlank();
  }

  @Test
  // INDICA QUE EL CONTEXTO DEBE SER REINICIADO DESPUES DE ESTA PRUEBA
  // EL CONTEXTO SE REINICIA PARA ASEGURAR QUE LOS CAMBIOS REALIZADOS EN ESTA PRUEBA NO AFECTEN A
  // OTRAS PRUEBAS
  // ESPECIALMENTE IMPORTANTE CUANDO SE REALIZAN OPERACIONES DE ESCRITURA EN LA BASE DE DATOS
  // PARA MANTENER LA INTEGRIDAD DE LAS PRUEBAS .
  @DirtiesContext
  void shouldCreateNote() {
    NoteDto noteDto =
        new NoteDto(
            0L,
            "Meeting Notes",
            "Discuss project timeline and deliverables",
            "text",
            null,
            false,
            false,
            "blue");

    ResponseEntity<Void> createResponse = restTemplate.postForEntity("/notes", noteDto, Void.class);

    assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    URI location = createResponse.getHeaders().getLocation();

    ResponseEntity<String> response = restTemplate.getForEntity(location, String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  @DirtiesContext
  void shouldUpdateNoteNotBadRequest() {
    NoteDto noteDto =
        new NoteDto(
            1L,
            "Meeting Notes Updated",
            "Discuss project timeline and deliverables",
            "text",
            null,
            false,
            true,
            "green");
    HttpEntity<NoteDto> requestUpdate = new HttpEntity<>(noteDto);

    ResponseEntity<Void> updateResponse =
        restTemplate.exchange("/notes/0", HttpMethod.PUT, requestUpdate, Void.class);

    assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  @DirtiesContext
  void shouldUpdateNoteNotExists() {
    NoteDto noteDto =
        new NoteDto(
            1L,
            "Meeting Notes Updated",
            "Discuss project timeline and deliverables",
            "text",
            null,
            false,
            true,
            "green");
    HttpEntity<NoteDto> requestUpdate = new HttpEntity<>(noteDto);

    ResponseEntity<Void> updateResponse =
        restTemplate.exchange("/notes/88", HttpMethod.PUT, requestUpdate, Void.class);

    assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  @DirtiesContext
  void shouldUpdateNote() {
    NoteDto noteDto =
        new NoteDto(
            1L,
            "Meeting Notes Updated",
            "Discuss project timeline and deliverables",
            "text",
            null,
            false,
            true,
            "green");
    HttpEntity<NoteDto> requestUpdate = new HttpEntity<>(noteDto);

    ResponseEntity<Void> updateResponse =
        restTemplate.exchange("/notes/1", HttpMethod.PUT, requestUpdate, Void.class);

    assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    ResponseEntity<String> response = restTemplate.getForEntity("/notes/1", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    DocumentContext documentContext = JsonPath.parse(response.getBody());
    String title = documentContext.read("$.title", String.class);
    assertThat(title).isEqualTo("Meeting Notes Updated");
  }

  @Test
  @DirtiesContext
  void shouldDeleteNoteNotExistsReturnNotFound() {

    ResponseEntity<Void> updateResponse =
        restTemplate.exchange("/notes/88", HttpMethod.DELETE, null, Void.class);

    assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  @DirtiesContext
  void shouldDeleteANote() {

    ResponseEntity<Void> updateResponse =
        restTemplate.exchange("/notes/1", HttpMethod.DELETE, null, Void.class);

    assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    ResponseEntity<String> response = restTemplate.getForEntity("/notes/1", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  void shouldReturnAllNotes() {
    ResponseEntity<String> response = restTemplate.getForEntity("/notes", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    DocumentContext documentContext = JsonPath.parse(response.getBody());

    int size = documentContext.read("$.size()");
    assertThat(size).isEqualTo(7);

    JSONArray ids = documentContext.read("$..id");
    assertThat(ids).isNotEmpty();
    assertThat(ids).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6, 7);

    JSONArray titles = documentContext.read("$..title");
    assertThat(titles).isNotEmpty();
  }

  @Test
  void shouldReturnAllNotesWithPagination() {
    ResponseEntity<String> response =
        restTemplate.getForEntity("/notes?page=0&size=3", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    DocumentContext documentContext = JsonPath.parse(response.getBody());

    int size = documentContext.read("$.size()");
    assertThat(size).isEqualTo(3);

    JSONArray page = documentContext.read("$[*]");
    assertThat(page).isNotEmpty();
    assertThat(page.size()).isEqualTo(3);
  }

  @Test
  void shouldReturnAllNotesPaginationWithSorting() {
    ResponseEntity<String> response =
        restTemplate.getForEntity("/notes?page=0&size=3&sort=title,desc", String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    DocumentContext documentContext = JsonPath.parse(response.getBody());

    int size = documentContext.read("$.size()");
    assertThat(size).isEqualTo(3);

    JSONArray page = documentContext.read("$[*]");
    assertThat(page).isNotEmpty();
    assertThat(page.size()).isEqualTo(3);

    String fisrtTitle = documentContext.read("$[0].title", String.class);
    assertThat(fisrtTitle).isEqualTo("Shopping list");

    String secondTitle = documentContext.read("$[2].title", String.class);
    assertThat(secondTitle).isEqualTo("Meeting notes");
  }
}
