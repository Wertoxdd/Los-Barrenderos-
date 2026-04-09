package org.pmoo.packageescoba;
import java.util.ArrayList;
import java.util.Iterator;
public class Mesa {
	// atributos
	private ArrayList<Jugador> jugadores; // lista de jugadores
	private ListaCartasMesa cartasMesa;
	private static Mesa miMesa = null; // atributo para el singleton de la mesa
	
	private int puntosJugador = 0; // puntos de cada partida
	private int puntosIA = 0;
	
	private int puntosAcumuladosJugador = 0; // puntos que se acumulan despues de cada partida (HASTA LLEGAR A 11, 15 o 21 (depende)) 
	private int puntosAcumuladosIA = 0;
	
	private static final String ficheroRanking = "ranking.txt"; // atributo para generar el fichero
	
	// Constructora
	private Mesa() {
		this.jugadores = new ArrayList<Jugador>(); // inicializar la lista de jugadores
		this.jugadores.add(new JugadorPersona(0)); // inicializar el jugador con 0 puntos
		this.jugadores.add(new JugadorIA(0)); // inicializar la ia con 0 puntos
		
		this.cartasMesa = new ListaCartasMesa(); // crear las cartas de la mesa
		
		this.puntosJugador = 0;
		this.puntosIA = 0;
		this.puntosAcumuladosJugador = 0;
		this.puntosAcumuladosIA = 0;
		
	}
	
	//Métodos
	public static Mesa getMesa() {
		if (miMesa == null) {
			miMesa = new Mesa();
		}
		
		return miMesa;
	}
	
	// metodos
	
	private Iterator<Jugador> getIterador(){
		return this.jugadores.iterator();
	}
	
	
	
	// metodo para repartir las cartas a los jugadores y a la mesa
	private void repartirCartas(boolean esRepartoInicial) { // el parametro esRepartoInicial muestra si es el reparto inicial para saber si es la primera vez que se reparte, PARA NO VOLVER A REPARTIR 4 CARTAS A LA MESA CADA RONDA 
		Mazo mazo = Mazo.getMazo();
		
		// 4 cartas en la mesa (SOLO REPARTO INICIAL)
		if (esRepartoInicial) {
			for (int i = 0; i < 4; i++) {
				cartasMesa.agregar(mazo.darCarta());
			}
		}
		
		// 3 a cada jugador
		Iterator<Jugador> it = getIterador();
		while (it.hasNext()) {
			Jugador j = it.next();
			mazo.repartirCartas(j);
			}
	}
	
	
	
	
	public void jugarPartida() {
		// TODO PROGRAMA PRINCIPAL, AQUÍ OCURRE EL BUCLE DE JUEGO CON LOS TURNOS DE CADA JUGADOR Y EL RECUENTO FINAL, LA PARTIDA TERMINA CUANDO SE ACABAN LAS CARTAS DE LAS MANOS DE LOS JUGADORES Y SE ACABAN LAS CARTAS DEL MAZO	
	}
	private Iterator<Jugador> getIterator(){
		return this.jugadores.iterator();
	}
}
