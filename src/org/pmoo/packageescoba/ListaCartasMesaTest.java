package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;

public class ListaCartasMesaTest {

    @Test
    public void testEstaVaciaDevuelveTrueAlCrearMesa() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        assertTrue(mesa.estaVacia());
    }

    @Test
    public void testEliminarCartaQuitaLaCartaCorrectaDeLaMesa() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        Carta carta1 = new Carta(Palo.Espadas, 4);
        mesa.agregarCarta(carta1);
        mesa.eliminarCarta(carta1);
        assertTrue(mesa.estaVacia());
    }

    @Test
    public void testSumaQuinceDevuelveTrueCombinandoVariasCartasEnMesa() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Copas, 2));
        mesa.agregarCarta(new Carta(Palo.Espadas, 3));
        mesa.agregarCarta(new Carta(Palo.Bastos, 10));
        assertTrue(mesa.sumaQuince(new Carta(Palo.Oros, 0))); // 2+3+10 = 15
    }

    @Test
    public void testCapturaConMesaVaciaDevuelveFalse() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        Carta miCarta = new Carta(Palo.Oros, 5);
        assertFalse("No se puede capturar nada si no hay cartas en el tablero", mesa.sumaQuince(miCarta));
    }

    @Test
    public void testSumaInvalidaNoPermiteCaptura() {
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Bastos, 5));
        mesa.agregarCarta(new Carta(Palo.Bastos, 2));
        // 5 + 2 + 7 = 14 (No llega a 15)
        Carta miCarta = new Carta(Palo.Oros, 7);
        assertFalse("No debe permitir captura si la suma es diferente de 15", mesa.sumaQuince(miCarta));
    }
}
