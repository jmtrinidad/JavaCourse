package org.example.class_anidadas;

public class OuterClass {

    private String outerField="Outer Field";

    public class InnerClass {
        public void display() {
            System.out.println("Accessing: " + outerField);
        }
    }
}
