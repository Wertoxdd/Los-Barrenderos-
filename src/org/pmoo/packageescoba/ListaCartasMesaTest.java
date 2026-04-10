package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Iterator;

public class ListaCartasMesaTest {

    @Test
    public void testEstaVaciaDevuelveTrueAlCrearMesa() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        
        assertTrue(mesa.estaVacia());
    }

    @Test
    public void testEstaVaciaDevuelveFalseAlAgregarCarta() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Oros, 5));
        
        assertFalse(mesa.estaVacia());
    }

    @Test
    public void testEliminarCartaQuitaLaCartaCorrectaDeLaMesa() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        Carta carta1 = new Carta(Palo.Espadas, 4);
        Carta carta2 = new Carta(Palo.Bastos, 7);
        
        mesa.agregarCarta(carta1);
        mesa.agregarCarta(carta2);
        
        mesa.eliminarCarta(carta1);
        
        assertEquals(1, mesa.tamaño());
        
        Iterator<Carta> iterador = mesa.getIterador();
        assertEquals(carta2, iterador.next());
    }

    @Test
    public void testSumaQuinceDevuelveFalseSiLaMesaEstaVacia() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        Carta carta = new Carta(Palo.Copas, 10);
        
        assertFalse(mesa.sumaQuince(carta));
    }

    @Test
    public void testSumaQuinceDevuelveTrueConUnaSolaCartaEnMesa() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Oros, 5));
        
        Carta carta = new Carta(Palo.Bastos, 10);
        
        assertTrue(mesa.sumaQuince(carta));
    }

    @Test
    public void testSumaQuinceDevuelveTrueCombinandoVariasCartasEnMesa() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Copas, 2));
        mesa.agregarCarta(new Carta(Palo.Espadas, 3));
        mesa.agregarCarta(new Carta(Palo.Oros, 6));
        
        Carta carta = new Carta(Palo.Bastos, 10);
        
        assertTrue(mesa.sumaQuince(carta));
    }

    @Test
    public void testSumaQuinceDevuelveFalseSiNingunaCombinacionSumaQuince() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Copas, 2));
        mesa.agregarCarta(new Carta(Palo.Espadas, 2));
        
        Carta carta = new Carta(Palo.Bastos, 10);
        
        assertFalse(mesa.sumaQuince(carta));
    }

    @Test
    public void testSumaQuinceDevuelveTrueExcluyendoCartasInnecesarias() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Copas, 8));
        mesa.agregarCarta(new Carta(Palo.Espadas, 7));
        mesa.agregarCarta(new Carta(Palo.Oros, 2));
        
        Carta carta = new Carta(Palo.Bastos, 6);
        
        assertTrue(mesa.sumaQuince(carta));
    }
}