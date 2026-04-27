package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;

public class ListaCartasTest {

    @Test
    public void testTamaño() {
        ListaCartas lista = new ListaCartas();
        assertEquals(0, lista.tamaño());
    }

    @Test
    public void testAgregarCartaAumentaTamano() {
        ListaCartas lista = new ListaCartas();
        lista.agregarCarta(new Carta(Palo.Oros, 1));
        lista.agregarCarta(new Carta(Palo.Copas, 2));
        
        assertEquals(2, lista.tamaño());
    }

    @Test
    public void testGetCartasMedianteIndice() {
        ListaCartas lista = new ListaCartas();
        Carta carta = new Carta(Palo.Bastos, 5);
        lista.agregarCarta(carta);
        
        assertEquals(1, lista.tamaño());
        assertEquals(carta, lista.obtenerCarta(0));
    }

    @Test
    public void testRecorrerCartasAgregadas() {
        ListaCartas lista = new ListaCartas();
        Carta carta1 = new Carta(Palo.Oros, 3);
        Carta carta2 = new Carta(Palo.Espadas, 7);
        
        lista.agregarCarta(carta1);
        lista.agregarCarta(carta2);
        
        assertEquals(2, lista.tamaño());
        assertEquals(carta1, lista.obtenerCarta(0));
        assertEquals(carta2, lista.obtenerCarta(1));
    }

    @Test
    public void testToStringDevuelveCadenaVaciaSiNoHayCartas() {
        ListaCartas lista = new ListaCartas();
        assertEquals("", lista.toString());
    }

    @Test
    public void testToStringFormateaCorrectamenteUnaCarta() {
        ListaCartas lista = new ListaCartas();
        lista.agregarCarta(new Carta(Palo.Bastos, 5));
        
        assertEquals("5 de Bastos", lista.toString());
    }

    @Test
    public void testToStringFormateaCorrectamenteMultiplesCartas() {
        ListaCartas lista = new ListaCartas();
        lista.agregarCarta(new Carta(Palo.Oros, 1));
        lista.agregarCarta(new Carta(Palo.Copas, 10));
        
        assertEquals("1 de Oros + Rey de Copas", lista.toString());
    }
}