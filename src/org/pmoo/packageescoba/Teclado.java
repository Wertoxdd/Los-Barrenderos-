package org.pmoo.packageescoba;

import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;

public class Teclado {
	private static Scanner sc = new Scanner(System.in);
	
	public static int leerNumero(String mensajeAMostrar) {
		System.out.println(mensajeAMostrar);
		return sc.nextInt();
	}
	
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


	public static ListaCartasMonton leerCaptura(ListaCartasCombinaciones pLista) throws IndiceCombinacionInvalidoException {
		for (int i = 0; i < pLista.size(); i++) {
			System.out.println("Combinacion " + (i+1) + ":");
			ListaCartasMonton cartasCombinacion = pLista.get(i);
			
			for (int j = 0; j < cartasCombinacion.tamaño(); j++) {
				System.out.print(cartasCombinacion.obtenerCarta(j).toString() + "  ");
			}
			System.out.println();
		}
		
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
	
	public static void mostrarCartasEnHorizontal(ListaCartasMonton cartas) {
		for (int i = 0; i < cartas.tamaño(); i++) {
			System.out.print(cartas.obtenerCarta(i).toString() + "  ");
		}
		System.out.println();
	}
}
