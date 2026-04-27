package org.pmoo.packageescoba;

public class JugadorPersona extends Jugador {
    
    public JugadorPersona(String pNombre) {
        super(pNombre);
    }
    @Override
    public Carta elegirCarta() {
        System.out.println("\nTu mano: ");

        try {
            return Teclado.leerCarta(getMano());
        } catch (IndiceCartaInvalidoException e) {
            System.out.println("XX " + e.getMessage());
            System.out.println("Por favor, inténtalo de nuevo.\n");
            return elegirCarta();
        }
    }
    
    @Override
    public ListaCartasMonton elegirCaptura(ListaCartasMesa mesa, Carta pCarta) {
        ListaCartasAuxiliar cartasMesa = new ListaCartasAuxiliar();
        mesa.agregarCartas(cartasMesa);

        buscarCombinaciones(cartasMesa, 0, 15 - pCarta.getValor(), new ListaCartasMonton());

        try {
            return Teclado.leerCaptura(getPosiblesCombinaciones());
        } catch (IndiceCombinacionInvalidoException e) {
            System.out.println("XX " + e.getMessage());
            System.out.println("Por favor, inténtalo de nuevo.\n");
            return elegirCaptura(mesa, pCarta);
        }
    }
}
