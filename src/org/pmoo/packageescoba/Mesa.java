package org.pmoo.packageescoba;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;


/**
 * 
 * Mesa: El cerebro del juego. Gestiona las rondas, los turnos, el recuento de puntos y el sistema del ranking. 
 *
 * Utiliza el Patrón Singleton porque es una MAE (no puede haber más de una mesa en todo el juego). 
 * 
 */
public class Mesa {
    
	// ==============   Atributos   ==============
    
	// cartasMesa: La lista de cartas que estan en el tablero de la mesa.
	private ListaCartasMesa cartasMesa;
	
	// miMesa: Instancia de la mesa.
    private static Mesa miMesa = null;
    
    // puntosFinalPartida: Puntuacion objetivo para ganar el juego.
    private static final int puntosFinalPartida = 15;
    
    // ficheroTexto: El nombre del archivo para guardar los resultados.
    private static final String ficheroTexto = "ranking.txt";
    
    
    // ==============   Constructora (privada)   ==============
    private Mesa() {
        ListaJugadores jugadores = ListaJugadores.getListaJugadores();
        jugadores.agregarJugador(new JugadorPersona("Jugador 1"));
        jugadores.agregarJugador(new JugadorPersona("Jugador 2"));
        jugadores.agregarJugador(new JugadorIA());
        cartasMesa = new ListaCartasMesa();
    }
    
    // ==============   Patrón Singleton   ==============
    public static Mesa getMesa() {
        if (miMesa == null) {
            miMesa = new Mesa();
        }
        return miMesa;
    }
    
    // ==============   Métodos   ==============
    
    
    /**
     * 
     * Metodo que reparte 3 cartas a cada jugador de la partida.
     * 
     * @param esRepartoInicial: Si es el inicio de la mano, pone 4 cartas en la mesa.
     * 
     */
    private void repartirCartas(boolean esRepartoInicial) {
        Mazo mazo = Mazo.getMazo();
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        
        if (esRepartoInicial) {
            for (int i = 0; i < 4; i++) {
                cartasMesa.agregarCarta(mazo.darCarta());
            }
        }
        
        for (int i = 0; i < lj.tamaño(); i++) {
            Jugador j = lj.obtenerJugador(i);
            for (int k = 0; k < 3; k++) {
                j.recibirCarta(mazo.darCarta());
            }
        }
    }
    
    
    /**
     * 
     * Método que comprueba si se ha efectuado una escoba.
     * 
     * @return booleano 
     * 
     */
    private boolean esEscoba() {
        return cartasMesa.estaVacia();
    }
    
    
    
