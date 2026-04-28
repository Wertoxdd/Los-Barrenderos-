package org.pmoo.packageescoba;

/**
 * 
 * 
 * Jugador: Representa el participante de la partida. Contiene el nombre del jugador, el cumulo de cartas en su mano y en su monton, 
 * y el algoritmo de busqueda de combinaciones.
 * 
 * Es una clase abstracta, ya que se divide en si es una persona (JugadorPersona) o una IA (JugadorIA).
 *
 */
public abstract class Jugador {
	
	// ==============   Atributos   ==============
	
	// nombre: Guarda el nombre del jugador.
    private String nombre;
    
    // mano: Guarda la lista de cartas de la mano.
    private ListaCartasMano mano;
    
    // monton: Guarda la lista de cartas que tienes acumuladas en el montón
    private ListaCartasMonton monton;
    
    // escobas: Guarda las escobas de cada jugador.
    private int escobas;
    
    // puntosRonda: Guarda los puntos que se han hecho en una ronda
    private int puntosRonda;
    
    // puntosAcumulados: Guarda los puntos que se han hecho durante la partida.
    private int puntosAcumulados;
    
    // posiblesCombinaciones: Guarda los resultados de la busqueda recursiva de sumas de 15. 
    private ListaCartasCombinaciones posiblesCombinaciones;
    
    
    // ==============   Constructora   ==============
    public Jugador(String pNombre) {
        this.nombre = pNombre;
        this.mano = new ListaCartasMano();
        this.monton = new ListaCartasMonton();
        this.escobas = 0;
        this.puntosRonda = 0;
        this.puntosAcumulados = 0;
    }
    
    
    // ==============   Metodos   ==============
    
    /**
     * 
     * Método que devuelve el nombre de un jugador.
     * 
     * @return nombre
     */
    public String getNombre() { 
    	return this.nombre; 
    }
    
    /**
     * 
     * Método que devuelve la mano de un jugador
     * 
     * 
     * @return mano
     */
    public ListaCartasMano getMano() {
    	return this.mano; 
    }
    
    /**
     * 
     * Método que devuelve el monton de cartas acumuladas de un jugador.
     * 
     * @return monton
     */
    public ListaCartasMonton getMonton() { return this.monton; }
    
    
    /**
     * 
     * Método que añade una carta que viene del mazo directamente a la mano del jugador.
     * 
     * @param pCarta: Recibe la carta del mazo.
     */
    public void recibirCarta(Carta pCarta) {
        mano.agregarCarta(pCarta);
    }
    
    /**
     * 
     * Método que añade un grupo de cartas capturadas del tablero al monton del jugador.
     * 
     * @param pCartas: Recibe un cúmulo de cartas.
     */
    public void agregarCapturadas(ListaCartasMonton pCartas) {
        monton.agregarCartas(pCartas);
    }
    
    /**
     * 
     * Método que añade escobas.
     * 
     */
    public void añadirEscoba() {
        escobas++;
    }
    
    /**
     * 
     * Método que devuelve las escobas
     * 
     * @return escobas
     */
    public int obtenerEscobas() {
    	return this.escobas; 
    }
    
    /**
     * 
     * Método que comprueba si la mano de un jugador esta vacía.
     * 
     * @return booleano
     */
    public boolean manoVacia() {
    	return this.mano.estaVacia();
    }
    
    /**
     * 
     * Método que cuenta la cantida de cartas que hay en el monton de cartas de un jugador.
     * 
     * @return la cantidad de cartas.
     */
    public int totalCartas() {
    	return this.monton.tamaño(); 
    	}
    
    /**
     * 
     * Método que cuenta la cantida de oros que tiene un jugador en su monton.
     * 
     * @return la cantidad de oros.
     */
    public int totalOros() {
        return this.monton.contarOros();
    }
    
    /**
     * 
     * Método que cuenta la cantida de oros que tiene un jugador en su monton.
     * 
     * @return la cantidad de oros.
     */
    public int totalSietes() {
        return this.monton.contarSietes();
    }
    
    /**
     * 
     * Método que comprueba quien tiene el siete de oros en su monton.
     * 
     * @return booleano.
     */
    public boolean tieneSieteDeOros() {
        return this.monton.tieneSieteDeOros();
    }
    
