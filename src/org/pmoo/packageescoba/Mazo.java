package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * Mazo: Representa la baraja de 40 cartas. 
 * 
 * Hereda a ListaCartas para aprovechar su gestión de listas. 
 * 
 * Utiliza el Patrón Singleton porque es una MAE (no pueden haber dos barajas en una partida).
 * 
 */

public class Mazo extends ListaCartas {
	
	// ==============   Atributos   ==============
	
	// miMazo: Instancia del mazo
	private static Mazo miMazo = null;
	
	// ==============   Constructora (privada)   ============== 
	private Mazo () {
		super();
	}
	
	// ==============   Patrón Singleton   ==============	
	public static Mazo getMazo() {
		if (miMazo == null) {
			miMazo = new Mazo();
		}
		return miMazo;
	}
	
	// ==============   Métodos   ==============
	
	/**
	 * 
	 * Método que crea las cartas y las agrega en la lista de cartas de la baraja.
	 * 
	 */
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
	
	
	/**
	 * Método que baraja el mazo, usando Random.
	 * 
	 * NOTA: Extrae las cartas a una lista auxiliar, para luego volver a insertarlo en la lista anterior con un orden aleatorio.
	 * 
	 */
	public void barajarMazo() {
		Random random = new Random();
		ArrayList<Carta> aux = new ArrayList<Carta>();
		int total = this.tamaño();
		for (int i = 0; i < total; i++) {
			aux.add(this.eliminarCarta(0));
		}
		
		while (!aux.isEmpty()) {
			int r = random.nextInt(aux.size());
			this.agregarCarta(aux.remove(r));
		}
	}
	
	
	/**
	 * 
	 * Método para dar una carta. 
	 * 
	 * Entrega la ultima carta de la baraja (la de encima del mazo) y la quita de la lista usando la propiedad de remove.
	 * 
	 * @return una carta.
	 */
	public Carta darCarta() {
		return this.eliminarCarta(this.tamaño() - 1);
	}
	
	
	/**
	 * 
	 * Método para saber si la baraja esta vacia.
	 * 
	 * @return booleano.
	 */
	public boolean estaVacio() {
		return this.tamaño() == 0;
	}
	
	
	
	/**
	 * 
	 * Método para resetear el mazo y el estado temporal de los jugadores (monton y escobas) al inicio de cada ronda.
	 * 
	 */
	public void resetear() {
		super.resetear();
		ListaJugadores jugadores = ListaJugadores.getListaJugadores();
		for (int i = 0; i < jugadores.tamaño(); i++) {
			Jugador j = jugadores.obtenerJugador(i);
			j.añadirPuntosRonda(-j.getPuntosRonda() - 1);
			j.resetearPuntosRonda();
		}
	}
}
