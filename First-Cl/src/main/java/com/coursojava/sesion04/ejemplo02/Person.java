package com.coursojava.sesion04.ejemplo02;

import java.util.Objects;

public class Person {

    private  String FirstName;

    private  String LastName;

    public String getLastName() {
        return LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public  Person(String firstName, String lastName){
        this.FirstName=firstName;
        this.LastName=lastName;
    }
    public  Person(){
        this.FirstName="";
        this.LastName="";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(FirstName, person.FirstName) && Objects.equals(LastName, person.LastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FirstName, LastName);
    }
}
