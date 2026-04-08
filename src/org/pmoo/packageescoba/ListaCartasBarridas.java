package org.pmoo.packageescoba;

import java.util.Iterator;

public class ListaCartasBarridas extends ListaCartas {
	//Constructora
	public ListaCartasBarridas() {
		super();
	}
	//Métodos
	public boolean tieneSieteDeOros() {
		// devuelve un booleano que es true si la lista contiene el siete de Oros
		boolean loTiene= false;
		Carta miCarta = null;
		Iterator<Carta> itr=super.getIterador();
		while (itr.hasNext()&&!loTiene) {
			miCarta = itr.next();
			if (miCarta.getPalo()=="Oros"&&miCarta.getValor()==7) {
				loTiene=true;
			}
		}
		return loTiene;
	}
	public int numCartas() {
		return super.getCartas().size();
	}
	public int cuantosSietes() {
			// devuelve el numero de sietes de la lista
		int nSietes=0;
		Carta miCarta = null;
		Iterator<Carta> itr=super.getIterador();
		while (itr.hasNext()) {
			miCarta = itr.next();
			if (miCarta.getValor()==7) {
				nSietes++;
			}
		}
		return nSietes;
	}
	public int cuantosOros() {
		// devuelve el numero de oros de la lista
		int nOros=0;
		Carta miCarta = null;
		Iterator<Carta> itr=super.getIterador();
		while (itr.hasNext()) {
			miCarta = itr.next();
			if (miCarta.getPalo()=="Oros") {
				nOros++;
			}
		}
		return nOros;
	}
}

