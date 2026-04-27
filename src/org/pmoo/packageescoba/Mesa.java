package org.pmoo.packageescoba;


import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

// Objetos para el fichero
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;


public class Mesa {
	// atributos
	private ListaJugadores jugadores;
	private ListaCartasMesa cartasMesa;
	private static Mesa miMesa = null;
	private int puntosJugador = 0;
	private int puntosIA = 0;
	private int puntosAcumuladosJugador = 0;
	private int puntosAcumuladosIA = 0;
	
	private static final String ficheroRanking = "ranking.txt";
	
	// constructora privada
	private Mesa() {
		jugadores = new ListaJugadores();
		jugadores.agregarJugador(new JugadorPersona());
		jugadores.agregarJugador(new JugadorIA());
		cartasMesa = new ListaCartasMesa();
		puntosJugador = 0;
		puntosIA = 0;
		puntosAcumuladosJugador = 0;
		puntosAcumuladosIA = 0;
	}
	
	// Singleton
	public static Mesa getMesa() {
		if (miMesa == null) {
			miMesa = new Mesa();
		}
		return miMesa;
	}
	
	private void repartirCartas(boolean esRepartoInicial) {
		Mazo mazo = Mazo.getMazo();
		
		// 4 cartas en la mesa (SOLO REPARTO INICIAL)
		if (esRepartoInicial) for (int i = 0; i < 4; i++) {cartasMesa.agregarCarta(mazo.darCarta());}
				
		// 3 cartas a cada jugador
		Iterator<Jugador> it = jugadores.getIterador();
		while (it.hasNext()) {
			Jugador j = it.next();
			for (int i = 0; i < 3; i++) {
				j.recibirCarta(mazo.darCarta());
			}
		}
	}
	
	private boolean esEscoba() {
		return cartasMesa.estaVacia();
	}
	
	private Jugador turnoJugador(Jugador pJugador, Jugador ultimoEnCapturar) { // recibe dos jugadores, asi puede actualizar el ultimoEnCapturar mientras pasa el turno.
		System.out.println("\n================| TURNO DE " + pJugador.getNombre() + " |================");
		System.out.println("Cartas restantes en el mazo: " + Mazo.getMazo().tama�o());
		System.out.println("\nCartas en la mesa:");
		ArrayList<Carta> cartasLista = new ArrayList<Carta>();
		Iterator<Carta> it = cartasMesa.getIterador();
		while (it.hasNext()) {
		    cartasLista.add(it.next());
		}
		Teclado.mostrarCartasEnHorizontal(cartasLista);
		System.out.println();
		
		Carta cartaElegida = pJugador.elegirCarta();
		
		if (cartasMesa.sumaQuince(cartaElegida)) {
			System.out.println("\nCombinaciones posibles: ");
			ListaCartasMonton cartasCapturadas = pJugador.elegirCaptura(cartasMesa, cartaElegida);
			
			Iterator<Carta> it2 = cartasCapturadas.getIterador();
			while (it2.hasNext()) {
				Carta c = it2.next();
				cartasMesa.eliminarCarta(c);
			}
			
			cartasCapturadas.agregarCarta(cartaElegida);
			pJugador.agregarCapturadas(cartasCapturadas);
			ultimoEnCapturar = pJugador;
			
			// Mostrar mont�n actualizado
		    System.out.println("\nCartas en tu mont�n: " + pJugador.totalCartas());
			
			if (esEscoba()) {
				pJugador.a�adirEscoba();
				System.out.println("ESCOBA!");
			}
			
			
		} else {
		    cartasMesa.agregarCarta(cartaElegida);
		    System.out.println("Sin captura. El " + cartaElegida.getValor() + " de " + cartaElegida.getPalo() + " se a�ade a la mesa.");
		}
		
		return ultimoEnCapturar;
	}
	
	private void contarPuntos() {		
				
	}
	
