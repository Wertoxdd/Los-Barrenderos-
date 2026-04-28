package org.pmoo.packageescoba;

import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;


/**
 * 
 * 
 * Teclado: Utilidad que se encarga de gestionar la interacción del usuario con las entradas.
 * 
 * No tiene constructora porque es una utilidad.
 * 
 */
public class Teclado {
	// ==============   Atributos   ==============
	private static Scanner sc = new Scanner(System.in);
	
	// ==============   Métodos   ==============
	/**
	 * 
	 * Método que lee un número. 
	 * 
	 * @param mensajeAMostrar: Recibe el mensaje que se quiere imprimir por pantalla.
	 * @return número a leer.
	 */
	public static int leerNumero(String mensajeAMostrar) {
		System.out.println(mensajeAMostrar);
		return sc.nextInt();
	}
	
	/**
	 * 
	 * Método que muestra las cartas de la mano del jugador y permite seleccionar una.
	 * 
	 * @param pMano: Recibe la lista de cartas de la mano que contiene las cartas actuales del jugador.
	 * @return la carta seleccionada por el usuario.
	 * @throws IndiceCartaInvalidoException: Error si el indice esta fuera de rango.
	 */
	public static Carta leerCarta(ListaCartasMano pMano) throws IndiceCartaInvalidoException {
		for (int i = 0; i < pMano.tamaño(); i++) {
			System.out.print(pMano.consultarCarta(i).toString() + "  ");
		}
		System.out.println();
		
		int eleccion = leerNumero("Elige una carta(1-" + pMano.tamaño() + "): ");
        if (eleccion < 1 || eleccion > pMano.tamaño()) {
            throw new IndiceCartaInvalidoException(eleccion, pMano.tamaño());
        }
		return pMano.elegirCarta(eleccion - 1);
	}

	/**
	 * 
	 * Método que muestra las posibles combinaciones de captura y permite al usuario elegir una. 
	 * 
	 * Si solo existe una combinación, se selecciona autoaticamente.
	 * 
	 * Antes de mostrar las combinaciones se espera a que el usuario pulse enter para que el juego se sienta más pausado y no maree. 
	 * 
	 * @param pLista: Recibe la lista de las opciones de combinaciones.
	 * @return 
	 * @throws IndiceCombinacionInvalidoException: Error si el indice esta fuera de rango.
	 */
	public static ListaCartasMonton leerCaptura(ListaCartasCombinaciones pLista) throws IndiceCombinacionInvalidoException {
		for (int i = 0; i < pLista.size(); i++) {
			System.out.println("Combinacion " + (i+1) + ":");
			ListaCartasMonton cartasCombinacion = pLista.get(i);
			
			for (int j = 0; j < cartasCombinacion.tamaño(); j++) {
				System.out.print(cartasCombinacion.obtenerCarta(j).toString() + "  ");
			}
			System.out.println();
		}
		
		// SELECCION AUTOMATICA
		if (pLista.size() == 1){
			System.out.println("\nPulsa enter para continuar...");
			sc.nextLine();
			sc.nextLine();
			return pLista.get(0);
		}
		
		int eleccion = leerNumero("Elige combinacion (1-" + pLista.size() + "): ");
        if (eleccion < 1 || eleccion > pLista.size()) {
            throw new IndiceCombinacionInvalidoException(eleccion, pLista.size());
        }
		System.out.println("\nPulsa enter para continuar");
		sc.nextLine();
		sc.nextLine();
		return pLista.get(eleccion - 1);
	}
	
	/**
	 * 
	 * Método para mostrar la lista de cartas en horizontal por consola.
	 * 
	 * @param pCartas: Recibe la lista de cartas que se quiera mostrar horizontalmente.
	 */
	public static void mostrarCartasEnHorizontal(ListaCartasMonton pCartas) {
		for (int i = 0; i < pCartas.tamaño(); i++) {
			System.out.print(pCartas.obtenerCarta(i).toString() + "  ");
		}
		System.out.println();
	}
}
