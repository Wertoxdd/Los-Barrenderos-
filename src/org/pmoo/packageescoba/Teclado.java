package org.pmoo.packageescoba;

import java.util.Scanner;

public class Teclado {
	// objeto para leer inputs
	private static Scanner sc = new Scanner(System.in);
	
	// metodos
	
	
	public static int leerEntero(String mensaje) { // se pone un mensaje como parametro porque no sabemos en que caso se va a usar
		System.out.println(mensaje);
		return sc.nextInt();
	}
	
	
	
	
}
