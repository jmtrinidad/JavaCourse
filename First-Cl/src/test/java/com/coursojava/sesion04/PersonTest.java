package com.coursojava.sesion04;

import com.coursojava.sesion04.ejemplo02.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    public  void testConstructWithParameter(){
     //Given
        var firstName="Carlos";
        var lastName="Paulino";
        Person person;

        //When
          person = new Person(firstName, lastName);

        //Then
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
    }

    @Test
    public  void testConstructSinParameter(){
        //Given

       Person person;
        //When
          person = new Person();

        //Then
        assertEquals("", person.getFirstName());
        assertEquals("", person.getLastName());
    }
}
