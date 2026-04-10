package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;

public class TecladoTest {

    @Test
    public void testLeerNumeroDevuelveValorIntroducido() {
        System.setIn(new ByteArrayInputStream("5\n".getBytes()));
        
        int resultado = Teclado.leerNumero("Introduce un numero: ");
        
        assertEquals(5, resultado);
    }

    @Test
    public void testLeerCartaEligeCorrectamenteSegunEntrada() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        
        ListaCartasMano mano = new ListaCartasMano();
        Carta carta1 = new Carta(Palo.Oros, 1);
        Carta carta2 = new Carta(Palo.Copas, 7);
        mano.agregarCarta(carta1);
        mano.agregarCarta(carta2);
        
        Carta seleccionada = Teclado.leerCarta(mano);
        
        assertEquals(carta2, seleccionada);
    }

    @Test
    public void testLeerCapturaDevuelveMontonUnicoDirectamente() {
        System.setIn(new ByteArrayInputStream("\n\n".getBytes()));
        
        ArrayList<ListaCartasMonton> posiblesCombinaciones = new ArrayList<ListaCartasMonton>();
        ListaCartasMonton montonUnico = new ListaCartasMonton();
        montonUnico.agregarCarta(new Carta(Palo.Espadas, 10));
        posiblesCombinaciones.add(montonUnico);
        
        ListaCartasMonton resultado = Teclado.leerCaptura(posiblesCombinaciones);
        
        assertEquals(montonUnico, resultado);
    }

    @Test
    public void testLeerCapturaEligeCorrectamenteConVariasOpciones() {
        System.setIn(new ByteArrayInputStream("2\n\n\n".getBytes()));
        
        ArrayList<ListaCartasMonton> posiblesCombinaciones = new ArrayList<ListaCartasMonton>();
        ListaCartasMonton monton1 = new ListaCartasMonton();
        ListaCartasMonton monton2 = new ListaCartasMonton();
        
        monton1.agregarCarta(new Carta(Palo.Oros, 5));
        monton2.agregarCarta(new Carta(Palo.Bastos, 5));
        
        posiblesCombinaciones.add(monton1);
        posiblesCombinaciones.add(monton2);
        
        ListaCartasMonton resultado = Teclado.leerCaptura(posiblesCombinaciones);
        
        assertEquals(monton2, resultado);
    }

    @Test
    public void testMostrarCartasEnHorizontalFuncionaSinErrores() {
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add(new Carta(Palo.Oros, 3));
        cartas.add(new Carta(Palo.Espadas, 4));
        
        Teclado.mostrarCartasEnHorizontal(cartas);
        
        assertTrue(true);
    }
}