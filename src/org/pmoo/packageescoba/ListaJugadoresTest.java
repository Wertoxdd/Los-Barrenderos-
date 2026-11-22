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
        
        j1.getMonton().agregarCarta(new Carta(Palo.Oros, 1));
        j2.getMonton().agregarCarta(new Carta(Palo.Oros, 7));
        
        lj.agregarJugador(j1);
        lj.agregarJugador(j2);
        
        assertNull("En caso de empate en oros, el ganador debe ser null", lj.jugadorConMasOros());
    }

    @Test
    public void testPrimerJugadorSinSietesNoAnulaAlGanador() {
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        lj.resetear();

        Jugador j1 = new JugadorPersona("Alex");  // 0 sietes
        Jugador j2 = new JugadorIA("IA 1");       // 2 sietes
        Jugador j3 = new JugadorIA("IA 2");       // 1 siete

        j2.getMonton().agregarCarta(new Carta(Palo.Copas, 7));
        j2.getMonton().agregarCarta(new Carta(Palo.Bastos, 7));
        j3.getMonton().agregarCarta(new Carta(Palo.Espadas, 7));

        lj.agregarJugador(j1);
        lj.agregarJugador(j2);
        lj.agregarJugador(j3);

        assertSame(j2, lj.jugadorConMasSietes());
    }

    @Test
    public void testEmpateAnteriorSeAnulaConUnNuevoMaximo() {
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        lj.resetear();

        Jugador j1 = new JugadorPersona("Alex");
        Jugador j2 = new JugadorIA("IA 1");
        Jugador j3 = new JugadorIA("IA 2");

        // j1 y j2 empatan a 1 carta, pero j3 tiene 2
        j1.getMonton().agregarCarta(new Carta(Palo.Copas, 1));
        j2.getMonton().agregarCarta(new Carta(Palo.Copas, 2));
        j3.getMonton().agregarCarta(new Carta(Palo.Copas, 3));
        j3.getMonton().agregarCarta(new Carta(Palo.Copas, 4));

        lj.agregarJugador(j1);
        lj.agregarJugador(j2);
        lj.agregarJugador(j3);

        assertSame(j3, lj.jugadorConMasCartas());
    }
}
