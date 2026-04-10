package org.pmoo.packageescoba;

public class Carta {
	// atributos
	private Palo palo;
	private int valor;
	
	// constructora
	public Carta(Palo pPalo, int pValor) {
		this.palo = pPalo;
		this.valor = pValor;
	}
	
	// METODOS
	
	/**
	 * Devuelve la figura de la carta.
	 * @return tipo de palo de la carta
	 */
	public Palo getPalo() {
		return this.palo;
	}
	
	/**
	 * Devuelve el valor de la carta
	 * @return valor de la carta
	 */
	public int getValor() {
		return this.valor;
	}

	/**
	 * Este metodo convierte un objeto tipo Carta a String
	 * cambiando además con un if el valor de la carta. 
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
