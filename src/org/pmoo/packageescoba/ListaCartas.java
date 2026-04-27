package org.pmoo.packageescoba;

import java.util.Iterator;
import java.util.ArrayList;


/**
 * 
 *
 * ListaCartas: Responsable de acumular en un ArrayList de Carta, una lista de cartas.
 * 
 * Proporciona metodos comunes, que usaran sus hijas.
 *
 */
public class ListaCartas {
	
	// ==============   Atributos   ==============
	
	// cartas: Guarda una lista de cartas.
    protected ArrayList<Carta> cartas;
    
    // ==============   Constructora (protected)   ==============
    protected ListaCartas() {
        this.cartas = new ArrayList<Carta>();
    }
    
    // ==============   Métodos   ==============
    
    /**
     * 
     * Método que inserta una carta al final de la lista de cartas.
     * 
     * @param pCarta: Recibe la carta que va a añadir.
     * 
     */
    public void agregarCarta(Carta pCarta) {
        this.cartas.add(pCarta);
    }
    
    /**
     * 
     * Método que devuelve el tamaño de una lista de cartas.
     * 
     * @return tamaño
     */
    public int tamaño() {
        return this.cartas.size();
    }
    
    /**
     * 
     * Método que devuelve la carta en una posición concreta sin eliminarla de la lista.
     * 
     * @param posicion: La posicion de la carta - 1.
     * @return la carta o null.
     */
    public Carta obtenerCarta(int posicion) {
        if (posicion >= 0 && posicion < cartas.size()) {
            return cartas.get(posicion);
        }
        return null;
    }
    
    /**
     * 
     * Método que elimina la carta que se encuentra en la posicion que se ha insertado.
     * 
     * @param posicion: La posicion de la carta que se quiere eliminar - 1.
     * @return la carta 
     */
    public Carta eliminarCarta(int posicion) {
        if (posicion >= 0 && posicion < cartas.size()) {
            return cartas.remove(posicion);
        }
        return null;
    }
    
    /**
     * 
     * Método que llama al iterador del ArrayList de Carta para iterar sobre una lista de cartas.
     * 
     * @return el iterador
     */
    private Iterator<Carta> getIterador() {
        return this.cartas.iterator();
    }
    
    
    /**
     * 
     * Método que copia una lista de cartas para pegarla a otra lista de cartas
     * 
     * @param pLista: Recibe una lista de cartas nueva.
     */
    public void copiarA(ListaCartas pLista) {
        Iterator<Carta> it = getIterador(); 
        while (it.hasNext()) {
            pLista.agregarCarta(it.next());
        }
    }
    
    /**
     * 
     * Método para resetear una lista de cartas.
     * 
     */
    public void resetear() {
        this.cartas.clear();
    }
    
    /**
     * 
     * Método que recorre la lista completa para imprimir carta por carta usando "+" para separar entre cartas.
     * 
     * Override porque modificas el metodo toString.
     * 
     */
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
