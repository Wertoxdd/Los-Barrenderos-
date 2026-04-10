package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
	// atributos
	private ArrayList<Carta> mazo;
	private static Mazo miMazo = null;
	
	// constructora 
	// No solo genera un nuevo arrayList de Cartas sino que también va a generar todas las cartas que necesitamos en nuestro mazo. 
	private Mazo() {
		this.mazo = new ArrayList<Carta>();	
		String[] palos = {"Oros", "Copas", "Espadas", "Bastos"};
		
		 for (String paloActual : palos)       {  
		     for (int valor = 1; valor <= 10; valor++) {         
		         Carta nuevaCarta = new Carta(paloActual, valor);
		         this.mazo.add(nuevaCarta);
		        }
		    }
		this.barajarMazo();
	}
	
	// Singleton
	public static Mazo getMazo() {
		if(miMazo == null) {
			miMazo = new Mazo();
		} 
		return miMazo;
	}
	// se baraja el arrayList de Cartas en mazo
	private void barajarMazo() {
		Collections.shuffle(mazo);
		System.out.println("Se ha barajado el mazo");
	}
	//Dado un objeto de tipo jugador se le daran 3 cartas del mazo a la mano del jugador
	public void repartirCartas(Jugador pJugador) {
		if (!this.mazo.isEmpty()) {
			for (int i=0 ; i<3;i++) {
			
			Carta miCarta = this.mazo.remove(0);
			pJugador.recibirCarta(miCarta);
			}
		}
			else {
				System.out.println("El mazo está vacío");
			}
		}


	public Carta darCarta() {
		
		if (!mazo.isEmpty()) {
		return this.mazo.remove(0);
		}
		else {
			System.out.println("El mazo está vacío");
		return null;
		}
	}

	
}
	

