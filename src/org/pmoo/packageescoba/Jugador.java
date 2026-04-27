package org.pmoo.packageescoba;

public abstract class Jugador {

    private String nombre;
    private ListaCartasMano mano;
    private ListaCartasMonton monton;
    private int escobas;
    
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
    
    public abstract Carta elegirCarta();
    public abstract ListaCartasMonton elegirCaptura(ListaCartasMesa mesa, Carta pCarta);
    
    protected void buscarCombinaciones(ListaCartasAuxiliar pLista, int i, int objetivo, 
            ListaCartasMonton listaActual, ListaCartasCombinaciones posiblesCombinaciones) {
        
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
