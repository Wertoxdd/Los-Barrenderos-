package org.pmoo.packageescoba;

import java.util.Random;

/**
 *
 * JugadorIA: Jugador controlado por el ordenador.
 *
 * Estrategia: de todas las capturas posibles con las cartas de su mano, elige la que más vale
 * (escoba, 7 de oros, sietes, oros y número de cartas). Si no puede capturar, se descarta de la carta
 * que menos le importa perder.
 *
 */
public class JugadorIA extends Jugador {

	// ==============   Constantes de la estrategia   ==============
	private static final int VALOR_ESCOBA = 100;
	private static final int VALOR_SIETE_DE_OROS = 30;
	private static final int VALOR_SIETE = 10;
	private static final int VALOR_ORO = 3;
	private static final int VALOR_CARTA = 1;

    private Random random;

    public JugadorIA() {
        this("IA");
    }

    public JugadorIA(String pNombre) {
        super(pNombre);
        random = new Random();
    }

    /**
     *
     * Elegir carta sin conocer la mesa: juega una carta al azar.
     *
     */
    @Override
    public Carta elegirCarta() {
        int i = random.nextInt(getMano().tamaño());
        return jugarCarta(i);
    }

    /**
     *
     * Elegir carta conociendo la mesa: prueba cada carta de la mano y juega la que consigue la captura más valiosa.
     * Si ninguna captura, se descarta de la carta menos valiosa.
     *
     * @param pMesa: Recibe las cartas que hay en la mesa.
     * @return la carta elegida.
     */
    @Override
    public Carta elegirCarta(ListaCartasMesa pMesa) {
        ListaCartasMano mano = getMano();
        int mejorIndice = -1;
        int mejorValor = -1;

        for (int i = 0; i < mano.tamaño(); i++) {
            Carta c = mano.consultarCarta(i);
            ListaCartasMonton captura = mejorCaptura(pMesa, c);
            if (captura != null) {
                int valor = valorCaptura(captura, c, pMesa.tamaño());
                if (valor > mejorValor) {
                    mejorValor = valor;
                    mejorIndice = i;
                }
            }
        }

        if (mejorIndice == -1) {
            // Sin captura posible: descartar la carta que menos vale
            int menorValor = Integer.MAX_VALUE;
            for (int i = 0; i < mano.tamaño(); i++) {
                int valor = valorCarta(mano.consultarCarta(i)) * 100 + mano.consultarCarta(i).getValor();
                if (valor < menorValor) {
                    menorValor = valor;
                    mejorIndice = i;
                }
            }
        }
        return jugarCarta(mejorIndice);
    }

    /**
     *
     * Elige, de entre todas las combinaciones que suman 15 con la carta jugada, la más valiosa.
     *
     */
    @Override
    public ListaCartasMonton elegirCaptura(ListaCartasMesa mesa, Carta pCarta) {
        ListaCartasMonton captura = mejorCaptura(mesa, pCarta);
        if (captura != null) {
            return captura;
        }
        return new ListaCartasMonton();
    }

    // ==============   Métodos auxiliares   ==============

    private Carta jugarCarta(int pIndice) {
        Carta c = getMano().elegirCarta(pIndice);
        System.out.println("\n" + getNombre() + " juega: " + c.toString());
        return c;
    }

    /**
     *
     * Busca todas las combinaciones de la mesa que suman 15 con pCarta y devuelve la más valiosa (o null si no hay ninguna).
     *
     */
    private ListaCartasMonton mejorCaptura(ListaCartasMesa pMesa, Carta pCarta) {
        ListaCartasAuxiliar cartasMesa = new ListaCartasAuxiliar();
        pMesa.agregarCartas(cartasMesa);
        buscarCombinaciones(cartasMesa, 0, 15 - pCarta.getValor(), new ListaCartasMonton());

        ListaCartasCombinaciones combinaciones = getPosiblesCombinaciones();
        ListaCartasMonton mejor = null;
        int mejorValor = -1;
        for (int i = 0; i < combinaciones.size(); i++) {
            ListaCartasMonton combinacion = combinaciones.get(i);
            int valor = valorCaptura(combinacion, pCarta, pMesa.tamaño());
            if (valor > mejorValor) {
                mejorValor = valor;
                mejor = combinacion;
            }
        }
        return mejor;
    }

    /**
     *
     * Valor de una captura: las cartas de la mesa capturadas más la carta jugada, con un gran extra si deja la mesa vacía (escoba).
     *
     */
    private int valorCaptura(ListaCartasMonton pCombinacion, Carta pCartaJugada, int pTamañoMesa) {
        int valor = valorCarta(pCartaJugada);
        for (int i = 0; i < pCombinacion.tamaño(); i++) {
            valor += valorCarta(pCombinacion.obtenerCarta(i));
        }
        if (pCombinacion.tamaño() == pTamañoMesa) {
            valor += VALOR_ESCOBA;
        }
        return valor;
    }

    /**
     *
     * Valor de una carta para el recuento final: cuenta como carta, y suma si es oro, siete o el 7 de oros.
     *
     */
    private int valorCarta(Carta pCarta) {
        int valor = VALOR_CARTA;
        if (pCarta.getPalo() == Palo.Oros) {
            valor += VALOR_ORO;
        }
        if (pCarta.getValor() == 7) {
            valor += VALOR_SIETE;
            if (pCarta.getPalo() == Palo.Oros) {
                valor += VALOR_SIETE_DE_OROS;
            }
        }
        return valor;
    }
}
