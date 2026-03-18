package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaCartas {
	// atributos
	private ArrayList<Carta> cartas;

	// constructora
	public ListaCartas() {
		this.cartas = new ArrayList<Carta>();
	}
	
	// METODOS
	
	private Iterator<Carta> getIterador() {
		return this.cartas.iterator();
	}
	
	
	public void agregar(Carta pCarta) {
		this.cartas.add(pCarta);
	}
	
	
	public Carta obtenerCarta(int pos) {
		return this.cartas.get(pos);
	}
	
	
	public void eliminar(Carta pCarta) {
		this.cartas.remove(pCarta);
	}
	
	
	public int tamaño() {
		return this.cartas.size();
	}
	
	
	public boolean estaVacia() {
		return this.cartas.size() == 0;
	}
	
	
	public int cuantosOros() {
		Iterator<Carta> it = getIterador();
		int cont = 0;
		while(it.hasNext()) {
			Carta c = it.next();
			if (c.getPalo() == "oros"){
					cont++;
			}
		}
		return cont;
	}
	
	
	public int cuantosSietes() {
		Iterator<Carta> it = getIterador();
		int cont = 0;
		while(it.hasNext()) {
			Carta c = it.next();
			if (c.getValor() == 7) {
				cont++;
			}
		}
		return cont;
	}	
	
	
	public boolean tieneSieteDeOros() {
		Iterator<Carta> it = getIterador();
		while(it.hasNext()) {
			Carta c = it.next();
			if(c.getPalo() == "oros" && c.getValor() == 7) {
				return true;
			}
		}
		return false;
	}
	
	
}