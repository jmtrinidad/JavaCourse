package com.coursojava.sesion02;

public class Bucles {
    public static void main(String[] args) {
       int[] numbers = new int[5];

       int[] numbersPasiva = {1,5,8,9,5,85};

       for (int i=0; i < 4; i++){
           numbers[i]= i * 5;
       }
    //ESTO ES EQUIVALENTE A UN foreach DE C#
       for (int number: numbers)
           System.out.println(number);
    }
}
