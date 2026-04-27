package org.pmoo.packageescoba;

public class ListaCartasMano extends ListaCartas {
    
    public ListaCartasMano() {}
    
    // ✅ Usar métodos heredados de ListaCartas
    public Carta obtenerCarta(int pos) {
        return super.obtenerCarta(pos);
    }
    
    // ✅ elegirCarta() elimina y devuelve (diferente de obtenerCarta())
    public Carta elegirCarta(int pos) {
        return super.eliminarCarta(pos);
    }
    
    public boolean estaVacia() {
        return this.tamaño() == 0;
    }
    
    // ✅ NO necesita getIterador() - no lo usa para su funcionalidad
}
