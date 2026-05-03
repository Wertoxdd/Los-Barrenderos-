package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;

public class ListaJugadoresTest {

    @Test
    public void testAgregarJugadorAumentaTamano() {
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        lj.resetear();
        lj.agregarJugador(new JugadorPersona("Test1"));
        lj.agregarJugador(new JugadorIA());
        assertEquals(2, lj.tamaño());
    }

    @Test
    public void testObtenerJugadorDevuelveElJugadorCorrecto() {
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        lj.resetear();
        Jugador j1 = new JugadorPersona("Alex");
        lj.agregarJugador(j1);
        assertEquals(j1, lj.obtenerJugador(0));
    }

    @Test
    public void testTodosConSusManosVacias() {
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        lj.resetear();
        Jugador j1 = new JugadorPersona("Alex");
        j1.recibirCarta(new Carta(Palo.Oros, 5));
        lj.agregarJugador(j1);
        assertFalse(lj.todosConSusManosVacias());
    }

    @Test
    public void testEmpatePeligrosoEnOrosDevuelveNull() {
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        lj.resetear();
        
        Jugador j1 = new JugadorPersona("Alex");
        Jugador j2 = new JugadorPersona("Javi");
        
        // Empate tÃ©cnico: Ambos tienen 1 oro
        j1.getMonton().agregarCarta(new Carta(Palo.Oros, 1));
        j2.getMonton().agregarCarta(new Carta(Palo.Oros, 7));
        
        lj.agregarJugador(j1);
        lj.agregarJugador(j2);
        
        assertNull("En caso de empate en oros, el ganador debe ser null", lj.jugadorConMasOros());
    }
}
