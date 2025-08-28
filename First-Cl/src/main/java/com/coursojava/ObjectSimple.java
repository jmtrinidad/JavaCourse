package com.coursojava;



public class ObjectSimple {
    public static void main(String[] args) {
      String test="Tests";

     var tes="Test";

     tes="25";

        try {
            double result= Double.parseDouble(tes);

            System.out.println(result);

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
