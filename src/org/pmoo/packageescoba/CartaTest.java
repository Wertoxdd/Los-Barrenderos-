package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;

public class CartaTest {
	// testeo para saber si getPalo devuelve los palos correctamente
    @Test
    public void testGetPalo() {
        Carta c = new Carta(Palo.Oros, 5);
        assertEquals(Palo.Oros, c.getPalo());
    }
    // testeo para saber si getValor devuelve los valores correctamente
    @Test
    public void testGetValor() {
        Carta c = new Carta(Palo.Copas, 3);
        assertEquals(3, c.getValor());
    }

    // TESTEOS PARA SABER SI CADA VALOR DE CARTA ES CORRECTO SEGUN LA FIGURA
    @Test
    public void testToStringSota() {
        Carta c = new Carta(Palo.Espadas, 8);
        assertEquals("Sota de Espadas", c.toString());
    }

    @Test
    public void testToStringCaballo() {
        Carta c = new Carta(Palo.Bastos, 9);
        assertEquals("Caballo de Bastos", c.toString());
    }

    @Test
    public void testToStringRey() {
        Carta c = new Carta(Palo.Oros, 10);
        assertEquals("Rey de Oros", c.toString());
    }

    @Test
    public void testToStringNumero() {
        Carta c = new Carta(Palo.Copas, 7);
        assertEquals("7 de Copas", c.toString());
    }
    
    
    @Test
    public void testToStringUno() {
        Carta c = new Carta(Palo.Espadas, 1);
        assertEquals("1 de Espadas", c.toString());
    }
}