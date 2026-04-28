package org.pmoo.packageescoba;

import java.util.ArrayList;

public class ListaCartasAuxiliar extends ListaCartas {
    
    
    private ArrayList<ListaCartasMonton> combinaciones;
    
    
    public ListaCartasAuxiliar() {
        super();
        combinaciones = new ArrayList<ListaCartasMonton>();
    }
    
    
    public Carta obtener(int i) {
        return super.obtenerCarta(i);
    }
    
   
    @Override
    public int tamaño() {
        return super.tamaño();
    }
    
    
    public ArrayList<ListaCartasMonton> getCombinaciones() {
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
    
   
    public void add(ListaCartasMonton pLista) {
        combinaciones.add(pLista);
    }
}
