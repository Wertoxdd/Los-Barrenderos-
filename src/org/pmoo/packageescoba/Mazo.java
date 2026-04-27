package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Random;


/**
 * 
 * Mazo: Gestión del mazo completo de la baraja. 
 *
 * En esta clase se implementa el patrón Singleton, ya que solamente puede haber un mazo en todo el juego. 
 *
 */
public class Mazo {
	
	// Atributos
	
	/**
	 * @baraja: 
	 */
	private ArrayList<Carta> baraja;
	private static Mazo miMazo = null;
	
	private Mazo () {
		this.baraja = new ArrayList<Carta >();
	}
	
	public static Mazo getMazo() {
		if (miMazo == null) {
			miMazo = new Mazo();
		}
		return miMazo;
	}
	
	public void generarMazo() {
		int i;
		
		i = 1;
		while (i <= 10) {
			baraja.add(new Carta(Palo.Oros, i));
			i++;
		}
		
		i = 1;
		while (i <= 10) {
			baraja.add(new Carta(Palo.Copas, i));
			i++;
		}
		
		i = 1;
		while (i <= 10) {
			baraja.add(new Carta(Palo.Espadas, i));
			i++;
		}
		
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
			int j = random.nextInt(i + 1);
			Carta aux = baraja.get(i);
			baraja.set(i, baraja.get(j));
			baraja.set(j,  aux);
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
