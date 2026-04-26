package org.pmoo.packageescoba;

import java.util.Iterator;
import java.util.ArrayList;

public class ListaCartas {
	private ArrayList<Carta> cartas;
	
	protected ListaCartas() {
		this.cartas = new ArrayList<Carta>();
	}
	
	protected ArrayList<Carta> getCartas(){
		return cartas;
	}
	
	public void agregarCarta(Carta pCarta) {
		this.cartas.add(pCarta);
	}
	
	public int tamaño() {
		return this.cartas.size();
	}
	
	// ✅ PRIVATE - Solo accesible dentro de la clase y subclases
	private Iterator<Carta> getIterador(){
		return this.cartas.iterator();
	}
	
	// ✅ Método público para copiar cartas a otra lista (encapsula el iterador)
	public void copiarA(ListaCartas destino) {
		Iterator<Carta> it = getIterador();
		while (it.hasNext()) {
			destino.agregarCarta(it.next());
		}
	}
	
	// ✅ Método público para obtener carta por posición
	public Carta obtenerCarta(int posicion) {
		if (posicion >= 0 && posicion < cartas.size()) {
			return cartas.get(posicion);
		}
		return null;
	}
	
	@Override
	public String toString() {
		String resultado = "";
		Iterator<Carta> it = getIterador();
		while (it.hasNext()) {
			resultado += it.next().toString();
			if (it.hasNext()) resultado += " + ";
		}
		return resultado;
	}
}
