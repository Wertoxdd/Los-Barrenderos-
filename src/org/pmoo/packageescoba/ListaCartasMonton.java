package org.pmoo.packageescoba;

/**
 * Gestiona el montón de cartas que un jugador ha logrado capturar.
 * Incluye métodos específicos para el recuento de méritos al finalizar la ronda.
 */
public class ListaCartasMonton extends ListaCartas {
    
    /**
     * Constructor que inicializa el montón vacío.
     */
    public ListaCartasMonton() {
        super();
    }
    
    /**
     * Añade un conjunto completo de cartas capturadas a este montón.
     * 
     * @param pLista La colección de cartas a integrar.
     */
    public void agregarCartas(ListaCartasMonton pLista) {
        for (int i = 0; i < pLista.tamaño(); i++) {
            Carta c = pLista.obtenerCarta(i);
            this.agregarCarta(c);
        }
    }
    
    /**
     * Cuenta cuántas cartas pertenecen al palo de oros en este montón.
     * 
     * @return El número total de oros capturados.
     */
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
    
    /**
     * Cuenta cuántas cartas con valor numérico 7 hay en el montón.
     * 
     * @return El número total de sietes capturados.
     */
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
    
    /**
     * Verifica si el siete de oros se encuentra entre las cartas capturadas.
     * 
     * @return true si el jugador posee el siete de oros, false en caso contrario.
     */
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
