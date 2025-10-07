package com.brainstorming.quicknote.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

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
        assertThat(title).isEqualTo("Grocery List");

    }
}
