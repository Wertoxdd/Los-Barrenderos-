package org.pmoo.packageescoba;


public class ListaCartasMano extends ListaCartas {
    
    
    public ListaCartasMano() {}
    
    
    public Carta consultarCarta(int pos) {
        return super.obtenerCarta(pos);
    }
    
    
    public Carta elegirCarta(int pos) {
        return super.eliminarCarta(pos);
    }
    
    
    public boolean estaVacia() {
        return this.tamaño() == 0;
    }
}
