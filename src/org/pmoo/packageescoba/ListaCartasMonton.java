package org.pmoo.packageescoba;

import java.util.Iterator;

public class ListaCartasMonton extends ListaCartas{
	// atributos -> heredados
	
	// constructora
	public ListaCartasMonton() {
		super();
	}
	
	// METODOS (propios)
	
	public void agregarCartas(ListaCartasMonton pLista) {
	    Iterator<Carta> it = pLista.getIterador();  // itera sobre pLista
	    while (it.hasNext()) {
	        Carta c = it.next();
	        this.agregarCarta(c);  // añade a this
	    }
	}
	
	public int contarOros() {
		Iterator<Carta> it = getIterador();
		int cont = 0;
		while (it.hasNext()) {
			Carta c = it.next();
			if (c.getPalo().equals(Palo.Oros)) {
				cont++;
			}
		} 
		
		return cont;
	}
	
	public int contarSietes() {
		Iterator<Carta> it = getIterador();
		int cont = 0;
		while (it.hasNext()) {
			Carta c = it.next();
			if (c.getValor() == 7) {
				cont++;
			}
		}
		
		return cont;
	}
	
	public boolean tieneSieteDeOros() {
		Iterator<Carta> it = getIterador();
		while (it.hasNext()){
			Carta c = it.next();
			if (c.getPalo().equals(Palo.Oros) && c.getValor() == 7) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
}
