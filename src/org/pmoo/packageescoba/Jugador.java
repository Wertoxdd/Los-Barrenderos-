package org.pmoo.packageescoba;

public abstract class Jugador {
	//atributos
	private ListaCartasMano mano;
	private ListaCartasBarridas barridas;
	private int puntos;
	protected  Jugador(int pPuntos) {
		this.mano = new ListaCartasMano();
		this.barridas = new ListaCartasBarridas();
		this.puntos = pPuntos;
	}
	public void sumarPuntos(int pPuntos) {
		this.puntos = this.puntos + pPuntos;
	}
	protected ListaCartasMano getMano() {
		return this.mano;
	}
	protected ListaCartasBarridas getBarridas() {
		return this.barridas;
	}
	protected int getPuntos() {
		return this.puntos;
	}
	public void recibirCarta(Carta pCarta) {
		 this.mano.agregar(pCarta);
	}
	public abstract void jugarTurno();
}
