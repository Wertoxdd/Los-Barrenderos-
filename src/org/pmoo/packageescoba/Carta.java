package org.pmoo.packageescoba;

public class Carta {
	//atributos
	private String palo;
	private int valor;
	
	// constructora
	public Carta(String pPalo, int pValor) {
		this.palo = pPalo;
		this.valor = pValor;
	}
	
	// METODOS
	public String getPalo() {
		return this.palo;
	}
	
	public int getValor() {
		return this.valor;
	}
	
	@Override
	public String toString() {
		return this.valor + " de " + this.palo;
	}
}
