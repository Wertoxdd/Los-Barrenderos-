package org.pmoo.packageescoba;

public abstract class Jugador {
	// atributos
	private String nombre; 
	private ListaCartasMano mano;
	private ListaCartasMonton monton;
	private int escobas; 
	
	// constructora
	protected Jugador(String pNombre) {
		this.nombre = pNombre;
		this.mano = new ListaCartasMano();
		this.monton = new ListaCartasMonton();
		this.escobas = 0;
	}
	
	// metodos
	
	
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
	 * Añade una carta a un jugador en su mano, usando polimorfismo
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
	
	// metodo para añadir una escoba al contador de escobas de un jugador
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
	
	// METODOS ABSTRACTOS
	
	// metodo que derivan a las hijas para elegir la carta (la persona la elige manualmente y la IA lo elije aleatoriamente)
	public abstract Carta elegirCarta();
	
	// metodo que derivan a las hijas para elegir la captura de la mesa 
	public abstract ListaCartasMonton elegirCaptura(ListaCartasMesa mesa, Carta pCarta);
}