package org.pmoo.packageescoba;

import java.util.ArrayList;

/**
 * ListaCartasMesa - Gestiona las cartas que están sobre la mesa
 * 
 * ⚠️ DISEÑO: No accede directamente al ArrayList de la superclase.
 * Utiliza únicamente métodos públicos/protected de ListaCartas.
 */
public class ListaCartasMesa extends ListaCartas {
    
    /**
     * Constructora
     */
    public ListaCartasMesa() {
        super();
    }
    
    /**
     * Elimina una carta específica de la mesa
     * @param pCarta La carta a eliminar
     */
    public void eliminarCarta(Carta pCarta) {
        // ✅ Usar método heredado de ListaCartas (sin acceder a getCartas())
        // Necesitas añadir este método en ListaCartas (ver más abajo)
        eliminarCartaPorObjeto(pCarta);
    }
    
    /**
     * Comprueba si la mesa está vacía
     * @return true si no hay cartas en la mesa
     */
    public boolean estaVacia() {
        // ✅ Usar método público tamaño() en vez de getCartas().size()
        return this.tamaño() == 0;
    }
    
    /**
     * Comprueba si se puede sumar 15 con la carta jugada y cartas de la mesa
     * @param pCarta La carta que juega el jugador
     * @return true si existe alguna combinación que sume 15
     */
    public boolean sumaQuince(Carta pCarta) {
        int objetivo = 15 - pCarta.getValor();
        
        // ✅ Crear lista auxiliar usando métodos públicos
        ListaCartasAuxiliar listaAux = new ListaCartasAuxiliar();
        this.copiarA(listaAux);  // Método heredado de ListaCartas
        
        // ✅ Convertir a ArrayList SOLO para la recursividad (uso interno)
        ArrayList<Carta> arrayList = new ArrayList<Carta>();
        for (int i = 0; i < listaAux.tamaño(); i++) {
            arrayList.add(listaAux.obtenerCarta(i));
        }
        
        return puedeSumarQuince(arrayList, objetivo, 0, 0);
    }
    
    /**
     * Copia todas las cartas de la mesa a otra lista
     * @param destino La lista de destino
     */
    public void agregarCartas(ListaCartas destino) {
        // ✅ Usar método heredado de ListaCartas (encapsula el iterador)
        this.copiarA(destino);
    }
    
    /**
     * Obtiene una carta por posición (heredado de ListaCartas)
     * @param posicion La posición de la carta
     * @return La carta en esa posición, o null si no existe
     */
    @Override
    public Carta obtenerCarta(int posicion) {
        return super.obtenerCarta(posicion);
    }
    
    /**
     * Recursividad para buscar combinaciones que sumen 15
     * @param pLista Lista de cartas a comprobar
     * @param objetivo El valor objetivo (15 - valor de la carta jugada)
     * @param indice Índice actual en la recursividad
     * @param sumaActual Suma acumulada hasta ahora
     * @return true si se encuentra una combinación válida
     */
    private boolean puedeSumarQuince(ArrayList<Carta> pLista, int objetivo, 
            int indice, int sumaActual) {
        
        // Caso base: se alcanzó el objetivo
        if (sumaActual == objetivo) {
            return true;
        }
        
        // Caso base: se superó el objetivo o no hay más cartas
        if (sumaActual > objetivo || indice >= pLista.size()) {
            return false;
        }
        
        // Opción 1: Incluir la carta actual en la suma
        if (puedeSumarQuince(pLista, objetivo, indice + 1, 
                sumaActual + pLista.get(indice).getValor())) {
            return true;
        }
        
        // Opción 2: Excluir la carta actual
        return puedeSumarQuince(pLista, objetivo, indice + 1, sumaActual);
    }
}
