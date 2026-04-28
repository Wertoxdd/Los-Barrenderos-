package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Iterator;


public class ListaCartasCombinaciones {
	
	
	private ArrayList<ListaCartasMonton> lista;
	
	
	public ListaCartasCombinaciones() {
		this.lista = new ArrayList<ListaCartasMonton>();
	}

	
	private Iterator<ListaCartasMonton> getIterador(){
		return lista.iterator();
	}

	
	public void add(ListaCartasMonton pLista) {
		this.lista.add(pLista);
	}

	
	public ListaCartasMonton get(int pPos) {
	   
	    if (pPos < 0) {
	        return null; 
	    }

	    Iterator<ListaCartasMonton> it = this.getIterador();
	    int pos = 0;

	    while (pos < pPos && it.hasNext()) {
	        it.next(); 
	        pos++;
	    }
	    if (it.hasNext()) {
	        return it.next();
	    } else {
	        return null; 
	    }
	}

	
	public int size() {
		return lista.size();
	}
}
