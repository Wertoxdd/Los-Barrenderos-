package org.pmoo.packageescoba;

import static org.junit.Assert.*;
import org.junit.Test;

public class TecladoTest {

    @Test
    public void testLeerNumeroDevuelveValorIntroducido() throws FormatoIncorrectoException {
        System.out.println("Escribe un 5 y pulsa Enter.");
        int resultado = Teclado.leerNumero("Introduce un numero: ");
        assertEquals(5, resultado);
    }

    @Test(expected = FormatoIncorrectoException.class)
    public void testLeerNumeroExcepcionLetra() throws FormatoIncorrectoException {
        System.out.println("Escribe la letra 'a' y pulsa Enter para forzar el error.");
        Teclado.leerNumero("Introduce un numero: ");
    }

    @Test
    public void testLeerCartaEligeCorrectamenteSegunEntrada() throws IndiceCartaInvalidoException, FormatoIncorrectoException {
        System.out.println("Escribe un 2 y pulsa Enter.");
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
        System.out.println("Selección automática. Por favor, pulsa Enter dos veces.");
        ListaCartasCombinaciones posiblesCombinaciones = new ListaCartasCombinaciones();
        ListaCartasMonton montonUnico = new ListaCartasMonton();
        montonUnico.agregarCarta(new Carta(Palo.Espadas, 10));
        posiblesCombinaciones.add(montonUnico);
        
        ListaCartasMonton resultado = Teclado.leerCaptura(posiblesCombinaciones);
        assertEquals(montonUnico, resultado);
    }

    @Test
    public void testLeerCapturaEligeCorrectamenteConVariasOpciones() throws IndiceCombinacionInvalidoException, FormatoIncorrectoException {
        System.out.println("Escribe un 2 y pulsa Enter tres veces.");
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
}