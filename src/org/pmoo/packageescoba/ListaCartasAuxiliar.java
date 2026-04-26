package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaCartasAuxiliar extends ListaCartas {
	private ArrayList<ListaCartasMonton> combinaciones;
	
	public ListaCartasAuxiliar() {
		super();
		combinaciones = new ArrayList<ListaCartasMonton>();
	}
	
	public Carta obtener(int i) {
		return getCartas().get(i);
	}
	
	public int tamaño() {
		return getCartas().size();
	}
	
	public ArrayList<ListaCartasMonton> getCombinaciones(){
		return combinaciones;
	}
	
	public void añadirCombinacion(ListaCartasMonton pLista) {
		combinaciones.add(pLista);
	}
	
	public int numeroCombinaciones() {
		return combinaciones.size();
	}
	
	public ListaCartasMonton obtenerCombinacion(int i) {
		return combinaciones.get(i);
	}
	
	// ✅ PRIVATE - No exponer
	private Iterator<Carta> getIterador(){
		return super.getCartas().iterator();
	}
}
