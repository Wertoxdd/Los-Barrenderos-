package org.pmoo.packageescoba;

import java.util.Iterator;
import java.util.ArrayList;

public class ListaCartasMesa extends ListaCartas {
	
	public ListaCartasMesa() {
		super();
	}
	
	public void eliminarCarta(Carta pCarta) {
		getCartas().remove(pCarta);
	}
	
	// ✅ PRIVATE - No exponer
	private Iterator<Carta> getIterador(){
		return super.getCartas().iterator();
	}
	
	public boolean estaVacia() {
		return this.getCartas().size() == 0;
	}
	
	public boolean sumaQuince(Carta pCarta) {
		int objetivo = 15 - pCarta.getValor();
		ArrayList<Carta> listaAux = new ArrayList<Carta>();
		Iterator<Carta> it = getIterador();  // Uso interno OK
		while (it.hasNext()) {
			Carta c = it.next();
			listaAux.add(c);
		}
		return puedeSumarQuince(listaAux, objetivo, 0, 0);
	}
	
	// ✅ Método público para copiar cartas a otra lista
	public void agregarCartas(ListaCartas destino) {
		Iterator<Carta> it = getIterador();  // Uso interno OK
		while (it.hasNext()) {
			destino.agregarCarta(it.next());
		}
	}
	
	// ✅ Método público para obtener carta por posición (heredado de ListaCartas)
	// Usar: obtenerCarta(int posicion)
	
	private boolean puedeSumarQuince(ArrayList<Carta> pLista, int objetivo, 
			int indice, int sumaActual) {
		if (sumaActual == objetivo) return true;
		if (sumaActual > objetivo || indice >= tamaño()) return false;
		
		if (puedeSumarQuince(pLista, objetivo, indice + 1, 
			sumaActual + pLista.get(indice).getValor())) return true;
		
		return puedeSumarQuince(pLista, objetivo, indice + 1, sumaActual);		
	}
}
