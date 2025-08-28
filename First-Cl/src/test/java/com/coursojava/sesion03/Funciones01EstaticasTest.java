package com.coursojava.sesion03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import  static  com.coursojava.sesion03.FuncioneEstaticas.*;

public class Funciones01EstaticasTest {
    @Test
    public  void testSum(){
      int res= sum(5,2);
      assertEquals(7,res);
    }
}
