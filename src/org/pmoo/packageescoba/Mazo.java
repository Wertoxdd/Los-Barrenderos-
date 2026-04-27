package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Random;

public class Mazo extends ListaCartas {
	
	private static Mazo miMazo = null;
	
	private Mazo () {
		super();
	}
	
	public static Mazo getMazo() {
		if (miMazo == null) {
			miMazo = new Mazo();
		}
		return miMazo;
	}
	
	public void generarMazo() {
		int i;
		
		// OROS
		i = 1;
		while (i <= 10) {
			this.agregarCarta(new Carta(Palo.Oros, i));
			i++;
		}
		
		// COPAS
		i = 1;
		while (i <= 10) {
			this.agregarCarta(new Carta(Palo.Copas, i));
			i++;
		}
		
		// ESPADAS
		i = 1;
		while (i <= 10) {
			this.agregarCarta(new Carta(Palo.Espadas, i));
			i++;
		}
		
		// BASTOS
		i = 1;
		while (i <= 10) {
			this.agregarCarta(new Carta(Palo.Bastos, i));
			i++;
		}
	}
	
	public void barajarMazo() {
		Random random = new Random();
		ArrayList<Carta> temp = new ArrayList<Carta>();
		int total = this.tamaño();
		for (int i = 0; i < total; i++) {
			temp.add(this.eliminarCarta(0));
		}
		
		while (!temp.isEmpty()) {
			int r = random.nextInt(temp.size());
			this.agregarCarta(temp.remove(r));
		}
	}
	
	public Carta darCarta() {
		return this.eliminarCarta(this.tamaño() - 1);
	}
	
	public boolean estaVacio() {
		return this.tamaño() == 0;
	}
}