	private void mostrarResultados() {
		
		// TITULO
		System.out.println("\n================| RESULTADO FINAL |================");
		
		// JUGADOR
		System.out.println("\n" + jugadores.obtenerJugador(0).getNombre() + ":");
		System.out.println(" Cartas:      " + jugadores.obtenerJugador(0).totalCartas());
		System.out.println(" Oros:      " + jugadores.obtenerJugador(0).totalOros());
		System.out.println(" 7s:      " + jugadores.obtenerJugador(0).totalSietes());
		System.out.println(" 7 de Oros:      " + jugadores.obtenerJugador(0).tieneSieteDeOros());
		System.out.println(" Escobas:      " + jugadores.obtenerJugador(0).obtenerEscobas());
		System.out.println(" PUNTOS: " + puntosJugador);
		
		// IA
		System.out.println("\n" + jugadores.obtenerJugador(1).getNombre() + ":");
		System.out.println(" Cartas:      " + jugadores.obtenerJugador(1).totalCartas());
		System.out.println(" Oros:      " + jugadores.obtenerJugador(1).totalOros());
		System.out.println(" 7s:      " + jugadores.obtenerJugador(1).totalSietes());
		System.out.println(" 7 de Oros:      " + jugadores.obtenerJugador(1).tieneSieteDeOros());
		System.out.println(" Escobas:      " + jugadores.obtenerJugador(1).obtenerEscobas());
		System.out.println(" PUNTOS: " + puntosIA);
		
		// SEPARADOR
		System.out.println("================================================");
		
		if (puntosJugador > puntosIA) System.out.println("GANADOR DE LA RONDA: " + jugadores.obtenerJugador(0).getNombre());
		else if (puntosJugador < puntosIA) System.out.println("GANADOR DE LA RONDA" + jugadores.obtenerJugador(1).getNombre());
		else System.out.println("EMPATE");
	}
	
	private void cargarRanking() {
		try {
			Scanner sc = new Scanner(new File(ficheroRanking));
			puntosAcumuladosJugador = sc.nextInt();
			puntosAcumuladosIA = sc.nextInt();
			
		} catch (Exception e) {
			puntosAcumuladosJugador = 0;
			puntosAcumuladosIA = 0;
		}
	}
	
	private void guardarRanking() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(ficheroRanking)); 
			pw.println(puntosAcumuladosJugador);
			pw.println(puntosAcumuladosIA);
			pw.close();
		} catch (Exception e) {
			System.out.println("Error al guardar el ranking.");
		}
	}
	
	private void mostrarRanking() {
		System.out.println("\n================| RANKING |================");
		System.out.println(jugadores.obtenerJugador(0).getNombre() + ": " + puntosAcumuladosJugador + " puntos.");
		System.out.println(jugadores.obtenerJugador(1).getNombre()+ ": " + puntosAcumuladosIA + " puntos.");
		if (puntosAcumuladosJugador >= 15) System.out.println("GANADOR DE LA PARTIDA: " + jugadores.obtenerJugador(0).getNombre());
		else if (puntosAcumuladosIA >= 15) System.out.println("GANADOR DE LA PARTIDA: " + jugadores.obtenerJugador(1).getNombre());
	}
	
	
	public void jugarPartida() {
		// inicializa el ranking
		cargarRanking();
		// empieza el bucle del juego con la condicion de que no se acabe la partida.
		while (puntosAcumuladosJugador < 15 && puntosAcumuladosIA < 15) {
			
			// Reset de la partida
			cartasMesa = new ListaCartasMesa();
			jugadores = new ListaJugadores();
			jugadores.agregarJugador(new JugadorPersona());
			jugadores.agregarJugador(new JugadorIA());
			puntosJugador = 0;
			puntosIA = 0;
			
			// Preparar mazo 
			Mazo mazo = Mazo.getMazo();
			mazo.resetear();
			mazo.generarMazo();
			mazo.barajarMazo();
			repartirCartas(true);
			
			// Resetear el ultimo jugador en capturar (para el final de la partida)
			Jugador ultimoEnCapturar = null;
			
			// Bucle de la partida
			while (!jugadores.todosConSusManosVacias() || !mazo.estaVacio()) {
				Iterator<Jugador> it = jugadores.getIterador();
				while (it.hasNext()) {
					Jugador j = it.next();
					ultimoEnCapturar = turnoJugador(j, ultimoEnCapturar);
				} 
				// para volver a repartir las cartas
				if (jugadores.todosConSusManosVacias() && !mazo.estaVacio()) {
					repartirCartas(false);
				}
			}
			
			// Fin de la partida
			if (ultimoEnCapturar != null) {
				ListaCartasMonton restoDeLaMesa = new ListaCartasMonton();
				Iterator<Carta> it = cartasMesa.getIterador();
				while (it.hasNext()) {
					Carta c = it.next();
					restoDeLaMesa.agregarCarta(c);
				}
				
				ultimoEnCapturar.agregarCapturadas(restoDeLaMesa);
			}
			
			contarPuntos();
			mostrarResultados();
			
			// para actualizar el ranking
			puntosAcumuladosJugador += puntosJugador;
			puntosAcumuladosIA += puntosIA;
			guardarRanking();
			mostrarRanking();
		}
	}
}
