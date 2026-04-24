package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaCartas {
	// atributos
	private ArrayList<Carta> cartas;
	
	// constructora
	protected ListaCartas() {
		this.cartas = new ArrayList<Carta>();
	}
	
	// metodos
	
	protected ArrayList<Carta> getCartas(){
		return cartas;
	}
	
	public void agregarCarta(Carta pCarta) {
		this.cartas.add(pCarta);
	}
	
	public int tamaño() {
		return this.cartas.size();
	}
	
<<<<<<< HEAD
	private Iterator<Carta> getIterador(){
=======
	// iterador en protected porque se accede desde ListaCartasMesa
	protected Iterator<Carta> getIterador(){
>>>>>>> branch 'master' of https://github.com/Wertoxdd/Los-Barrenderos-.git
		return this.cartas.iterator();
	}
	
	@Override
	public String toString() {
		String resultado = "";
		Iterator<Carta> it = getIterador();
		while (it.hasNext()) {
			resultado += it.next().toString();
			if (it.hasNext()) resultado += " + ";
		}
		return resultado;
	}
}