    /**
     * 
     * Método que devuelve los puntos que ha hecho un jugador en una ronda.
     * 
     * @return los puntos de la ronda.
     */
    public int getPuntosRonda() {
    	return this.puntosRonda; 
    }
    
    /**
     * 
     * Método que devuelve los puntos acumulados durante la partida.
     * 
     * @return los puntos acumulados.
     */
    public int getPuntosAcumulados() {
    	return this.puntosAcumulados; 
    }
    
    /**
     * 
     * Método que añade los puntos que ha hecho durante la ronda al atributo de puntosRonda
     * 
     * @param pPuntos que se han hecho durante la ronda
     */
    public void añadirPuntosRonda(int pPuntos) {
        this.puntosRonda += pPuntos;
    }
    
    /**
     * 
     * Método que añade los puntos que ha hecho en total al atributo de puntosAcumulados
     * 
     * @param pPuntos que se han hecho durante la partida
     */
    public void añadirPuntosAcumulados(int pPuntos) {
        this.puntosAcumulados += pPuntos;
    }
    
    /**
     * 
     * Método para resetear los puntos de la ronda a cada jugador.
     * 
     */
    public void resetearPuntosRonda() {
        this.puntosRonda = 0;
    }

    /**
     * 
     * Método para obtener las posibles combinaciones al calcular. 
     * 
     * @return posibles combinaciones
     */
    public ListaCartasCombinaciones getPosiblesCombinaciones() {
        return this.posiblesCombinaciones;
    }
    
    // ==============   Métodos abstractos   ==============
    public abstract Carta elegirCarta();
    public abstract ListaCartasMonton elegirCaptura(ListaCartasMesa pMesa, Carta pCarta);
    
    
    /**
     * 
     * Método recursivo, que mediante "backtracking" explora todas las combinaciones posibles de cartas en la mesa que, sumadas al valor de 
     * la carta que se ha decidido jugar, den exactamente 15. Si se encuentra una combinación válida, la guarda en posiblesCombinaciones.
     * 
     * INICIALIZACIÓN: Cuando i = 0, entonces se crea una nueva instancia de ListaCartasCombinaciones para limpiar resultados previos.
     * 
     * CASO 1 (caso de éxito): Si el objetivo es 0, se ha encontrado una suma válida, por lo que se clona a la lista actual y se guarda.
     * 
     * CASO 2 (caso de detención): Si se recorre toda la lista sin alcanzar el objetivo, se detiene la búsqueda en esa rama.
     * 
     * RECURSIVIDAD: El método intenta dos caminos:
     *    * Incluyendo la carta actual (si no supera el objetivo)
     * 	  * Saltando la carta actual, así se exploran todas las combinaciones posibles.
     *  
     * @param pLista: Recibe la lista de cartas auxiliares de la mesa sobre la que se realiza la búsqueda.
     * @param pI: Recibe el índice que indica qué carta de la lista se esta evaluando en este paso. 
     * @param pObjetivo: Recibe el valor númerico que falta para completar la suma de 15.
     * @param pListaActual: La combinación temporal de cartas que se va formando durante la exploración de combinaciones.
     */
    protected void buscarCombinaciones(ListaCartasAuxiliar pLista, int pI, int pObjetivo, ListaCartasMonton pListaActual) {
    	
        // INICIALIZACIÓN
    	if (pI == 0) {
            this.posiblesCombinaciones = new ListaCartasCombinaciones();
        }
    	
    	// CASO 1
        if (pObjetivo == 0) {
            ListaCartasMonton copia = new ListaCartasMonton();
            pListaActual.copiarA(copia);
            this.posiblesCombinaciones.add(copia);
            return;
        }
        
        // CASO 2
        if (pI >= pLista.tamaño()) return;

        Carta c = pLista.obtenerCarta(pI);
        
        // RECURSIVIDAD
        if (c.getValor() <= pObjetivo) {
            pListaActual.agregarCarta(c);
            buscarCombinaciones(pLista, pI + 1, pObjetivo - c.getValor(), pListaActual);
            pListaActual.eliminarCarta(pListaActual.tamaño() - 1);
        }

        buscarCombinaciones(pLista, pI + 1, pObjetivo, pListaActual);
    }
}
