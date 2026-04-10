package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;

public class JugadorIATest {

    @Test
    public void testConstructorAsignaNombreIA() {
        JugadorIA ia = new JugadorIA();
        assertEquals("IA", ia.getNombre());
    }

    @Test
    public void testElegirCartaDevuelveCartaNoNula() {
        JugadorIA ia = new JugadorIA();
        ia.recibirCarta(new Carta(Palo.Oros, 5));
        ia.recibirCarta(new Carta(Palo.Copas, 3));
        Carta resultado = ia.elegirCarta();
        assertNotNull(resultado);
    }

    @Test
    public void testElegirCartaReduceTamanoManoPorUno() {
        JugadorIA ia = new JugadorIA();
        ia.recibirCarta(new Carta(Palo.Oros, 1));
        ia.recibirCarta(new Carta(Palo.Copas, 4));
        ia.recibirCarta(new Carta(Palo.Espadas, 6));
        ia.elegirCarta();
        assertFalse(ia.manoVacia());
    }

    @Test
    public void testElegirCapturaDevuelveListaNoNula() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Copas, 8));
        Carta cartaJugada = new Carta(Palo.Oros, 7);
        ListaCartasMonton resultado = ia.elegirCaptura(mesa, cartaJugada);
        assertNotNull(resultado);
    }

    @Test
    public void testElegirCapturaSumaQuinceConCartaJugada() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Copas, 8));
        Carta cartaJugada = new Carta(Palo.Oros, 7);
        ListaCartasMonton resultado = ia.elegirCaptura(mesa, cartaJugada);
        int sumaCaptura = 0;
        java.util.Iterator<Carta> it = resultado.getIterador();
        while (it.hasNext()) {
            sumaCaptura += it.next().getValor();
        }
        assertEquals(15 - cartaJugada.getValor(), sumaCaptura);
    }

    @Test
    public void testElegirCartaConUnaCartaEnManoDevuelveEsaCarta() {
        JugadorIA ia = new JugadorIA();
        Carta unica = new Carta(Palo.Bastos, 6);
        ia.recibirCarta(unica);
        Carta resultado = ia.elegirCarta();
        assertEquals(6, resultado.getValor());
        assertEquals(Palo.Bastos, resultado.getPalo());
    }

    @Test
    public void testElegirCartaConUnaCartaDejaManonVacia() {
        JugadorIA ia = new JugadorIA();
        ia.recibirCarta(new Carta(Palo.Espadas, 2));
        ia.elegirCarta();
        assertTrue(ia.manoVacia());
    }

    @Test
    public void testElegirCapturaConUnaCartaEnMesaQueCompletaQuince() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Oros, 10));
        Carta cartaJugada = new Carta(Palo.Copas, 5);
        ListaCartasMonton resultado = ia.elegirCaptura(mesa, cartaJugada);
        assertEquals(1, resultado.tamaño());
        assertEquals(10, resultado.getIterador().next().getValor());
    }

    @Test
    public void testElegirCapturaConDosCartasEnMesaQueSumanObjetivo() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Oros, 6));
        mesa.agregarCarta(new Carta(Palo.Copas, 4));
        Carta cartaJugada = new Carta(Palo.Espadas, 5);
        ListaCartasMonton resultado = ia.elegirCaptura(mesa, cartaJugada);
        int sumaCaptura = 0;
        java.util.Iterator<Carta> it = resultado.getIterador();
        while (it.hasNext()) {
            sumaCaptura += it.next().getValor();
        }
        assertEquals(10, sumaCaptura);
    }

    @Test
    public void testElegirCapturaVariasCombinacionesDevuelvePrimera() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Oros, 9));
        mesa.agregarCarta(new Carta(Palo.Copas, 5));
        mesa.agregarCarta(new Carta(Palo.Espadas, 4));
        Carta cartaJugada = new Carta(Palo.Bastos, 6);
        ListaCartasMonton resultado = ia.elegirCaptura(mesa, cartaJugada);
        assertNotNull(resultado);
        int sumaCaptura = 0;
        java.util.Iterator<Carta> it = resultado.getIterador();
        while (it.hasNext()) {
            sumaCaptura += it.next().getValor();
        }
        assertEquals(9, sumaCaptura);
    }

    @Test
    public void testNombreIAEsExactamenteIA() {
        JugadorIA ia = new JugadorIA();
        assertFalse(ia.getNombre().equalsIgnoreCase("ia") && !ia.getNombre().equals("IA"));
        assertEquals("IA", ia.getNombre());
    }

    @Test
    public void testElegirCartaVariasVecesVaciaLaMano() {
        JugadorIA ia = new JugadorIA();
        ia.recibirCarta(new Carta(Palo.Oros, 1));
        ia.recibirCarta(new Carta(Palo.Copas, 2));
        ia.recibirCarta(new Carta(Palo.Bastos, 3));
        ia.elegirCarta();
        ia.elegirCarta();
        ia.elegirCarta();
        assertTrue(ia.manoVacia());
    }

    @Test
    public void testElegirCapturaNoIncluyeCartasQueSupernObjetivo() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Oros, 3));
        mesa.agregarCarta(new Carta(Palo.Copas, 10));
        mesa.agregarCarta(new Carta(Palo.Espadas, 2));
        Carta cartaJugada = new Carta(Palo.Bastos, 10);
        ListaCartasMonton resultado = ia.elegirCaptura(mesa, cartaJugada);
        int sumaCaptura = 0;
        java.util.Iterator<Carta> it = resultado.getIterador();
        while (it.hasNext()) {
            sumaCaptura += it.next().getValor();
        }
        assertTrue(sumaCaptura <= 5);
    }
}