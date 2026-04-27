package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;

public class JugadorPersonaTest {

    @Test
    public void testConstructorAsignaNombreJugadorUno() {
        JugadorPersona alex = new JugadorPersona("alex");
        assertEquals("Jugador 1", alex.getNombre());
    }

    @Test
    public void testManoNoTieneElementosAlInicio() {
        JugadorPersona ahmed = new JugadorPersona("ahmed");
        assertEquals(0, ahmed.getMano().tamaño());
    }

    @Test
    public void testManoRecibeLasCartas() {
        JugadorPersona samuel = new JugadorPersona("samuel");
        Carta cartaOros = new Carta(Palo.Oros, 1);
        Carta cartaCopas = new Carta(Palo.Copas, 7);
        
        samuel.recibirCarta(cartaOros);
        samuel.recibirCarta(cartaCopas);
        
        assertEquals(2, samuel.getMano().tamaño());
        assertEquals(cartaOros, samuel.getMano().obtenerCarta(0));
        assertEquals(cartaCopas, samuel.getMano().obtenerCarta(1));
    }

    @Test
    public void testManoVaciaDevuelveTrueAlCrearJugador() {
        JugadorPersona mikel = new JugadorPersona("mikel");
        assertTrue(mikel.manoVacia());
    }

    @Test
    public void testManoVaciaDevuelveFalseAlRecibirCarta() {
        JugadorPersona jon = new JugadorPersona("jon");
        jon.recibirCarta(new Carta(Palo.Bastos, 3));
        assertFalse(jon.manoVacia());
    }

    @Test
    public void testObtenerEscobasIniciaEnCeroYSumaCorrectamente() {
        JugadorPersona alex = new JugadorPersona("alex");
        assertEquals(0, alex.obtenerEscobas());
        
        alex.añadirEscoba();
        alex.añadirEscoba();
        
        assertEquals(2, alex.obtenerEscobas());
    }

    @Test
    public void testAgregarCapturadasSumaAlTotalDeCartas() {
        JugadorPersona ahmed = new JugadorPersona("ahmed");
        ListaCartasMonton montonCapturado = new ListaCartasMonton();
        montonCapturado.agregarCarta(new Carta(Palo.Espadas, 4));
        montonCapturado.agregarCarta(new Carta(Palo.Copas, 5));
        
        ahmed.agregarCapturadas(montonCapturado);
        
        assertEquals(2, ahmed.totalCartas());
    }

    @Test
    public void testTotalOrosDelJugadorSumaCorrectamente() {
        JugadorPersona samuel = new JugadorPersona("samuel");
        ListaCartasMonton montonCapturado = new ListaCartasMonton();
        montonCapturado.agregarCarta(new Carta(Palo.Oros, 4));
        montonCapturado.agregarCarta(new Carta(Palo.Copas, 5));
        montonCapturado.agregarCarta(new Carta(Palo.Oros, 2));
        
        samuel.agregarCapturadas(montonCapturado);
        
        assertEquals(2, samuel.totalOros());
    }

    @Test
    public void testTotalSietesDelJugadorSumaCorrectamente() {
        JugadorPersona jon = new JugadorPersona("jon");
        ListaCartasMonton montonCapturado = new ListaCartasMonton();
        montonCapturado.agregarCarta(new Carta(Palo.Copas, 7));
        montonCapturado.agregarCarta(new Carta(Palo.Espadas, 7));
        montonCapturado.agregarCarta(new Carta(Palo.Oros, 3));
        
        jon.agregarCapturadas(montonCapturado);
        
        assertEquals(2, jon.totalSietes());
    }

    @Test
    public void testTieneSieteDeOrosElJugadorDevuelveTrueSiLoCapturo() {
        JugadorPersona mikel = new JugadorPersona("mikel");
        ListaCartasMonton montonCapturado = new ListaCartasMonton();
        montonCapturado.agregarCarta(new Carta(Palo.Oros, 7));
        montonCapturado.agregarCarta(new Carta(Palo.Bastos, 2));
        
        mikel.agregarCapturadas(montonCapturado);
        
        assertTrue(mikel.tieneSieteDeOros());
    }
    
    @Test
    public void testTieneSieteDeOrosElJugadorDevuelveFalseSiNoLoTiene() {
        JugadorPersona alex = new JugadorPersona("alex");
        ListaCartasMonton montonCapturado = new ListaCartasMonton();
        montonCapturado.agregarCarta(new Carta(Palo.Copas, 7));
        montonCapturado.agregarCarta(new Carta(Palo.Oros, 6));
        
        alex.agregarCapturadas(montonCapturado);
        
        assertFalse(alex.tieneSieteDeOros());
    }
}