package org.pmoo.packageescoba;

import java.util.Random;

public class JugadorIA extends Jugador {
	
	private Random random;
	
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
		ListaCartasAuxiliar cartasMesa = new ListaCartasAuxiliar();
		
		// ✅ Usar método público que encapsula el iterador
		mesa.agregarCartas(cartasMesa);
		
		// ✅ Necesitas implementar buscarCombinaciones (igual que JugadorPersona)
		ListaListas posiblesCombinaciones = new ListaListas();
		buscarCombinaciones(cartasMesa, 0, 15 - pCarta.getValor(), 
		                   new ListaCartasMonton(), posiblesCombinaciones);
		
		// ✅ La IA elige la primera combinación disponible (o la mejor)
		if (posiblesCombinaciones.size() > 0) {
			return posiblesCombinaciones.get(0);
		}
		return new ListaCartasMonton();
	}
	
	private void buscarCombinaciones(ListaCartasAuxiliar pLista, int i, int objetivo, 
			ListaCartasMonton listaActual, ListaListas posiblesCombinaciones) {
		
		if (objetivo == 0) {
			posiblesCombinaciones.add(listaActual);
			return;
		}
		
		if (i >= pLista.tamaño()) return;
		
		Carta c = pLista.obtener(i);		
		
		if (c.getValor() <= objetivo) {
			ListaCartasMonton nuevaLista = new ListaCartasMonton();
			nuevaLista.agregarCartas(listaActual);
			nuevaLista.agregarCarta(c);
			buscarCombinaciones(pLista, i+1, objetivo - c.getValor(), nuevaLista, posiblesCombinaciones);
		}
		
		buscarCombinaciones(pLista, i+1, objetivo, listaActual, posiblesCombinaciones);
	}
}
