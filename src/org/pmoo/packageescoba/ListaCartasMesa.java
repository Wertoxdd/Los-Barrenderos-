package org.pmoo.packageescoba;

/**
 * ListaCartasMesa: Gestiona las cartas que se encuentran boca arriba sobre el tablero.
 * 
 * Proporciona métodos para verificar la posibilidad de capturas que sumen 15 
 * utilizando algoritmos de búsqueda recursiva.
 *
 */
public class ListaCartasMesa extends ListaCartas {

    // ==============   Constructora   ==============
    
    /**
     * Constructor que inicializa la mesa vacía.
     */
    public ListaCartasMesa() {
        super();
    }

    
    // ==============   Métodos   ==============

    /**
     * Elimina una carta física de la mesa tras haber sido capturada.
     * 
     * @param pCarta La carta que se desea retirar del tablero.
     */
    public void eliminarCarta(Carta pCarta) {
        this.cartas.remove(pCarta);
    }

    
    /**
     * Comprueba si el tablero está totalmente despejado.
     * 
     * @return true si la mesa está vacía, false si quedan cartas.
     */
    public boolean estaVacia() {
        return this.tamaño() == 0;
    }

    
    /**
     * Verifica si existe alguna combinación de cartas en la mesa que,
     * sumada al valor de la carta jugada, dé como resultado exactamente 15.
     * 
     * @param pCarta: Recibe la carta lanzada por el jugador.
     * @return booleano si existe combinacion o no.
     */
    public boolean sumaQuince(Carta pCarta) {
        int objetivo = 15 - pCarta.getValor();
        
        // Encapsulamos las cartas en nuestra clase auxiliar para cumplir criterios de diseño
        ListaCartasAuxiliar listaAux = new ListaCartasAuxiliar();
        this.copiarA(listaAux); 
        
        return puedeSumarQuince(listaAux, objetivo, 0, 0);
    }

    
    /**
     * Copia el contenido de la mesa a otra lista de cartas.
     * 
     * @param pDestino Lista donde se agregarán las cartas de la mesa.
     */
    public void agregarCartas(ListaCartas pDestino) {
        this.copiarA(pDestino);
    }
    
    
    /**
     * Obtiene la carta situada en una posición específica de la mesa.
     * 
     * @param pPosicion Índice de la carta.
     * @return La carta encontrada en dicho índice.
     */
    @Override
    public Carta obtenerCarta(int pPosicion) {
        return super.obtenerCarta(pPosicion);
    }

    
    /**
     * Método recursivo, que mediante "backtracking" determinaa si es posible alcanzar 
     * una suma objetivo mediante cualquier combinación de cartas.
     * 
     * CASO BASE 1 (caso base): Se ha alcanzado el objetivo.
     * 
     * CASO BASE 2 (caso base también): Se ha excedido el objetivo o no se ha alcanzado el objetivo.
     * 
     * CASO 1: Incluye la carta a la lista de combinaciones.
     * 
     * CASO 2: Ignora la carta actual y sigue buscando.
     * 
     * @param pLista: ListaCartasAuxiliar que encapsula las cartas de la mesa.
     * @param pObjetivo: Valor numérico que se desea alcanzar.
     * @param pIndice: Posición actual del análisis en la lista.
     * @param pSumaActual: Valor acumulado hasta el momento en la rama de la recursión.
     * @return si se halla o no una combinación para sumar.
     */
    private boolean puedeSumarQuince(ListaCartasAuxiliar pLista, int pObjetivo, int pIndice, int pSumaActual) {
        
        // CASO BASE 1
        if (pSumaActual == pObjetivo) {
            return true;
        }
        
        // Casos base: exceso de valor o fin de la lista sin éxito
        if (pSumaActual > pObjetivo || pIndice >= pLista.tamaño()) {
            return false;
        }
        
        // Opción 1: Incluir la carta actual en la combinación
        if (puedeSumarQuince(pLista, pObjetivo, pIndice + 1, 
                pSumaActual + pLista.obtenerCarta(pIndice).getValor())) {
            return true;
        }
        
        // Opción 2: Ignorar la carta actual y continuar buscando
        return puedeSumarQuince(pLista, pObjetivo, pIndice + 1, pSumaActual);
    }
}
