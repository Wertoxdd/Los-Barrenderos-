package org.pmoo.packageescoba;

import java.util.ArrayList;

public class Mazo {
	// atributos
	private ArrayList<Carta> mazo;
	private static Mazo miMazo = null;
	
	// constructora
	private Mazo() {
		this.mazo = new ArrayList<Carta>();
	}
	
	// Singleton
	private static Mazo getMazo() {
		if(miMazo == null) {
			miMazo = new Mazo();
		} 
		return miMazo;
	}
	
	public void repartirCartas(Jugador pJugador) {}
	
}
