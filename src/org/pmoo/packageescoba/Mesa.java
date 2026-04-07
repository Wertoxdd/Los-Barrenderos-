package org.pmoo.packageescoba;
import java.util.ArrayList;
import java.util.Iterator;
public class Mesa {
	// atributos
	private ArrayList<Jugador> jugadores;
	private static Mesa miMesa = new Mesa();
	// Constructora
	private Mesa() {
		// TODO Auto-generated constructor stub
	}
	//Métodos
	public static Mesa getMesa() {
		return miMesa;
	}
	public void jugarPartida() {
		// TODO PROGRAMA PRINCIPAL, AQUÍ OCURRE EL BUCLE DE JUEGO CON LOS TURNOS DE CADA JUGADOR Y EL RECUENTO FINAL, LA PARTIDA TERMINA CUANDO SE ACABAN LAS CARTAS DE LAS MANOS DE LOS JUGADORES Y SE ACABAN LAS CARTAS DEL MAZO	
	}
	private Iterator<Jugador> getIterator(){
		return this.jugadores.iterator();
	}
}
