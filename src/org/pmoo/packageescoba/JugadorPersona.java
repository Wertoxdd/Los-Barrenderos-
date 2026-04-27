package org.pmoo.packageescoba;

public class JugadorPersona extends Jugador {
    
    public JugadorPersona() {
        super("Jugador 1");
    }
    
    @Override
    public Carta elegirCarta() {
        System.out.println("\nTu mano: ");
        
        while (true) {
            try {
                return Teclado.leerCarta(getMano());
            } catch (IndiceCartaInvalidoException e) {
                System.out.println("XX " + e.getMessage());
                System.out.println("Por favor, inténtalo de nuevo.\n");
            }
        }
    }
    
    @Override
    public ListaCartasMonton elegirCaptura(ListaCartasMesa mesa, Carta pCarta) {
        ListaCartasAuxiliar cartasMesa = new ListaCartasAuxiliar();
        ListaListas posiblesCombinaciones = new ListaListas();
        
        mesa.agregarCartas(cartasMesa);
        
        // ✅ Usar método heredado de Jugador (sin duplicar código)
        buscarCombinaciones(cartasMesa, 0, 15 - pCarta.getValor(), 
                           new ListaCartasMonton(), posiblesCombinaciones);
        
        while (true) {
            try {
                return Teclado.leerCaptura(posiblesCombinaciones);
            } catch (IndiceCombinacionInvalidoException e) {
                System.out.println("XX " + e.getMessage());
                System.out.println("Por favor, inténtalo de nuevo.\n");
            }
        }
    }
}
