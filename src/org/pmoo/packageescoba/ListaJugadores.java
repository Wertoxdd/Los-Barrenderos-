package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaJugadores {
	// atributos
	private ArrayList<Jugador> jugadores;
	// constructora privada
	
	public ListaJugadores() {
		this.jugadores = new ArrayList<Jugador>();
	}
	
	public Iterator<Jugador> getIterador(){
		return this.jugadores.iterator();
	}
	
	public void agregarJugador(Jugador pJugador) {
		this.jugadores.add(pJugador);
	}
	
	public Jugador obtenerJugador(int pos) {
		return this.jugadores.get(pos);
	}
	
	public int tamaño() {
		return this.jugadores.size();
	}
	
	public boolean todosConSusManosVacias() {
		Iterator<Jugador> it = getIterador();
		while (it.hasNext()) {
			Jugador j = it.next();
			if (!j.manoVacia()) return false;
		}
		return true;
	}
	
	public Jugador jugadorConMasCartas() {
		Iterator<Jugador> it = getIterador();
		int max = 0;
		Jugador ganador = null;
		boolean empate = false; // se puede obviar pero es necesario si existe un 3er jugador
		while (it.hasNext()) {
			Jugador j = it.next();
			if (j.totalCartas() > max) { // el maximo se actualiza
				max = j.totalCartas();
				ganador = j;
			} else if (j.totalCartas() == max) { // empate
				empate = true;
			}
		}
		// comprobacion final
		if (empate) return null;
		else return ganador;
	}
	
	public Jugador jugadorConMasOros() {
		Iterator<Jugador> it = getIterador();
		int max = 0;
		Jugador ganador = null;
		boolean empate = false;
		while (it.hasNext()) {
			Jugador j = it.next();
			if (j.totalOros() > max) {
				max = j.totalOros();
				ganador = j;
			} else if (j.totalOros() == max) {
				empate = true;
			}
		}
		
		if (empate) return null;
		else return ganador;
	}
	
	
	public Jugador jugadorConMasSietes() {
		Iterator<Jugador> it = getIterador();
		int max = 0;
		Jugador ganador = null;
		boolean empate = false;
		while (it.hasNext()) {
			Jugador j = it.next();
			if (j.totalSietes() > max) {
				max = j.totalSietes();
				ganador = j;
			} else if (j.totalOros() == max) {
				empate = true;
			}
		}
		
		if (empate) return null;
		else return ganador;
	}
	
	public Jugador jugadorConElSieteDeOros() {
		Iterator<Jugador> it = getIterador();
		while (it.hasNext()) {
			Jugador j = it.next();
			if (j.tieneSieteDeOros()) {
				return j;
			}
		} return null;
	}
}