    /**
     * 
     * Método que controla los turnos de los jugadores.
     * 
     * Muestra la mesa en consola, elige una carta, comprueba si suma 15 (captura), mueve las coartas al monton del jugador y verifica si es escoba (mesa vacía).
     * 
     * @param pJugador: Recibe un jugador como parametro, que es el que va a jugar el turno.
     * @param ultimoEnCapturar: Recibe el jugador que ha sido el ultimo en capturar de la mesa, para que al final de la partida se lleve las cartas que sobran en la mesa.
     * @return el último jugador que ha capturado de la mesa.
     * 
     */
    private Jugador turnoJugador(Jugador pJugador, Jugador ultimoEnCapturar) {
        System.out.println("\n================| TURNO DE " + pJugador.getNombre() + " |================");
        System.out.println("Cartas restantes en el mazo: " + Mazo.getMazo().tamaño());
        System.out.println("\nCartas en la mesa:");
        
        for (int i = 0; i < cartasMesa.tamaño(); i++) {
            System.out.print(cartasMesa.obtenerCarta(i).toString() + "  ");
        }
        System.out.println();
        
        Carta cartaElegida = pJugador.elegirCarta();
        
        if (cartasMesa.sumaQuince(cartaElegida)) {
            System.out.println("\nCombinaciones posibles: ");
            
            ListaCartasMonton cartasCapturadas = pJugador.elegirCaptura(cartasMesa, cartaElegida);
            
            for (int i = cartasCapturadas.tamaño() - 1; i >= 0; i--) {
                Carta c = cartasCapturadas.obtenerCarta(i);
                cartasMesa.eliminarCarta(c);
            }
            
            cartasCapturadas.agregarCarta(cartaElegida);
            pJugador.agregarCapturadas(cartasCapturadas);
            ultimoEnCapturar = pJugador;
            
            System.out.println("\nCartas en tu montón: " + pJugador.totalCartas());
            
            if (esEscoba()) {
                pJugador.añadirEscoba();
                System.out.println("¡ESCOBA!");
            }
        } else {
            cartasMesa.agregarCarta(cartaElegida);
            System.out.println("Sin captura. El " + cartaElegida.getValor() + " de " + 
                cartaElegida.getPalo() + " se añade a la mesa.");
        }
        
        return ultimoEnCapturar;
    }
    
    
    /**
     * 
     * Método para contar los puntos. Llama a ListaJugadores para sumar 1 punto si cumple las condiciones para sumar.
     * 
     * Una vez hecho el recuento 
     * 
     */
    private void contarPuntos() {
        ListaJugadores jugadores = ListaJugadores.getListaJugadores();
        for (int i = 0; i < jugadores.tamaño(); i++) {
            jugadores.obtenerJugador(i).resetearPuntosRonda();
        }

        Jugador jMasCartas = jugadores.jugadorConMasCartas();
        if (jMasCartas != null) {
            jMasCartas.añadirPuntosRonda(1);
        }

        Jugador jMasOros = jugadores.jugadorConMasOros();
        if (jMasOros != null) {
            jMasOros.añadirPuntosRonda(1);
        }

        Jugador jMasSietes = jugadores.jugadorConMasSietes();
        if (jMasSietes != null) {
            jMasSietes.añadirPuntosRonda(1);
        }

        Jugador jSieteOros = jugadores.jugadorConElSieteDeOros();
        if (jSieteOros != null) {
            jSieteOros.añadirPuntosRonda(1);
        }

        for (int i = 0; i < jugadores.tamaño(); i++) {
            Jugador j = jugadores.obtenerJugador(i);
            j.añadirPuntosRonda(j.obtenerEscobas());
            j.añadirPuntosAcumulados(j.getPuntosRonda());
        }
    }
    
