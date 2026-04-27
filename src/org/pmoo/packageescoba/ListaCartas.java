package org.pmoo.packageescoba;

import java.util.Iterator;
import java.util.ArrayList;

/**
 * ListaCartas - Clase base para todas las listas de cartas
 * 
 * ⚠️ DISEÑO: El ArrayList es completamente privado.
 * Las subclases NO pueden acceder a él directamente.
 */
public class ListaCartas {
    
    // ✅ COMPLETEMENTE PRIVADO - Ni siquiera protected
    private ArrayList<Carta> cartas;
    
    /**
     * Constructora protegida (solo subclases pueden crear)
     */
    protected ListaCartas() {
        this.cartas = new ArrayList<Carta>();
    }
    
    /**
     * Añade una carta a la lista
     * @param pCarta La carta a añadir
     */
    public void agregarCarta(Carta pCarta) {
        this.cartas.add(pCarta);
    }
    
    /**
     * Devuelve el número de cartas en la lista
     * @return El tamaño de la lista
     */
    public int tamaño() {
        return this.cartas.size();
    }
    
    /**
     * Obtiene una carta por posición
     * @param posicion La posición de la carta (0-based)
     * @return La carta en esa posición, o null si no existe
     */
    public Carta obtenerCarta(int posicion) {
        if (posicion >= 0 && posicion < cartas.size()) {
            return cartas.get(posicion);
        }
        return null;
    }
    
    /**
     * Elimina una carta por posición
     * @param posicion La posición de la carta a eliminar
     * @return La carta eliminada, o null si no existía
     */
    public Carta eliminarCarta(int posicion) {
        if (posicion >= 0 && posicion < cartas.size()) {
            return cartas.remove(posicion);
        }
        return null;
    }
    
    /**
     * Elimina una carta por objeto (busca y elimina)
     * @param pCarta La carta a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    protected boolean eliminarCartaPorObjeto(Carta pCarta) {
        return this.cartas.remove(pCarta);
    }
    
    /**
     * ✅ PRIVATE - Solo uso interno de la clase
     * Las subclases NO pueden acceder a este método
     */
    private Iterator<Carta> getIterador() {
        return this.cartas.iterator();
    }
    
    /**
     * Copia todas las cartas a otra lista (encapsula el iterador)
     * @param destino La lista de destino
     */
    public void copiarA(ListaCartas destino) {
        Iterator<Carta> it = getIterador();  // Uso interno OK
        while (it.hasNext()) {
            destino.agregarCarta(it.next());
        }
    }
    
    @Override
    public String toString() {
        String resultado = "";
        Iterator<Carta> it = getIterador();
        while (it.hasNext()) {
            resultado += it.next().toString();
            if (it.hasNext()) resultado += " + ";
        }
        return resultado;
    }
}
