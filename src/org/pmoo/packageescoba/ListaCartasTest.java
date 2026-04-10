package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaCartasTest {

    @Test
    public void testConstructorIniciaTamanoCero() {
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
    public void testGetCartasDevuelveListaCorrecta() {
        ListaCartas lista = new ListaCartas();
        Carta carta = new Carta(Palo.Bastos, 5);
        lista.agregarCarta(carta);
        
        ArrayList<Carta> extraida = lista.getCartas();
        
        assertEquals(1, extraida.size());
        assertEquals(carta, extraida.get(0));
    }

    @Test
    public void testGetIteradorRecorreCartasAgregadas() {
        ListaCartas lista = new ListaCartas();
        Carta carta1 = new Carta(Palo.Oros, 3);
        Carta carta2 = new Carta(Palo.Espadas, 7);
        
        lista.agregarCarta(carta1);
        lista.agregarCarta(carta2);
        
        Iterator<Carta> iterador = lista.getIterador();
        
        assertTrue(iterador.hasNext());
        assertEquals(carta1, iterador.next());
        
        assertTrue(iterador.hasNext());
        assertEquals(carta2, iterador.next());
        
        assertFalse(iterador.hasNext());
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