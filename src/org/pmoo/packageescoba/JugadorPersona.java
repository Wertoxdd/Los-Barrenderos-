package org.pmoo.packageescoba;

import java.util.Iterator;
import java.util.ArrayList;

public class JugadorPersona extends Jugador{
	// atributos -> heredados
	
	// constructora
	public JugadorPersona() {
		super("Jugador 1");
	}
	
	// METODOS (propios)
	
	private Iterator<Carta> getIterador(){
		return this.getMano().getIterador();
	}
 	
	public Carta elegirCarta() {
		System.out.println("\nTu mano: ");// \n es siguiente linea antes de escribir (por si las mosquillas)
		return Teclado.leerCarta(getMano());	
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
