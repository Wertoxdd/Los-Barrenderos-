package org.pmoo.packageescoba;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaListas {
	private ArrayList<ListaCartasMonton> lista;
	public ListaListas() {
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
public int size() {
	return lista.size();
}

}
