package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaJugadores {
	// atributos
	private ArrayList<Jugador> jugadores;
	// constructora
	
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
}