package org.pmoo.packageescoba;

public class JugadorPersona extends Jugador {
	
	public JugadorPersona() {
		super("Jugador 1");
	}
	
	public Carta elegirCarta() {
		System.out.println("\nTu mano: ");
		return Teclado.leerCarta(getMano());	
	}
	
	public ListaCartasMonton elegirCaptura(ListaCartasMesa mesa, Carta pCarta) {
		ListaCartasAuxiliar cartasMesa = new ListaCartasAuxiliar();
		ListaListas posiblesCombinaciones = new ListaListas();
		
		// ✅ Usar método público que encapsula el iterador
		mesa.agregarCartas(cartasMesa);
		
		buscarCombinaciones(cartasMesa, 0, 15 - pCarta.getValor(), 
		                   new ListaCartasMonton(), posiblesCombinaciones);
		
		return Teclado.leerCaptura(posiblesCombinaciones);
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
