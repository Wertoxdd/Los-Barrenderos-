package org.pmoo.packageescoba;

import java.util.Iterator;

public class ListaCartasMano extends ListaCartas {

	public ListaCartasMano() {
		super();
	}
	public void mostrarCartas() {
		
		// ENSEÑA TODAS LAS CARTAS DE LA MANO POR PANTALLA//
		
		Iterator<Carta> itr= super.getIterador();
		Carta miCarta = null;
		while (itr.hasNext()) {
			miCarta = itr.next();
			System.out.println(miCarta);
		}
	}
}
