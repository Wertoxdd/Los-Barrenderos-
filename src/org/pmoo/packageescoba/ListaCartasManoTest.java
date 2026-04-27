package org.pmoo.packageescoba;
import static org.junit.Assert.*;
import org.junit.Test;
public class ListaCartasManoTest {

    @Test
    public void testEstaVaciaDevuelveTrueAlCrearMano() {
        ListaCartasMano mano = new ListaCartasMano();
        assertTrue(mano.estaVacia());
    }

    @Test
    public void testEstaVaciaDevuelveFalseAlAgregarCarta() {
        ListaCartasMano mano = new ListaCartasMano();
        mano.agregarCarta(new Carta(Palo.Oros, 1));
        assertFalse(mano.estaVacia());
    }

    @Test
    public void testObtenerCartaDevuelveLaCartaCorrectaSinEliminarla() {
        ListaCartasMano mano = new ListaCartasMano();
        Carta carta = new Carta(Palo.Copas, 3);
        mano.agregarCarta(carta);
        assertEquals(carta, mano.consultarCarta(0));
        assertEquals(1, mano.tama�o());
    }

    @Test
    public void testElegirCartaEnPosicion0DevuelveYExtraePrimeraCarta() {
        ListaCartasMano mano = new ListaCartasMano();
        Carta carta1 = new Carta(Palo.Espadas, 4);
        Carta carta2 = new Carta(Palo.Bastos, 5);
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        Carta elegida = mano.elegirCarta(0);
        assertEquals(carta1, elegida);
        assertEquals(1, mano.tama�o());
        assertEquals(carta2, mano.consultarCarta(0));
    }

    @Test
    public void testElegirCartaEnPosicion1DevuelveYExtraeSegundaCarta() {
        ListaCartasMano mano = new ListaCartasMano();
        Carta carta1 = new Carta(Palo.Oros, 2);
        Carta carta2 = new Carta(Palo.Copas, 9);
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        Carta elegida = mano.elegirCarta(1);
        assertEquals(carta2, elegida);
        assertEquals(1, mano.tama�o());
        assertEquals(carta1, mano.consultarCarta(0));
    }

    @Test
    public void testEstaVaciaDevuelveTrueDespuesDeElegirTodasLasCartas() {
        ListaCartasMano mano = new ListaCartasMano();
        mano.agregarCarta(new Carta(Palo.Oros, 7));
        mano.elegirCarta(0);
        assertTrue(mano.estaVacia());
    }
}