    /**
     * 
     * Método para mostrar por consola los resultados de la ronda detalladamente.
     * 
     * Muestra los puntos, si ha habido empate o si hay un ganador de la ronda. 
     * 
     */
    private void mostrarResultados() {
        System.out.println("\n================| RESULTADO FINAL |================");
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        
        int maxPuntos = -1;
        Jugador ganadorRonda = null;
        boolean empate = false;

        for (int i = 0; i < lj.tamaño(); i++) {
            Jugador j = lj.obtenerJugador(i);
            System.out.println("\n" + j.getNombre() + ":");
            System.out.println("  Cartas:      " + j.totalCartas());
            System.out.println("  Oros:        " + j.totalOros());
            System.out.println("  7s:          " + j.totalSietes());
            System.out.println("  7 de Oros:   " + j.tieneSieteDeOros());
            System.out.println("  Escobas:     " + j.obtenerEscobas());
            System.out.println("  PUNTOS:      " + j.getPuntosRonda());

            if (j.getPuntosRonda() > maxPuntos) {
                maxPuntos = j.getPuntosRonda();
                ganadorRonda = j;
                empate = false;
            } else if (j.getPuntosRonda() == maxPuntos) {
                empate = true;
            }
        }
        
        System.out.println("\n================================================");
        
        if (empate) {
            System.out.println("EMPATE EN LA RONDA");
        } else {
            System.out.println("GANADOR DE LA RONDA: " + ganadorRonda.getNombre());
        }
    }
    
    
    /**
     * 
     * Método que accede a ranking.txt y recupera los puntos acumulados de cada jugador. 
     * 
     * Si el archivo no existe, se crea uno nuevo.
     * 
     */
    private void cargarRanking() {
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        try (Scanner sc = new Scanner(new File(ficheroTexto))) {
            for (int i = 0; i < lj.tamaño(); i++) {
                if (sc.hasNextInt()) {
                    lj.obtenerJugador(i).añadirPuntosAcumulados(sc.nextInt());
                }
            }
        } catch (Exception e) {
            System.out.println("No se encontró archivo de ranking. Iniciando desde 0.");
        }
    }
    
    
    /**
     * 
     * Método para guardar el ranking usando PrintWriter.
     * 
     */
    private void guardarRanking() {
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        try (PrintWriter pw = new PrintWriter(new FileWriter(ficheroTexto))) { 
            for (int i = 0; i < lj.tamaño(); i++) {
                pw.println(lj.obtenerJugador(i).getPuntosAcumulados());
            }
        } catch (Exception e) {
            System.out.println("Error al guardar el ranking.");
        }
    }
    
    
    /**
     * 
     * Método para mostrar el ranking por pantalla, con el ganador de la partida y los puntos que ha hecho cada jugador.
     * 
     */
    private void mostrarRanking() {
        System.out.println("\n================| RANKING |================");
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        Jugador ganadorPartida = null;

        for (int i = 0; i < lj.tamaño(); i++) {
            Jugador j = lj.obtenerJugador(i);
            System.out.println(j.getNombre() + ": " + j.getPuntosAcumulados() + " puntos.");
            if (j.getPuntosAcumulados() >= puntosFinalPartida) {
                if (ganadorPartida == null || j.getPuntosAcumulados() > ganadorPartida.getPuntosAcumulados()) {
                    ganadorPartida = j;
                }
            }
        }
        
        if (ganadorPartida != null) {
            System.out.println("\n¡GANADOR DE LA PARTIDA: " + ganadorPartida.getNombre() + "!");
        }
    }
    
    
    /**
     * 
     * Método que controla el bucle principal del juego, reiniciando el mazo y las rondas hasta que un jugador gana (llega a 15 puntos).
     * 
     */
    public void jugarPartida() {
        cargarRanking();
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        
        boolean alguienHaGanado = false;
        while (!alguienHaGanado) {
            
            cartasMesa = new ListaCartasMesa();
            
            Mazo mazo = Mazo.getMazo();
            mazo.resetear();
            mazo.generarMazo();
            mazo.barajarMazo();
            repartirCartas(true);
            
            Jugador ultimoEnCapturar = lj.obtenerJugador(0);
            
            while (!lj.todosConSusManosVacias() || !mazo.estaVacio()) {
                
                for (int i = 0; i < lj.tamaño(); i++) {
                    Jugador j = lj.obtenerJugador(i);
                    ultimoEnCapturar = turnoJugador(j, ultimoEnCapturar);
                }
                
                if (lj.todosConSusManosVacias() && !mazo.estaVacio()) {
                    repartirCartas(false);
                }
            }
            
            ListaCartasMonton restoDeLaMesa = new ListaCartasMonton();
            for (int i = cartasMesa.tamaño() - 1; i >= 0; i--) {
                restoDeLaMesa.agregarCarta(cartasMesa.obtenerCarta(i));
            }
            ultimoEnCapturar.agregarCapturadas(restoDeLaMesa);
            
            contarPuntos();
            mostrarResultados();
            guardarRanking();
            mostrarRanking();

            for (int i = 0; i < lj.tamaño(); i++) {
                if (lj.obtenerJugador(i).getPuntosAcumulados() >= puntosFinalPartida) {
                    alguienHaGanado = true;
                }
            }
        }
        
        System.out.println("\n¡PARTIDA TERMINADA!");
    }
}
