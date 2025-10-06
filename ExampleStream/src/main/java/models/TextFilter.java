package models;

public class TextFilter {

    public static boolean remove(String s){

        return s.equals("Java");
    }

    public int compare(String s1, String s2){
        return s1.compareTo(s2);
    }
}
