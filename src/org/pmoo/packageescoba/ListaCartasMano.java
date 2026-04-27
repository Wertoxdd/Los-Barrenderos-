package org.pmoo.packageescoba;

/**
 * Gestiona las cartas que un jugador posee en su mano para ser jugadas.
 */
public class ListaCartasMano extends ListaCartas {
    
    /**
     * Constructor que inicializa la mano vacía.
     */
    public ListaCartasMano() {}
    
    /**
     * Obtiene la carta de una posición determinada sin eliminarla de la mano.
     * 
     * @param pos Índice de la carta.
     * @return La carta en la posición indicada.
     */
    public Carta consultarCarta(int pos) {
        return super.obtenerCarta(pos);
    }
    
    /**
     * Selecciona una carta para ser jugada, eliminándola definitivamente de la mano.
     * 
     * @param pos Índice de la carta a extraer.
     * @return La carta seleccionada y eliminada.
     */
    public Carta elegirCarta(int pos) {
        return super.eliminarCarta(pos);
    }
    
    /**
     * Comprueba si el jugador se ha quedado sin cartas en la mano.
     * 
     * @return true si no hay cartas, false en caso contrario.
     */
    public boolean estaVacia() {
        return this.tamaño() == 0;
    }
}
