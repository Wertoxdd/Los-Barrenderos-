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
    public void testContarOrosIgnoraOtrosPalos() {
        ListaCartasMonton monton = new ListaCartasMonton();
        monton.agregarCarta(new Carta(Palo.Copas, 3));
        monton.agregarCarta(new Carta(Palo.Espadas, 5));
        
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
    public void testContarSietesDevuelveCeroSiMontonEstaVacio() {
        ListaCartasMonton monton = new ListaCartasMonton();
        
        assertEquals(0, monton.contarSietes());
    }

    @Test
    public void testContarSietesIgnoraOtrosNumeros() {
        ListaCartasMonton monton = new ListaCartasMonton();
        monton.agregarCarta(new Carta(Palo.Espadas, 6));
        monton.agregarCarta(new Carta(Palo.Bastos, 8));
        
        assertEquals(0, monton.contarSietes());
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
    public void testTieneSieteDeOrosDevuelveFalseEnMontonVacio() {
        ListaCartasMonton monton = new ListaCartasMonton();
        
        assertFalse(monton.tieneSieteDeOros());
    }

    @Test
    public void testTieneSieteDeOrosDevuelveFalseSiHaySietesDeOtrosPalos() {
        ListaCartasMonton monton = new ListaCartasMonton();
        monton.agregarCarta(new Carta(Palo.Espadas, 7));
        monton.agregarCarta(new Carta(Palo.Bastos, 7));
        
        assertFalse(monton.tieneSieteDeOros());
    }

    @Test
    public void testTieneSieteDeOrosDevuelveFalseSiHayOrosPeroNoElSiete() {
        ListaCartasMonton monton = new ListaCartasMonton();
        monton.agregarCarta(new Carta(Palo.Oros, 5));
        monton.agregarCarta(new Carta(Palo.Oros, 8));
        
        assertFalse(monton.tieneSieteDeOros());
    }

    @Test
    public void testTieneSieteDeOrosDevuelveTrueSiEstaPresente() {
        ListaCartasMonton monton = new ListaCartasMonton();
        monton.agregarCarta(new Carta(Palo.Copas, 7));
        monton.agregarCarta(new Carta(Palo.Oros, 7));
        
        assertTrue(monton.tieneSieteDeOros());
    }
}