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
    public void testElegirCarta() {
        JugadorIA ia = new JugadorIA();
        ia.recibirCarta(new Carta(Palo.Oros, 5));
        ia.recibirCarta(new Carta(Palo.Copas, 3));
        assertNotNull(ia.elegirCarta());
    }

    @Test
    public void testElegirCartaConUnaCartaEnMano() {
        JugadorIA ia = new JugadorIA();
        Carta unica = new Carta(Palo.Bastos, 6);
        ia.recibirCarta(unica);
        Carta resultado = ia.elegirCarta();
        assertEquals(6, resultado.getValor());
        assertEquals(Palo.Bastos, resultado.getPalo());
    }

    @Test
    public void testElegirCartaConUnaCartaDejaLaManoVacia() {
        JugadorIA ia = new JugadorIA();
        ia.recibirCarta(new Carta(Palo.Espadas, 2));
        ia.elegirCarta();
        assertTrue(ia.manoVacia());
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
    public void testElegirCapturaDevuelveListaNoNula() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Copas, 8));
        assertNotNull(ia.elegirCaptura(mesa, new Carta(Palo.Oros, 7)));
    }

    @Test
    public void testElegirCapturaSumaQuinceConCartaJugada() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Copas, 8));
        Carta cartaJugada = new Carta(Palo.Oros, 7);
        ListaCartasMonton resultado = ia.elegirCaptura(mesa, cartaJugada);
        int suma = 0;
        for (int i = 0; i < resultado.tamaño(); i++) {
            suma += resultado.obtenerCarta(i).getValor();
        }
        assertEquals(8, suma);
    }

    @Test
    public void testElegirCapturaConUnaCartaEnMesaQueCompletaQuince() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Oros, 10));
        Carta cartaJugada = new Carta(Palo.Copas, 5);
        ListaCartasMonton resultado = ia.elegirCaptura(mesa, cartaJugada);
        assertEquals(1, resultado.tamaño());
        assertEquals(10, resultado.obtenerCarta(0).getValor());
    }

    @Test
    public void testElegirCapturaConDosCartasEnMesaQueSumanObjetivo() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Oros, 6));
        mesa.agregarCarta(new Carta(Palo.Copas, 4));
        Carta cartaJugada = new Carta(Palo.Espadas, 5);
        ListaCartasMonton resultado = ia.elegirCaptura(mesa, cartaJugada);
        int suma = 0;
        for (int i = 0; i < resultado.tamaño(); i++) {
            suma += resultado.obtenerCarta(i).getValor();
        }
        assertEquals(10, suma);
    }

    @Test
    public void testElegirCapturaIgnoraCartasQueSupenanElObjetivo() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        // objetivo = 15 - 10 = 5; solo 3+2 suman 5, el 10 de Copas no puede entrar
        mesa.agregarCarta(new Carta(Palo.Oros, 3));
        mesa.agregarCarta(new Carta(Palo.Copas, 10));
        mesa.agregarCarta(new Carta(Palo.Espadas, 2));
        Carta cartaJugada = new Carta(Palo.Bastos, 10);
        ListaCartasMonton resultado = ia.elegirCaptura(mesa, cartaJugada);
        int suma = 0;
        for (int i = 0; i < resultado.tamaño(); i++) {
            suma += resultado.obtenerCarta(i).getValor();
        }
        assertEquals(5, suma);
    }

    @Test
    public void testConstructorConNombre() {
        JugadorIA ia = new JugadorIA("IA 2");
        assertEquals("IA 2", ia.getNombre());
    }

    @Test
    public void testElegirCartaConMesaPrefiereLaEscoba() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Copas, 4));
        mesa.agregarCarta(new Carta(Palo.Bastos, 6));
        // Con el 5 se lleva toda la mesa (escoba); con el 9 solo el 6
        ia.recibirCarta(new Carta(Palo.Espadas, 9));
        ia.recibirCarta(new Carta(Palo.Espadas, 5));
        assertEquals(5, ia.elegirCarta(mesa).getValor());
    }

    @Test
    public void testElegirCapturaPrefiereElSieteDeOros() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Copas, 7));
        mesa.agregarCarta(new Carta(Palo.Oros, 7));
        mesa.agregarCarta(new Carta(Palo.Bastos, 1));
        // Con un 8 puede llevarse cualquiera de los dos sietes
        ListaCartasMonton resultado = ia.elegirCaptura(mesa, new Carta(Palo.Espadas, 8));
        assertEquals(1, resultado.tamaño());
        assertEquals(Palo.Oros, resultado.obtenerCarta(0).getPalo());
    }

    @Test
    public void testElegirCartaSinCapturaNoSeDescartaDelSieteDeOros() {
        JugadorIA ia = new JugadorIA();
        ListaCartasMesa mesa = new ListaCartasMesa();
        mesa.agregarCarta(new Carta(Palo.Copas, 10)); // nada suma 15 con estas cartas
        ia.recibirCarta(new Carta(Palo.Oros, 7));
        ia.recibirCarta(new Carta(Palo.Bastos, 2));
        Carta jugada = ia.elegirCarta(mesa);
        assertEquals(Palo.Bastos, jugada.getPalo());
        assertEquals(2, jugada.getValor());
    }
}
