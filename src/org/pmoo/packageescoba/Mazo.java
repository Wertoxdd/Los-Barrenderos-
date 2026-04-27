package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Random;

public class Mazo {
	// atributos
	private ArrayList<Carta> baraja;
	private static Mazo miMazo = null;
	
	// constructora
	private Mazo () {
		this.baraja = new ArrayList<Carta >();
	}
	
	
	// Singleton
	public static Mazo getMazo() {
		if (miMazo == null) {
			miMazo = new Mazo();
		}
		return miMazo;
	}
	
	// METODOS
	
	public void generarMazo() {
		int i;
		
		// OROS
		i = 1;
		while (i <= 10) {
			baraja.add(new Carta(Palo.Oros, i));
			i++;
		}
		
		// COPAS
		i = 1;
		while (i <= 10) {
			baraja.add(new Carta(Palo.Copas, i));
			i++;
		}
		
		// ESPADAS
		i = 1;
		while (i <= 10) {
			baraja.add(new Carta(Palo.Espadas, i));
			i++;
		}
		
		// BASTOS
		i = 1;
		while (i <= 10) {
			baraja.add(new Carta(Palo.Bastos, i));
			i++;
		}
	}
	
	public void barajarMazo() {
		Random random = new Random();
		int i = baraja.size() - 1;
		while(i > 0) {
			int j = random.nextInt(i + 1); // nuevo indice para el random, que comprueba si hay una siguiente carta.
										   // si la hay la asigna.
			Carta aux = baraja.get(i); // Carta auxiliar para que no se pierda la carta.
			baraja.set(i, baraja.get(j)); // asigna en la posicion i la carta que esta en una posicion random.
			baraja.set(j,  aux); // asigna la carta que queda pendiente en la posicion auxiliar.
			i--;
		}
	}
	
	public Carta darCarta() {
		return baraja.remove(baraja.size()-1);
	}
		
	public boolean estaVacio() {
		return baraja.isEmpty();
	}
	
	public int tamaño() {
	    return baraja.size();
	}
	
	public void resetear() {
		baraja.clear();
	}
	
	
	
}
