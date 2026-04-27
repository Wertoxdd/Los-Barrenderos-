package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;

public class ListaCartasMontonTest {

    @Test
    public void testAgregarCartasSumaLosMontonesCorrectamente() {
        ListaCartasMonton montonPrincipal = new ListaCartasMonton();
        montonPrincipal.agregarCarta(new Carta(Palo.Oros, 1));

        ListaCartasMonton montonAdicional = new ListaCartasMonton();
        montonAdicional.agregarCarta(new Carta(Palo.Copas, 2));
        montonAdicional.agregarCarta(new Carta(Palo.Espadas, 3));

        montonPrincipal.agregarCartas(montonAdicional);

        assertEquals(3, montonPrincipal.tamaño());
    }

    @Test
    public void testContarOrosDevuelveCeroSiMontonEstaVacio() {
        ListaCartasMonton monton = new ListaCartasMonton();
        assertEquals(0, monton.contarOros());
    }

    @Test
    public void testContarOrosDevuelveCantidadCorrecta() {
        ListaCartasMonton monton = new ListaCartasMonton();
        monton.agregarCarta(new Carta(Palo.Oros, 1));
        monton.agregarCarta(new Carta(Palo.Copas, 3));
        monton.agregarCarta(new Carta(Palo.Oros, 5));
        assertEquals(2, monton.contarOros());
    }

    @Test
    public void testContarSietesDevuelveCantidadCorrecta() {
        ListaCartasMonton monton = new ListaCartasMonton();
        monton.agregarCarta(new Carta(Palo.Espadas, 7));
        monton.agregarCarta(new Carta(Palo.Oros, 7));
        monton.agregarCarta(new Carta(Palo.Bastos, 5));
        assertEquals(2, monton.contarSietes());
    }

    @Test
    public void testTieneSieteDeOrosDevuelveTrueSiEstaPresente() {
        ListaCartasMonton monton = new ListaCartasMonton();
        monton.agregarCarta(new Carta(Palo.Oros, 7));
        assertTrue(monton.tieneSieteDeOros());
    }

    @Test
    public void testAcumulacionExtrema20Cartas() {
        ListaCartasMonton monton = new ListaCartasMonton();
        for (int i = 0; i < 20; i++) {
            monton.agregarCarta(new Carta(Palo.Espadas, 1));
        }
        assertEquals("Debe poder gestionar y contar correctamente 20 cartas", 20, monton.tamaño());
    }

    @Test
    public void testConteoDetalladoOrosYSietes() {
        ListaCartasMonton monton = new ListaCartasMonton();
        // Escenario: 5 oros en total, de los cuales 2 son sietes.
        monton.agregarCarta(new Carta(Palo.Oros, 7));  // Oro + Siete
        monton.agregarCarta(new Carta(Palo.Oros, 1));  // Oro
        monton.agregarCarta(new Carta(Palo.Oros, 2));  // Oro
        monton.agregarCarta(new Carta(Palo.Oros, 3));  // Oro
        monton.agregarCarta(new Carta(Palo.Oros, 4));  // Oro
        monton.agregarCarta(new Carta(Palo.Copas, 7)); // Siete (no oro)
        
        assertEquals("Debe contar exactamente 5 oros", 5, monton.contarOros());
        assertEquals("Debe contar exactamente 2 sietes", 2, monton.contarSietes());
    }
}
