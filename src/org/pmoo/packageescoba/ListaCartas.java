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
	protected ArrayList<Carta> getCartas(){
		return this.cartas;
	}
	
	protected Iterator<Carta> getIterador() {
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
	
	
	
	
	

	
	
}