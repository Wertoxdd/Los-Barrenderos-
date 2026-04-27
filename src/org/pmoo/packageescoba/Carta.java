package org.pmoo.packageescoba;

/**
 * 
 * Carta: Representa una única carta de la baraja con su valor y su palo. 
 *
 */

public class Carta {
	
	// ==============   Atributos   ==============
	
	private Palo palo; 
	private int valor;
	
	// ==============   Constructora   ==============
	
	public Carta(Palo pPalo, int pValor) {
		this.palo = pPalo;
		this.valor = pValor;
	}
	
	// ==============   Metodos   ==============
	
	/**
	 * Método para devolver el palo de una carta.
	 * @return palo
	 */
	public Palo getPalo() {
		return this.palo;
	}
	
	/**
	 * Método para devolver el valor de una carta.
	 * @return valor
	 */
	public int getValor() {
		return this.valor;
	}

	
	/**
	 * Método para convertir una carta en formato String.
	 * 
	 * "Override" porque toString es un metodo que ya existe. Por eso lo que hacemos es sobreescribirlo.
	 * 
	 * @return
	 */
	@Override
	public String toString() {
	    String val;
	    if (valor == 8) {
	        val = "Sota";
	    } else if (valor == 9) {
	        val = "Caballo";
	    } else if (valor == 10) {
	        val = "Rey";
	    } else {
	        val = String.valueOf(valor);
	    }
	    return val + " de " + palo;
	}
}
