package org.pmoo.packageescoba;

import java.util.ArrayList;

/**
 * ListaCartasAuxiliar - Lista temporal para almacenar cartas de la mesa
 * durante el proceso de búsqueda de combinaciones
 * 
 * ⚠️ DISEÑO: No accede directamente al ArrayList de la superclase.
 * Utiliza únicamente métodos públicos/protected de ListaCartas.
 */
public class ListaCartasAuxiliar extends ListaCartas {
    
    // ArrayList de combinaciones (esto SÍ puede ser privado, es interno de esta clase)
    private ArrayList<ListaCartasMonton> combinaciones;
    
    /**
     * Constructora
     */
    public ListaCartasAuxiliar() {
        super();
        combinaciones = new ArrayList<ListaCartasMonton>();
    }
    
    /**
     * Obtiene una carta por posición
     * @param i La posición de la carta
     * @return La carta en esa posición
     */
    public Carta obtener(int i) {
        // ✅ Usar método heredado de ListaCartas (sin getCartas())
        return super.obtenerCarta(i);
    }
    
    /**
     * Devuelve el número de cartas en la lista
     * @return El tamaño de la lista
     */
    @Override
    public int tamaño() {
        // ✅ Usar método heredado de ListaCartas (sin getCartas())
        return super.tamaño();
    }
    
    /**
     * Devuelve la lista de combinaciones encontradas
     * @return ArrayList con las combinaciones
     */
    public ArrayList<ListaCartasMonton> getCombinaciones() {
        return combinaciones;
    }
    
    /**
     * Añade una nueva combinación a la lista
     * @param pLista La combinación a añadir
     */
    public void añadirCombinacion(ListaCartasMonton pLista) {
        combinaciones.add(pLista);
    }
    
    /**
     * Devuelve el número de combinaciones encontradas
     * @return Número de combinaciones
     */
    public int numeroCombinaciones() {
        return combinaciones.size();
    }
    
    /**
     * Obtiene una combinación por posición
     * @param i La posición de la combinación
     * @return La combinación en esa posición
     */
    public ListaCartasMonton obtenerCombinacion(int i) {
        return combinaciones.get(i);
    }
    
    /**
     * Añade un método add() para compatibilidad con ListaListas
     * @param pLista La combinación a añadir
     */
    public void add(ListaCartasMonton pLista) {
        combinaciones.add(pLista);
    }
}
