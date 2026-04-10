package org.pmoo.packageescoba;

import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;

public class Teclado {
	// sin atributos ni constructora porque es una utilidad para imprimir por pantalla
	
	private static Scanner sc = new Scanner(System.in);
	// METODOS
	
	public static int leerNumero(String mensajeAMostrar) {
		System.out.println(mensajeAMostrar);
		return sc.nextInt();
	}
	
	public static Carta leerCarta(ListaCartasMano pMano) {
		ArrayList<Carta> listaMano = new ArrayList<Carta>();
		Iterator<Carta> it = pMano.getIterador();
		while (it.hasNext()) {
			Carta c = it.next();
		    listaMano.add(c);
		}
		mostrarCartasEnHorizontal(listaMano);
	    System.out.println();
	    int eleccion = leerNumero("Elige una carta(1-" + pMano.tamaño() + "): ");
	    return pMano.elegirCarta(eleccion - 1);
	}

	public static ListaCartasMonton leerCaptura(ArrayList<ListaCartasMonton> pLista) {
	    for (int i = 0; i < pLista.size(); i++) {
	        System.out.println("Combinacion " + (i+1) + ":");
	        ArrayList<Carta> cartasCombinacion = new ArrayList<Carta>();
	        Iterator<Carta> it = pLista.get(i).getIterador();
	        while (it.hasNext()) {
	            cartasCombinacion.add(it.next());
	        }
	        mostrarCartasEnHorizontal(cartasCombinacion);
	    }
	    if (pLista.size() == 1){
	    	System.out.println("\nPulsa enter para continuar...");
	    	sc.nextLine();
	    	sc.nextLine();
	    	return pLista.get(0); }
	    int eleccion = leerNumero("Elige combinacion (1-" + pLista.size() + "): ");
	    System.out.println("\nPulsa enter para continuar");
	    sc.nextLine();
	    sc.nextLine();
	    return pLista.get(eleccion - 1);
	}
	
	public static void mostrarCartasEnHorizontal(ArrayList<Carta> cartas) {
	    ArrayList<String[]> lineasCartas = new ArrayList<String[]>();
	    for (int i = 0; i < cartas.size(); i++) {
	        lineasCartas.add(cartas.get(i).toString().split("\n"));
	    }
	    for (int linea = 0; linea < 7; linea++) {
	        for (int carta = 0; carta < lineasCartas.size(); carta++) {
	            System.out.print(lineasCartas.get(carta)[linea] + "  ");
	        }
	        System.out.println();
	    }
	}
}