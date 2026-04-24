package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Iterator;

public class ListaJugadoresTest {

    @Test
    public void testConstructorIniciaTamanoCero() {
        ListaJugadores lista = new ListaJugadores();
        assertEquals(0, lista.tamaño());
    }

    @Test
    public void testAgregarJugadorAumentaTamano() {
        ListaJugadores lista = new ListaJugadores();
        lista.agregarJugador(new JugadorPersona());
        lista.agregarJugador(new JugadorIA());
        assertEquals(2, lista.tamaño());
    }

    @Test
    public void testObtenerJugadorDevuelveElJugadorCorrecto() {
        ListaJugadores lista = new ListaJugadores();
        Jugador jugador1 = new JugadorPersona();
        Jugador jugador2 = new JugadorIA();
        lista.agregarJugador(jugador1);
        lista.agregarJugador(jugador2);
        assertEquals(jugador1, lista.obtenerJugador(0));
        assertEquals(jugador2, lista.obtenerJugador(1));
    }

    @Test
    public void testGetIteradorRecorreLosJugadoresAgregados() {
        ListaJugadores lista = new ListaJugadores();
        Jugador jugador1 = new JugadorPersona();
        Jugador jugador2 = new JugadorIA();
        lista.agregarJugador(jugador1);
        lista.agregarJugador(jugador2);
        Iterator<Jugador> iterador = lista.getIterador();
        assertTrue(iterador.hasNext());
        assertEquals(jugador1, iterador.next());
        assertTrue(iterador.hasNext());
        assertEquals(jugador2, iterador.next());
        assertFalse(iterador.hasNext());
    }

    @Test
    public void testTodosConSusManosVaciasDevuelveTrueSiLaListaEstaVacia() {
        ListaJugadores lista = new ListaJugadores();
        assertTrue(lista.todosConSusManosVacias());
    }

    @Test
    public void testTodosConSusManosVaciasDevuelveTrueSiNingunoTieneCartas() {
        ListaJugadores lista = new ListaJugadores();
        lista.agregarJugador(new JugadorPersona());
        lista.agregarJugador(new JugadorIA());
        assertTrue(lista.todosConSusManosVacias());
    }

    @Test
    public void testTodosConSusManosVaciasDevuelveFalseSiUnJugadorTieneCartas() {
        ListaJugadores lista = new ListaJugadores();
        Jugador jugador1 = new JugadorPersona();
        Jugador jugador2 = new JugadorIA();
        jugador1.recibirCarta(new Carta(Palo.Oros, 5));
        lista.agregarJugador(jugador1);
        lista.agregarJugador(jugador2);
        assertFalse(lista.todosConSusManosVacias());
    }

    @Test
    public void testTodosConSusManosVaciasDevuelveFalseSiTodosLosJugadoresTienenCartas() {
        ListaJugadores lista = new ListaJugadores();
        Jugador jugador1 = new JugadorPersona();
        Jugador jugador2 = new JugadorIA();
        jugador1.recibirCarta(new Carta(Palo.Oros, 1));
        jugador2.recibirCarta(new Carta(Palo.Copas, 3));
        lista.agregarJugador(jugador1);
        lista.agregarJugador(jugador2);
        assertFalse(lista.todosConSusManosVacias());
    }
}
