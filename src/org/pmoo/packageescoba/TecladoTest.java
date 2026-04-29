package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayInputStream;

public class TecladoTest {

    @Test
    public void testLeerNumeroDevuelveValorIntroducido() throws FormatoIncorrectoException {
        System.setIn(new ByteArrayInputStream("5\n".getBytes()));
        
        int resultado = Teclado.leerNumero("Introduce un numero: ");
        
        assertEquals(5, resultado);
    }

    @Test(expected = FormatoIncorrectoException.class)
    public void testLeerNumeroLanzaExcepcionConLetra() throws FormatoIncorrectoException {
        System.setIn(new ByteArrayInputStream("a\n".getBytes()));
        Teclado.leerNumero("Introduce un numero: ");
    }

    @Test
    public void testLeerCartaEligeCorrectamenteSegunEntrada() throws IndiceCartaInvalidoException, FormatoIncorrectoException {
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
    public void testLeerCapturaDevuelveMontonUnicoDirectamente() throws IndiceCombinacionInvalidoException, FormatoIncorrectoException {
        System.setIn(new ByteArrayInputStream("\n\n".getBytes()));
        
        ListaCartasCombinaciones posiblesCombinaciones = new ListaCartasCombinaciones();
        ListaCartasMonton montonUnico = new ListaCartasMonton();
        montonUnico.agregarCarta(new Carta(Palo.Espadas, 10));
        posiblesCombinaciones.add(montonUnico);
        
        ListaCartasMonton resultado = Teclado.leerCaptura(posiblesCombinaciones);
        
        assertEquals(montonUnico, resultado);
    }

    @Test
    public void testLeerCapturaEligeCorrectamenteConVariasOpciones() throws IndiceCombinacionInvalidoException, FormatoIncorrectoException {
        System.setIn(new ByteArrayInputStream("2\n\n\n".getBytes()));
        
        ListaCartasCombinaciones posiblesCombinaciones = new ListaCartasCombinaciones();
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
        ListaCartasMonton cartas = new ListaCartasMonton();
        cartas.agregarCarta(new Carta(Palo.Oros, 3));
        cartas.agregarCarta(new Carta(Palo.Espadas, 4));
        
        Teclado.mostrarCartasEnHorizontal(cartas);
        
        assertTrue(true);
    }
}