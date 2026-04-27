package org.pmoo.packageescoba;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Mesa {
    
    private ListaCartasMesa cartasMesa;
    private static Mesa miMesa = null;
    
    private static final int PuntosFinalPartida = 15;
    private static final String ficheroTexto = "ranking.txt";
    
    private Mesa() {
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        lj.agregarJugador(new JugadorPersona("Jugador 1"));
        lj.agregarJugador(new JugadorPersona("Jugador 2"));
        lj.agregarJugador(new JugadorIA());
        cartasMesa = new ListaCartasMesa();
    }
    
    public static Mesa getMesa() {
        if (miMesa == null) {
            miMesa = new Mesa();
        }
        return miMesa;
    }
    
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
    
    private boolean esEscoba() {
        return cartasMesa.estaVacia();
    }
    
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
    
    private void contarPuntos() {
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        for (int i = 0; i < lj.tamaño(); i++) {
            lj.obtenerJugador(i).resetearPuntosRonda();
        }

        Jugador jMasCartas = lj.jugadorConMasCartas();
        if (jMasCartas != null) {
            jMasCartas.añadirPuntosRonda(1);
        }

        Jugador jMasOros = lj.jugadorConMasOros();
        if (jMasOros != null) {
            jMasOros.añadirPuntosRonda(1);
        }

        Jugador jMasSietes = lj.jugadorConMasSietes();
        if (jMasSietes != null) {
            jMasSietes.añadirPuntosRonda(1);
        }

        Jugador jSieteOros = lj.jugadorConElSieteDeOros();
        if (jSieteOros != null) {
            jSieteOros.añadirPuntosRonda(1);
        }

        for (int i = 0; i < lj.tamaño(); i++) {
            Jugador j = lj.obtenerJugador(i);
            j.añadirPuntosRonda(j.obtenerEscobas());
            j.añadirPuntosAcumulados(j.getPuntosRonda());
        }
    }
    
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
    
    private void mostrarRanking() {
        System.out.println("\n================| RANKING |================");
        ListaJugadores lj = ListaJugadores.getListaJugadores();
        Jugador ganadorPartida = null;

        for (int i = 0; i < lj.tamaño(); i++) {
            Jugador j = lj.obtenerJugador(i);
            System.out.println(j.getNombre() + ": " + j.getPuntosAcumulados() + " puntos.");
            if (j.getPuntosAcumulados() >= PuntosFinalPartida) {
                if (ganadorPartida == null || j.getPuntosAcumulados() > ganadorPartida.getPuntosAcumulados()) {
                    ganadorPartida = j;
                }
            }
        }
        
        if (ganadorPartida != null) {
            System.out.println("\n¡GANADOR DE LA PARTIDA: " + ganadorPartida.getNombre() + "!");
        }
    }
    
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
                if (lj.obtenerJugador(i).getPuntosAcumulados() >= PuntosFinalPartida) {
                    alguienHaGanado = true;
                }
            }
        }
        
        System.out.println("\n¡PARTIDA TERMINADA!");
    }
}
