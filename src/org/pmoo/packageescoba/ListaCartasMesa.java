package org.pmoo.packageescoba;

import java.util.Iterator;
import java.util.ArrayList;

public class ListaCartasMesa extends ListaCartas{
	// atributos -> heredados
	
	// constructora 
	public ListaCartasMesa() {
		super();
	}
	
	// METODOS (propios)
	
	public void eliminarCarta(Carta pCarta) {
		getCartas().remove(pCarta);
	}
	
	public boolean estaVacia() {
		return this.getCartas().size() == 0;
	}
	
	public boolean sumaQuince(Carta pCarta) {
		int objetivo = 15 - pCarta.getValor();
		ArrayList<Carta> listaAux = new ArrayList<Carta>();
		Iterator<Carta> it = getIterador();
		while (it.hasNext()) {
			Carta c = it.next();
			listaAux.add(c);
		}
		
		return puedeSumarQuince(listaAux, objetivo, 0, 0);	// Empezar la recursividad	
	}
	
	private boolean puedeSumarQuince(ArrayList<Carta> pLista, int objetivo, int indice, int sumaActual) {
		// Si se efectua la suma.
		if (sumaActual == objetivo) return true;
		
		// Si superamos la suma o no hay más cartas que recorrer.
		if (sumaActual > objetivo || indice >= tamaño()) return false;
		
		// Incluir la carta actual (recursividad)
		if (puedeSumarQuince(pLista, objetivo, indice + 1, sumaActual + pLista.get(indice).getValor())) return true;
		
		
		// Excluir la carta actual
		return puedeSumarQuince(pLista, objetivo, indice + 1, sumaActual);		
	}
}
