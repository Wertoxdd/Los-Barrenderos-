package org.pmoo.packageescoba;
import static org.junit.Assert.*;
import org.junit.Test;
public class MazoTest {

    @Test
    public void testGetMazoDevuelveLaMismaInstancia() {
        Mazo mazo1 = Mazo.getMazo();
        Mazo mazo2 = Mazo.getMazo();
        assertSame(mazo1, mazo2);
    }

    @Test
    public void testGenerarMazoCreaCuarentaCartas() {
        Mazo mazo = Mazo.getMazo();
        mazo.resetear();
        mazo.generarMazo();
        assertEquals(40, mazo.tamaño());
    }

    @Test
    public void testResetearVaciaElMazo() {
        Mazo mazo = Mazo.getMazo();
        mazo.resetear();
        mazo.generarMazo();
        mazo.resetear();
        assertTrue(mazo.estaVacio());
        assertEquals(0, mazo.tamaño());
    }

    @Test
    public void testEstaVacioDevuelveFalseDespuesDeGenerar() {
        Mazo mazo = Mazo.getMazo();
        mazo.resetear();
        mazo.generarMazo();
        assertFalse(mazo.estaVacio());
    }

    @Test
    public void testDarCartaReduceElTamanoDelMazo() {
        Mazo mazo = Mazo.getMazo();
        mazo.resetear();
        mazo.generarMazo();
        mazo.darCarta();
        assertEquals(39, mazo.tamaño());
    }

    @Test
    public void testDarCartaDevuelveCartaNoNula() {
        Mazo mazo = Mazo.getMazo();
        mazo.resetear();
        mazo.generarMazo();
        assertNotNull(mazo.darCarta());
    }

    @Test
    public void testBarajarMazoMantieneLaCantidadDeCartas() {
        Mazo mazo = Mazo.getMazo();
        mazo.resetear();
        mazo.generarMazo();
        mazo.barajarMazo();
        assertEquals(40, mazo.tamaño());
    }
}
