package org.pmoo.packageescoba;

import java.util.Iterator;

public class ListaCartasMonton extends ListaCartas {
	
	public ListaCartasMonton() {
		super();
	}
	
	// ✅ PRIVATE - No exponer
	private Iterator<Carta> getIterador(){
		return super.getCartas().iterator();
	}
	
	public void agregarCartas(ListaCartasMonton pLista) {
		Iterator<Carta> it = pLista.getIterador();  // Uso interno OK
		while (it.hasNext()) {
			Carta c = it.next();
			this.agregarCarta(c); 
		}
	}
	
	public int contarOros() {
		Iterator<Carta> it = getIterador();
		int cont = 0;
		while (it.hasNext()) {
			Carta c = it.next();
			if (c.getPalo().equals(Palo.Oros)) {
				cont++;
			}
		} 
		return cont;
	}
	
	public int contarSietes() {
		Iterator<Carta> it = getIterador();
		int cont = 0;
		while (it.hasNext()) {
			Carta c = it.next();
			if (c.getValor() == 7) {
				cont++;
			}
		}
		return cont;
	}
	
	public boolean tieneSieteDeOros() {
		Iterator<Carta> it = getIterador();
		while (it.hasNext()){
			Carta c = it.next();
			if (c.getPalo().equals(Palo.Oros) && c.getValor() == 7) {
				return true;
			}
		}
		return false;
	}
	
	public Carta get(int pPos) {
		return obtenerCarta(pPos);  // Usar método heredado
	}
	
	public int size() {
		return tamaño();  // Usar método heredado
	}
}
