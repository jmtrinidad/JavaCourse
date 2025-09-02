package com.coursojava.sesion14;

import java.io.*;

public class FicherosApp {
    public static void main(String[] args) {
        System.out.println("Ejemplo de Ficheros");
      var fileName="C:\\Test-File\\test.txt";

        File file=null;

        try{
            file = new File(fileName);

            if(file.createNewFile()){
                System.out.println("Success!");
            }else {
                System.out.println("Error, file already exists");
            }

        }catch (IOException e){
//             e.printStackTrace();
            System.out.println(e.getMessage());
        }

        BufferedWriter out = null;

        try{
            FileWriter fileWriter = new FileWriter(fileName);
            out= new BufferedWriter(fileWriter);

            out.write("aString");
            out.close();
            System.out.println("File created successfully");

        }catch (IOException e){

            System.out.println("Fallo al escribirel fichero");
            System.out.println(e.getMessage());
        }finally {
            System.out.println("Ejecutado en ambos casos try completo o catch");
        }

        FileReader fileReader=null ;
        BufferedReader bufferReader ;
        try{
            /*
            * Apertura del fichero y creacion de BufferedReader para poder
            * hacer  una lectura comoda (Disponer del estado readLine()).
            * */
              file = new File(fileName);
              fileReader= new FileReader(file);
              bufferReader= new BufferedReader(fileReader);

            String linea;

            while ((linea= bufferReader.readLine()) !=null ){
                System.out.println(linea);
            }

        }catch (IOException e){

        }finally {
            /*
            * En el finally cerramos el fichero, para asegurarnos
            * que se cierre tanto si todo va bien como si salto
            * una exception
            * */

            try{
                if(fileReader != null ){
                    fileReader.close();
                }
            }catch (IOException e){

            }
        }
    }
}
