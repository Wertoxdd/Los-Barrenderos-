package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;

public class JugadorTest {

    private Jugador crearJugador(String pNombre) {
        return new JugadorPersona(pNombre);
    }

    @Test
    public void testGetNombreDevuelveNombreDelConstructor() {
        Jugador jugador = crearJugador("Alex");
        assertEquals("Alex", jugador.getNombre());
    }

    @Test
    public void testManoVaciaDevuelveTrueAlCrearJugador() {
        Jugador jugador = crearJugador("Samuel");
        assertTrue(jugador.manoVacia());
    }

    @Test
    public void testRecibirCartaHaceQueManoNoEsteVacia() {
        Jugador jugador = crearJugador("Mikel");
        Carta carta = new Carta(Palo.Oros, 3);
        jugador.recibirCarta(carta);
        assertFalse(jugador.manoVacia());
    }

    @Test
    public void testObtenerEscobasDevuelveCeroAlCrearJugador() {
        Jugador jugador = crearJugador("Jon");
        assertEquals(0, jugador.obtenerEscobas());
    }

    @Test
    public void testAnadirEscobaIncrementaContadorEnUno() {
        Jugador jugador = crearJugador("Ahmed");
        jugador.añadirEscoba();
        assertEquals(1, jugador.obtenerEscobas());
    }

    @Test
    public void testTotalCartasDevuelveCeroCuandoMontonVacio() {
        Jugador jugador = crearJugador("Alex");
        assertEquals(0, jugador.totalCartas());
    }

    @Test
    public void testAgregarCapturasIncrementaTotalCartas() {
        Jugador jugador = crearJugador("Samuel");
        ListaCartasMonton lista = new ListaCartasMonton();
        lista.agregarCarta(new Carta(Palo.Copas, 5));
        lista.agregarCarta(new Carta(Palo.Bastos, 6));
        jugador.agregarCapturadas(lista);
        assertEquals(2, jugador.totalCartas());
    }

    @Test
    public void testTotalOrosDevuelveCeroCuandoMontonVacio() {
        Jugador jugador = crearJugador("Mikel");
        assertEquals(0, jugador.totalOros());
    }

    @Test
    public void testTotalSietesDevuelveCeroCuandoMontonVacio() {
        Jugador jugador = crearJugador("Jon");
        assertEquals(0, jugador.totalSietes());
    }

    @Test
    public void testTieneSieteDeOrosDevuelveFalseCuandoMontonVacio() {
        Jugador jugador = crearJugador("Ahmed");
        assertFalse(jugador.tieneSieteDeOros());
    }

    @Test
    public void testAnadirEscobaVariasVecesAcumulaCorrectamente() {
        Jugador jugador = crearJugador("Alex");
        jugador.añadirEscoba();
        jugador.añadirEscoba();
        jugador.añadirEscoba();
        assertEquals(3, jugador.obtenerEscobas());
    }

    @Test
    public void testRecibirVariasCartasManoSigueNoVacia() {
        Jugador jugador = crearJugador("Samuel");
        jugador.recibirCarta(new Carta(Palo.Oros, 1));
        jugador.recibirCarta(new Carta(Palo.Copas, 2));
        jugador.recibirCarta(new Carta(Palo.Espadas, 3));
        assertFalse(jugador.manoVacia());
    }

    @Test
    public void testTotalOrosDevuelveNumeroCorrectoConOrosEnMonton() {
        Jugador jugador = crearJugador("Mikel");
        ListaCartasMonton lista = new ListaCartasMonton();
        lista.agregarCarta(new Carta(Palo.Oros, 1));
        lista.agregarCarta(new Carta(Palo.Oros, 3));
        lista.agregarCarta(new Carta(Palo.Copas, 5));
        jugador.agregarCapturadas(lista);
        assertEquals(2, jugador.totalOros());
    }

    @Test
    public void testTotalSietesDevuelveNumeroCorrectoConSietesEnMonton() {
        Jugador jugador = crearJugador("Jon");
        ListaCartasMonton lista = new ListaCartasMonton();
        lista.agregarCarta(new Carta(Palo.Oros, 7));
        lista.agregarCarta(new Carta(Palo.Copas, 7));
        lista.agregarCarta(new Carta(Palo.Espadas, 3));
        jugador.agregarCapturadas(lista);
        assertEquals(2, jugador.totalSietes());
    }

    @Test
    public void testTieneSieteDeOrosDevuelveTrueCuandoEstaEnMonton() {
        Jugador jugador = crearJugador("Ahmed");
        ListaCartasMonton lista = new ListaCartasMonton();
        lista.agregarCarta(new Carta(Palo.Oros, 7));
        jugador.agregarCapturadas(lista);
        assertTrue(jugador.tieneSieteDeOros());
    }

    @Test
    public void testTotalCartasTrasDosLlamadasAgregarCapturadas() {
        Jugador jugador = crearJugador("Alex");
        ListaCartasMonton lista1 = new ListaCartasMonton();
        lista1.agregarCarta(new Carta(Palo.Copas, 1));
        lista1.agregarCarta(new Carta(Palo.Copas, 2));
        ListaCartasMonton lista2 = new ListaCartasMonton();
        lista2.agregarCarta(new Carta(Palo.Bastos, 3));
        jugador.agregarCapturadas(lista1);
        jugador.agregarCapturadas(lista2);
        assertEquals(3, jugador.totalCartas());
    }

    @Test
    public void testDosJugadoresTienenNombresIndependientes() {
        Jugador jugador1 = crearJugador("Samuel");
        Jugador jugador2 = crearJugador("Mikel");
        assertEquals("Samuel", jugador1.getNombre());
        assertEquals("Mikel", jugador2.getNombre());
    }

    @Test
    public void testEscobasDeUnJugadorNoAfectanAlOtro() {
        Jugador jugador1 = crearJugador("Jon");
        Jugador jugador2 = crearJugador("Ahmed");
        jugador1.añadirEscoba();
        jugador1.añadirEscoba();
        assertEquals(2, jugador1.obtenerEscobas());
        assertEquals(0, jugador2.obtenerEscobas());
    }

    @Test
    public void testTotalOrosYTotalSietesIndependientesEnMismoMonton() {
        Jugador jugador = crearJugador("Alex");
        ListaCartasMonton lista = new ListaCartasMonton();
        lista.agregarCarta(new Carta(Palo.Oros, 7));
        lista.agregarCarta(new Carta(Palo.Oros, 1));
        lista.agregarCarta(new Carta(Palo.Copas, 7));
        jugador.agregarCapturadas(lista);
        assertEquals(2, jugador.totalOros());
        assertEquals(2, jugador.totalSietes());
    }

    @Test
    public void testManoVaciaDevuelveFalseTrasVariasCartas() {
        Jugador jugador = crearJugador("Samuel");
        jugador.recibirCarta(new Carta(Palo.Espadas, 4));
        jugador.recibirCarta(new Carta(Palo.Bastos, 5));
        assertFalse(jugador.manoVacia());
    }
}