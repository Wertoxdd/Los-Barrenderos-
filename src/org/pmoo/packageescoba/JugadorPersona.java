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
<<<<<<< HEAD
	    
	    buscarCombinaciones(cartasMesa, 0, 15 - pCarta.getValor(), new ListaCartasMonton(), posiblesCombinaciones);
	    
	    // Siempre pasa por Teclado para mostrar las combinaciones
	    return Teclado.leerCaptura(posiblesCombinaciones);
	}
	
	
	// para evitar un codigo redundante y excesivo lleno de iterators, se usa la recursividad.
	private void buscarCombinaciones(ArrayList<Carta> pLista, int i, int objetivo, ListaCartasMonton listaActual, ArrayList<ListaCartasMonton> posiblesCombinaciones) {
		
		if (objetivo == 0) {
			posiblesCombinaciones.add(listaActual);
			return;
		}
		
		if (i >= pLista.size()) return;
		
		Carta c = pLista.get(i);		
		if (c.getValor() <= objetivo) {
			ListaCartasMonton nuevaLista = new ListaCartasMonton();//Hacer New ListaCartas Auxiliar
			nuevaLista.agregarCartas(listaActual);
			nuevaLista.agregarCarta(c);
			buscarCombinaciones(pLista, i+1, objetivo - c.getValor(), nuevaLista, posiblesCombinaciones);
		}
		
		buscarCombinaciones(pLista, i+1, objetivo, listaActual, posiblesCombinaciones);
		
=======
	    buscarCombinaciones(cartasMesa, 0, 15 - pCarta.getValor(), new ListaCartasMonton());
	    return Teclado.leerCaptura(cartasMesa.getCombinaciones());
>>>>>>> branch 'master' of https://github.com/Wertoxdd/Los-Barrenderos-.git
	}
}
