package org.pmoo.packageescoba;

/**
 * Clase principal que contiene el punto de entrada de la aplicación.
 */
public class Main {
	
	/**
	 * Método principal que inicia la ejecución del juego.
	 * Crea la mesa e inicia la partida.
	 * 
	 * @param args Argumentos de la línea de comandos (no utilizados).
	 */
	public static void main(String[] args) {
		Mesa.getMesa().jugarPartida();
	}
}
