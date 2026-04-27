package org.pmoo.packageescoba;

import java.util.ArrayList;

/**
 * Gestiona las cartas que se encuentran boca arriba sobre la mesa.
 * Proporciona métodos para verificar la posibilidad de capturas que sumen 15.
 */
public class ListaCartasMesa extends ListaCartas {
    
    /**
     * Constructor que inicializa la mesa vacía.
     */
    public ListaCartasMesa() {
        super();
    }
    
    /**
     * Elimina una carta física de la mesa tras haber sido capturada.
     * 
     * @param pCarta El objeto Carta a eliminar del tablero.
     */
    public void eliminarCarta(Carta pCarta) {
        eliminarCartaPorObjeto(pCarta);
    }
    
    /**
     * Comprueba si el tablero de juego se ha quedado sin cartas.
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
     * @param pCarta La carta que el jugador pone en juego.
     * @return true si existe al menos una combinación válida para captura.
     */
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
    
    /**
     * Transfiere todas las cartas de la mesa a otro contenedor.
     * 
     * @param destino Lista donde se agregarán las cartas de la mesa.
     */
    public void agregarCartas(ListaCartas destino) {
        this.copiarA(destino);
    }
    
    /**
     * Obtiene la carta situada en una posición específica de la mesa.
     * 
     * @param posicion Índice de la carta.
     * @return La carta encontrada en dicho índice.
     */
    @Override
    public Carta obtenerCarta(int posicion) {
        return super.obtenerCarta(posicion);
    }
    
    /**
     * Algoritmo recursivo interno para determinar si es posible alcanzar 
     * una suma objetivo mediante cualquier combinación de cartas.
     * 
     * @param pLista Lista de cartas disponibles para la suma.
     * @param objetivo Valor total que se desea alcanzar.
     * @param indice Posición actual del análisis en la lista.
     * @param sumaActual Valor acumulado hasta el momento en la rama de la recursión.
     * @return true si se halla una combinación satisfactoria, false en caso contrario.
     */
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
