package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class JugadorIA extends Jugador {
	
	// atributo del random
	private Random random;
	
	// constructora
	public JugadorIA() {
		super("IA");
		random = new Random();
	}
	
	public Carta elegirCarta() {
		int i = random.nextInt(getMano().tamaño());
		Carta c = getMano().elegirCarta(i);
		System.out.println("\nLa IA juega: " + c.getValor() + " de " + c.getPalo());
		return c;
	}
	
	public ListaCartasMonton elegirCaptura(ListaCartasMesa mesa, Carta pCarta) {
		ArrayList<ListaCartasMonton> posiblesCombinaciones = new ArrayList<ListaCartasMonton>();
		ArrayList<Carta> cartasMesa = new ArrayList<Carta>();
		Iterator<Carta> it = mesa.getIterador();
		
		while (it.hasNext()) {
			Carta c = it.next();
			cartasMesa.add(c);
		}
		
		buscarCombinaciones(cartasMesa, 0, 15 - pCarta.getValor(), new ListaCartasMonton(), posiblesCombinaciones);
		System.out.println("La IA captura: " + posiblesCombinaciones.get(0));
		return posiblesCombinaciones.get(0);
	}
	
	
	// para evitar un codigo redundante y excesivo lleno de iterators, se usa la recursividad.
	private void buscarCombinaciones(ArrayList<Carta> pLista, int i, int objetivo, ListaCartasMonton listaActual, ArrayList<ListaCartasMonton> posiblesCombinaciones) {
		
		if (objetivo == 0) {
			posiblesCombinaciones.add(listaActual);
			return;
		}
		
		if (i >= pLista.size()) return;
		
		Carta c = pLista.get(i);		
		if (c.getValor() <= objetivo) {
			ListaCartasMonton nuevaLista = new ListaCartasMonton();
			nuevaLista.agregarCartas(listaActual);
			nuevaLista.agregarCarta(c);
			buscarCombinaciones(pLista, i+1, objetivo - c.getValor(), nuevaLista, posiblesCombinaciones);
		}
		
		buscarCombinaciones(pLista, i+1, objetivo, listaActual, posiblesCombinaciones);
		
	}
	
}
