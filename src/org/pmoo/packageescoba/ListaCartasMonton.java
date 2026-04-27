package org.pmoo.packageescoba;

/**
 * ListaCartasMonton - Gestiona las cartas capturadas por un jugador
 * 
 * ⚠️ DISEÑO: No accede directamente al ArrayList de la superclase.
 * Utiliza únicamente métodos públicos/protected de ListaCartas.
 */
public class ListaCartasMonton extends ListaCartas {
    
    /**
     * Constructora
     */
    public ListaCartasMonton() {
        super();
    }
    
    /**
     * Añade todas las cartas de otra lista a este montón
     * @param pLista La lista de cartas a añadir
     */
    public void agregarCartas(ListaCartasMonton pLista) {
        // ✅ Usar bucle con índices en vez de iterador
        for (int i = 0; i < pLista.tamaño(); i++) {
            Carta c = pLista.obtenerCarta(i);
            this.agregarCarta(c);
        }
    }
    
    /**
     * Cuenta cuántas cartas de oros hay en el montón
     * @return Número de cartas de oros
     */
    public int contarOros() {
        int cont = 0;
        // ✅ Usar bucle con índices en vez de iterador
        for (int i = 0; i < this.tamaño(); i++) {
            Carta c = this.obtenerCarta(i);
            if (c.getPalo().equals(Palo.Oros)) {
                cont++;
            }
        }
        return cont;
    }
    
    /**
     * Cuenta cuántos sietes hay en el montón
     * @return Número de sietes
     */
    public int contarSietes() {
        int cont = 0;
        // ✅ Usar bucle con índices en vez de iterador
        for (int i = 0; i < this.tamaño(); i++) {
            Carta c = this.obtenerCarta(i);
            if (c.getValor() == 7) {
                cont++;
            }
        }
        return cont;
    }
    
    /**
     * Comprueba si el montón tiene el 7 de oros
     * @return true si tiene el 7 de oros
     */
    public boolean tieneSieteDeOros() {
        // ✅ Usar bucle con índices en vez de iterador
        for (int i = 0; i < this.tamaño(); i++) {
            Carta c = this.obtenerCarta(i);
            if (c.getPalo().equals(Palo.Oros) && c.getValor() == 7) {
                return true;
            }
        }
        return false;
    }
}
    
   
