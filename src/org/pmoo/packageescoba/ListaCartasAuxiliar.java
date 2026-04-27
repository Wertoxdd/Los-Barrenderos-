package org.pmoo.packageescoba;

import java.util.ArrayList;

/**
 * Proporciona una estructura temporal para gestionar colecciones de cartas 
 * durante cálculos intermedios, como la búsqueda de combinaciones ganadoras.
 */
public class ListaCartasAuxiliar extends ListaCartas {
    
    /** Lista interna que almacena múltiples combinaciones de tipo ListaCartasMonton. */
    private ArrayList<ListaCartasMonton> combinaciones;
    
    /**
     * Constructor que inicializa la lista auxiliar y el almacén de combinaciones.
     */
    public ListaCartasAuxiliar() {
        super();
        combinaciones = new ArrayList<ListaCartasMonton>();
    }
    
    /**
     * Obtiene una carta de la lista según su posición.
     * 
     * @param i Índice de la carta.
     * @return El objeto Carta en dicho índice.
     */
    public Carta obtener(int i) {
        return super.obtenerCarta(i);
    }
    
    /**
     * Devuelve la cantidad de cartas almacenadas en la lista auxiliar.
     * 
     * @return Tamaño de la lista.
     */
    @Override
    public int tamaño() {
        return super.tamaño();
    }
    
    /**
     * Obtiene el listado completo de combinaciones encontradas.
     * 
     * @return ArrayList con los montones de cartas que forman combinaciones válidas.
     */
    public ArrayList<ListaCartasMonton> getCombinaciones() {
        return combinaciones;
    }
    
    /**
     * Registra una nueva combinación válida de cartas capturadas.
     * 
     * @param pLista El montón de cartas que forma la combinación.
     */
    public void añadirCombinacion(ListaCartasMonton pLista) {
        combinaciones.add(pLista);
    }
    
    /**
     * Obtiene el número total de combinaciones diferentes que se han identificado.
     * 
     * @return Cantidad de combinaciones almacenadas.
     */
    public int numeroCombinaciones() {
        return combinaciones.size();
    }
    
    /**
     * Recupera una combinación específica según su orden de registro.
     * 
     * @param i Índice de la combinación deseada.
     * @return El objeto ListaCartasMonton correspondiente.
     */
    public ListaCartasMonton obtenerCombinacion(int i) {
        return combinaciones.get(i);
    }
    
    /**
     * Método de conveniencia para añadir una combinación a la lista interna.
     * 
     * @param pLista La combinación a añadir.
     */
    public void add(ListaCartasMonton pLista) {
        combinaciones.add(pLista);
    }
}
