package org.pmoo.packageescoba;

import java.util.ArrayList;


public class ListaCartasMesa extends ListaCartas {
    
    
    public ListaCartasMesa() {
        super();
    }
    
    
    public void eliminarCarta(Carta pCarta) {
        this.cartas.remove(pCarta);
    }
    
    
    public boolean estaVacia() {
        return this.tamaño() == 0;
    }
    
    
    public boolean sumaQuince(Carta pCarta) {
        int objetivo = 15 - pCarta.getValor();
        
        ListaCartasAuxiliar listaAux = new ListaCartasAuxiliar();
        this.copiarA(listaAux); 
        
        ArrayList<Carta> arrayList = new ArrayList<Carta>();
        for (int i = 0; i < listaAux.tamaño(); i++) {
            arrayList.add(listaAux.obtenerCarta(i));
        }
        
        return puedeSumarQuince(arrayList, objetivo, 0, 0);
    }
    
    
    public void agregarCartas(ListaCartas destino) {
        this.copiarA(destino);
    }
    
    
    @Override
    public Carta obtenerCarta(int posicion) {
        return super.obtenerCarta(posicion);
    }
    
    private boolean puedeSumarQuince(ArrayList<Carta> pLista, int objetivo, 
            int indice, int sumaActual) {
        
        if (sumaActual == objetivo) {
            return true;
        }
        
        if (sumaActual > objetivo || indice >= pLista.size()) {
            return false;
        }
        
        if (puedeSumarQuince(pLista, objetivo, indice + 1, 
                sumaActual + pLista.get(indice).getValor())) {
            return true;
        }
        
        return puedeSumarQuince(pLista, objetivo, indice + 1, sumaActual);
    }
}
