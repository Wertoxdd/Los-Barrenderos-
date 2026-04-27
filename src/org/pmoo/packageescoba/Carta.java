package org.pmoo.packageescoba;

public class Carta {
	private Palo palo;
	private int valor;
	
	public Carta(Palo pPalo, int pValor) {
		this.palo = pPalo;
		this.valor = pValor;
	}
	
	public Palo getPalo() {
		return this.palo;
	}
	
	public int getValor() {
		return this.valor;
	}

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
