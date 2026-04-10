package org.pmoo.packageescoba;

public class ListaCartasMano extends ListaCartas {
	// atributos -> heredados
	
	// constructora
	public ListaCartasMano() {}
	
	// METODOS (propios)
		
	public Carta obtenerCarta(int pos) {
		return getCartas().get(pos);
	}
	
	public Carta elegirCarta(int pos){
		return this.getCartas().remove(pos); // lo elimina y lo devuelve a la vez.
	}
	
	public boolean estaVacia() {
		return this.getCartas().size() == 0;
	}
}
