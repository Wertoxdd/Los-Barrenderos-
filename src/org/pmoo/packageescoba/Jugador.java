package org.pmoo.packageescoba;

public abstract class Jugador {
<<<<<<< HEAD
    private String nombre;
    private ListaCartasMano mano;
    private ListaCartasMonton monton;
    private int escobas;
    
    // ✅ NUEVO: Puntos almacenados en el Jugador (no en Mesa)
    private int puntosRonda;
    private int puntosAcumulados;
    
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ListaCartasMano();
        this.monton = new ListaCartasMonton();
        this.escobas = 0;
        this.puntosRonda = 0;
        this.puntosAcumulados = 0;
    }
    
    // Getters y setters básicos
    public String getNombre() { return nombre; }
    public ListaCartasMano getMano() { return mano; }
    public ListaCartasMonton getMonton() { return monton; }
    
    public void recibirCarta(Carta pCarta) {
        mano.agregarCarta(pCarta);
    }
    
    public void agregarCapturadas(ListaCartasMonton pCartas) {
        monton.agregarCartas(pCartas);
    }
    
    public void añadirEscoba() {
        escobas++;
    }
    
    public int obtenerEscobas() { return escobas; }
    public boolean manoVacia() { return mano.estaVacia(); }
    
    // ✅ MÉTODOS DE PUNTUACIÓN (ahora en Jugador, no en Mesa)
    public int totalCartas() { return monton.tamaño(); }
    
    public int totalOros() {
        return monton.contarOros();
    }
    
    public int totalSietes() {
        return monton.contarSietes();
    }
    
    public boolean tieneSieteDeOros() {
        return monton.tieneSieteDeOros();
    }
    
    // ✅ NUEVO: Gestión de puntos en el Jugador
    public int getPuntosRonda() { return puntosRonda; }
    public int getPuntosAcumulados() { return puntosAcumulados; }
    
    public void añadirPuntosRonda(int puntos) {
        this.puntosRonda += puntos;
    }
    
    public void añadirPuntosAcumulados(int puntos) {
        this.puntosAcumulados += puntos;
    }
    
    public void resetearPuntosRonda() {
        this.puntosRonda = 0;
    }
    
    // ✅ MÉTODO ABSTRACTO - Cada tipo de jugador implementa su forma de elegir
    public abstract Carta elegirCarta();
    public abstract ListaCartasMonton elegirCaptura(ListaCartasMesa mesa, Carta pCarta);
    
    // ✅ buscarCombinaciones() AHORA EN LA SUPERCLASE (código común)
    protected void buscarCombinaciones(ListaCartasAuxiliar pLista, int i, int objetivo, 
            ListaCartasMonton listaActual, ListaListas posiblesCombinaciones) {
        
        if (objetivo == 0) {
            posiblesCombinaciones.add(listaActual);
            return;
        }
        
        if (i >= pLista.tamaño()) return;
        
        Carta c = pLista.obtener(i);        
        
        if (c.getValor() <= objetivo) {
            ListaCartasMonton nuevaLista = new ListaCartasMonton();
            nuevaLista.agregarCartas(listaActual);
            nuevaLista.agregarCarta(c);
            buscarCombinaciones(pLista, i+1, objetivo - c.getValor(), nuevaLista, posiblesCombinaciones);
        }
        
        buscarCombinaciones(pLista, i+1, objetivo, listaActual, posiblesCombinaciones);
    }
}
=======
	// atributos
	private String nombre; 
	private ListaCartasMano mano;
	private ListaCartasMonton monton;
	private int escobas; 
	private int puntos;
	
	// constructora
	protected Jugador(String pNombre) {
		this.nombre = pNombre;
		this.mano = new ListaCartasMano();
		this.monton = new ListaCartasMonton();
		this.escobas = 0;
		this.puntos = 0;
	}
	
	// metodos
	
	/*
	 * A�ade un punto a un jugador
	 */
	public void añadirPunto() {
		this.puntos++;
	}
	
	/**
	 * Devuelve los puntos de un jugador
	 * @return
	 */
	public int obtenerPuntos() {
		return this.puntos;
	}
	
	/**
	 * Devuelve el nombre de un jugador
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}
	
	/**
	 * Devuelve la lista de cartas de la mano de un jugador
	 * @return
	 */
	protected ListaCartasMano getMano() {
		return this.mano;
	}
	
	/**
	 * A�ade una carta a un jugador en su mano, usando polimorfismo
	 * @param pCarta (una carta)
	 */
	public void recibirCarta(Carta pCarta) {
		this.mano.agregarCarta(pCarta);
	}
	
	/**
	 * metodo para saber si un jugador tiene su mano vacia (se usa para saber si se ha acabado la ronda)
	 * usando polimorfismo
	 * @return un booleano
	 */
	public boolean manoVacia() {
		return this.mano.estaVacia();
	}
	
	// metodo para agregar una lista de cartas a tu monton
	public void agregarCapturadas(ListaCartasMonton pLista) {
		this.monton.agregarCartas(pLista);
	}
	
	// metodo para a�adir una escoba al contador de escobas de un jugador
	public void añadirEscoba() {
		this.escobas++;
	}
	
	// metodo para obtener las escobas de un jugador
	public int obtenerEscobas() {
		return this.escobas;		
	}
	
	// metodo para contar las cartas de un monton de un jugador
	public int totalCartas() {
		return this.monton.tamaño();
	}
	
	// metodo para contar los oros de un monton de un jugador
	public int totalOros() {
		return this.monton.contarOros();
	}
	
	// metodo para contar los sietes de un monton de un jugador
	public int totalSietes() {
		return this.monton.contarSietes();
	}
	
	// metodo para comprobar si en un monton esta el 7 de oros
	public boolean tieneSieteDeOros() {
		return this.monton.tieneSieteDeOros();
	}
	
	// metodo para buscar combinaciones usando "backtracking"/recursividad
	
	protected void buscarCombinaciones(ListaCartasAuxiliar pLista, int i, int objetivo, ListaCartasMonton listaActual) {
		
		if (objetivo == 0) {
			pLista.añadirCombinacion(listaActual);
			return;
		}
		
		if (i >= pLista.tamaño()) return;
		Carta c = pLista.obtener(i);
		if (c.getValor() <= objetivo) {
			ListaCartasMonton nuevaLista = new ListaCartasMonton();
			nuevaLista.agregarCartas(listaActual);
			nuevaLista.agregarCarta(c);
			buscarCombinaciones(pLista, i+1, objetivo - c.getValor(), nuevaLista);	
		}
		
		buscarCombinaciones(pLista, i+1, objetivo, listaActual);
	}
	
	
	
	// METODOS ABSTRACTOS
	
	// metodo que derivan a las hijas para elegir la carta (la persona la elige manualmente y la IA lo elije aleatoriamente)
	public abstract Carta elegirCarta();
	
	// metodo que derivan a las hijas para elegir la captura de la mesa 
	public abstract ListaCartasMonton elegirCaptura(ListaCartasMesa mesa, Carta pCarta);
}
>>>>>>> branch 'master' of https://github.com/Wertoxdd/Los-Barrenderos-.git
