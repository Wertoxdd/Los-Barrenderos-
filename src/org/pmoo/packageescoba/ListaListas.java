package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Estructura de datos diseñada para almacenar y gestionar múltiples colecciones 
 * de cartas capturadas (objetos ListaCartasMonton).
 */
public class ListaListas {
	
	/** Lista que contiene los diferentes montones de cartas capturadas. */
	private ArrayList<ListaCartasMonton> lista;
	
	/**
	 * Constructor que inicializa la estructura de listas vacía.
	 */
	public ListaListas() {
		this.lista = new ArrayList<ListaCartasMonton>();
	}

	/**
	 * Obtiene un iterador para recorrer secuencialmente los montones almacenados.
	 * 
	 * @return Un objeto Iterator de ListaCartasMonton.
	 */
	private Iterator<ListaCartasMonton> getIterador(){
		return lista.iterator();
	}

	/**
	 * Añade un nuevo montón de cartas a la colección.
	 * 
	 * @param pLista El objeto ListaCartasMonton a agregar.
	 */
	public void add(ListaCartasMonton pLista) {
		this.lista.add(pLista);
	}

	/**
	 * Recupera un montón de cartas específico según su posición.
	 * Implementa un recorrido mediante iterador para localizar el elemento.
	 * 
	 * @param pPos Índice del montón deseado.
	 * @return La colección ListaCartasMonton en dicha posición, o null si no existe.
	 */
	public ListaCartasMonton get(int pPos) {
	   
	    if (pPos < 0) {
	        return null; 
	    }

	    Iterator<ListaCartasMonton> itr = this.getIterador();
	    int miPos = 0;

	    while (miPos < pPos && itr.hasNext()) {
	        itr.next(); 
	        miPos++;
	    }
	    if (itr.hasNext()) {
	        return itr.next();
	    } else {
	        return null; 
	    }
	}

	/**
	 * Devuelve la cantidad de montones de cartas almacenados en la estructura.
	 * 
	 * @return Tamaño de la lista de listas.
	 */
	public int size() {
		return lista.size();
	}
}
