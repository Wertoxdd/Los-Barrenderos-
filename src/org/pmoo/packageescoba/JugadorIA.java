package org.pmoo.packageescoba;

import java.util.Random;

public class JugadorIA extends Jugador {
    
    private Random random;
    
    public JugadorIA() {
        super("IA");
        random = new Random();
    }
    
    @Override
    public Carta elegirCarta() {
        int i = random.nextInt(getMano().tamaño());
        Carta c = getMano().elegirCarta(i);
        System.out.println("\nLa IA juega: " + c.getValor() + " de " + c.getPalo());
        return c;
    }
    
    @Override
    public ListaCartasMonton elegirCaptura(ListaCartasMesa mesa, Carta pCarta) {
        ListaCartasAuxiliar cartasMesa = new ListaCartasAuxiliar();
        ListaCartasCombinaciones posiblesCombinaciones = new ListaCartasCombinaciones();
        
        mesa.agregarCartas(cartasMesa);
        
        buscarCombinaciones(cartasMesa, 0, 15 - pCarta.getValor(), 
                           new ListaCartasMonton(), posiblesCombinaciones);
        
        if (posiblesCombinaciones.size() > 0) {
            return posiblesCombinaciones.get(0);
        }
        return new ListaCartasMonton();
    }
}
