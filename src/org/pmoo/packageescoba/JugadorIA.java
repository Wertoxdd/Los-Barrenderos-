package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class JugadorIA extends Jugador {
	
	// atributo del random
	private Random random;
	
	// constructora
	public JugadorIA() {
		super("IA");
		random = new Random();
	}
	
	public Carta elegirCarta() {
		int i = random.nextInt(getMano().tamaño());
		Carta c = getMano().elegirCarta(i);
		System.out.println("\nLa IA juega: " + c.getValor() + " de " + c.getPalo());
		return c;
	}
	
	public ListaCartasMonton elegirCaptura(ListaCartasMesa mesa, Carta pCarta) {
	    ListaCartasAuxiliar cartasMesa = new ListaCartasAuxiliar();
	    Iterator<Carta> it = mesa.getIterador();
	    while (it.hasNext()) {
	        cartasMesa.agregarCarta(it.next());
	    }
	    buscarCombinaciones(cartasMesa, 0, 15 - pCarta.getValor(), new ListaCartasMonton());
	    return Teclado.leerCaptura(cartasMesa.getCombinaciones());
	}
	
}
