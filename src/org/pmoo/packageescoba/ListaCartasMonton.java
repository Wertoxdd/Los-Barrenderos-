package org.pmoo.packageescoba;


public class ListaCartasMonton extends ListaCartas {
    
    
    public ListaCartasMonton() {
        super();
    }
    
    
    public void agregarCartas(ListaCartasMonton pLista) {
        for (int i = 0; i < pLista.tamaño(); i++) {
            Carta c = pLista.obtenerCarta(i);
            this.agregarCarta(c);
        }
    }
    
   
    public int contarOros() {
        int cont = 0;
        for (int i = 0; i < this.tamaño(); i++) {
            Carta c = this.obtenerCarta(i);
            if (c.getPalo().equals(Palo.Oros)) {
                cont++;
            }
        }
        return cont;
    }
    
    
    public int contarSietes() {
        int cont = 0;
        for (int i = 0; i < this.tamaño(); i++) {
            Carta c = this.obtenerCarta(i);
            if (c.getValor() == 7) {
                cont++;
            }
        }
        return cont;
    }
    
    
    public boolean tieneSieteDeOros() {
        for (int i = 0; i < this.tamaño(); i++) {
            Carta c = this.obtenerCarta(i);
            if (c.getPalo().equals(Palo.Oros) && c.getValor() == 7) {
                return true;
            }
        }
        return false;
    }
}
