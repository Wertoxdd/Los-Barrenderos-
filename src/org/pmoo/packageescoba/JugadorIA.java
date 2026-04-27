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
        mesa.agregarCartas(cartasMesa);

        buscarCombinaciones(cartasMesa, 0, 15 - pCarta.getValor(), new ListaCartasMonton());

        if (getPosiblesCombinaciones().size() > 0) {
            return getPosiblesCombinaciones().get(0);
        }
        return new ListaCartasMonton();
    }
}
