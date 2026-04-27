package org.pmoo.packageescoba;

import java.util.Iterator;
import java.util.ArrayList;

public class ListaCartas {
    private ArrayList<Carta> cartas;
    
    protected ListaCartas() {
        this.cartas = new ArrayList<Carta>();
    }
    
    public void agregarCarta(Carta pCarta) {
        this.cartas.add(pCarta);
    }
    
    public int tamaño() {
        return this.cartas.size();
    }
    
    public Carta obtenerCarta(int posicion) {
        if (posicion >= 0 && posicion < cartas.size()) {
            return cartas.get(posicion);
        }
        return null;
    }
    
    public Carta eliminarCarta(int posicion) {
        if (posicion >= 0 && posicion < cartas.size()) {
            return cartas.remove(posicion);
        }
        return null;
    }
    
    protected boolean eliminarCartaPorObjeto(Carta pCarta) {
        return this.cartas.remove(pCarta);
    }
    
    private Iterator<Carta> getIterador() {
        return this.cartas.iterator();
    }
    
    public void copiarA(ListaCartas destino) {
        Iterator<Carta> it = getIterador(); 
        while (it.hasNext()) {
            destino.agregarCarta(it.next());
        }
    }
    
    public void resetear() {
        this.cartas.clear();
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
