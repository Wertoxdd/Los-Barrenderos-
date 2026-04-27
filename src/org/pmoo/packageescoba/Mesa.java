package org.pmoo.packageescoba;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Mesa {
    
    private ListaJugadores jugadores;
    private ListaCartasMesa cartasMesa;
    private static Mesa miMesa = null;
    
    private static final int jugador1 = 0;
    private static final int jugador2 = 1;
    private static final int PuntosFinalPartida = 15;
    private static final String ficheroTexto = "ranking.txt";
    
    private Mesa() {
        jugadores = new ListaJugadores();
        jugadores.agregarJugador(new JugadorPersona());
        jugadores.agregarJugador(new JugadorIA());
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
        
        if (esRepartoInicial) {
            for (int i = 0; i < 4; i++) {
                cartasMesa.agregarCarta(mazo.darCarta());
            }
        }
        
        for (int i = 0; i < jugadores.tamaño(); i++) {
            Jugador j = jugadores.obtenerJugador(i);
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
        Jugador j1 = jugadores.obtenerJugador(jugador1);
        Jugador j2 = jugadores.obtenerJugador(jugador2);
        
        j1.resetearPuntosRonda();
        j2.resetearPuntosRonda();
        
        if (j1.totalCartas() > j2.totalCartas()) {
            j1.añadirPuntosRonda(1);
        } else if (j2.totalCartas() > j1.totalCartas()) {
            j2.añadirPuntosRonda(1);
        }
        
        if (j1.totalOros() > j2.totalOros()) {
            j1.añadirPuntosRonda(1);
        } else if (j2.totalOros() > j1.totalOros()) {
            j2.añadirPuntosRonda(1);
        }
        
        if (j1.totalSietes() > j2.totalSietes()) {
            j1.añadirPuntosRonda(1);
        } else if (j2.totalSietes() > j1.totalSietes()) {
            j2.añadirPuntosRonda(1);
        }
        
        if (j1.tieneSieteDeOros()) {
            j1.añadirPuntosRonda(1);
        }
        if (j2.tieneSieteDeOros()) {
            j2.añadirPuntosRonda(1);
        }
        
        j1.añadirPuntosRonda(j1.obtenerEscobas());
        j2.añadirPuntosRonda(j2.obtenerEscobas());
        
        j1.añadirPuntosAcumulados(j1.getPuntosRonda());
        j2.añadirPuntosAcumulados(j2.getPuntosRonda());
    }
    
    private void mostrarResultados() {
        Jugador j1 = jugadores.obtenerJugador(jugador1);
        Jugador j2 = jugadores.obtenerJugador(jugador2);
        
        System.out.println("\n================| RESULTADO FINAL |================");
        
        System.out.println("\n" + j1.getNombre() + ":");
        System.out.println("  Cartas:      " + j1.totalCartas());
        System.out.println("  Oros:        " + j1.totalOros());
        System.out.println("  7s:          " + j1.totalSietes());
        System.out.println("  7 de Oros:   " + j1.tieneSieteDeOros());
        System.out.println("  Escobas:     " + j1.obtenerEscobas());
        System.out.println("  PUNTOS:      " + j1.getPuntosRonda());
        
        System.out.println("\n" + j2.getNombre() + ":");
        System.out.println("  Cartas:      " + j2.totalCartas());
        System.out.println("  Oros:        " + j2.totalOros());
        System.out.println("  7s:          " + j2.totalSietes());
        System.out.println("  7 de Oros:   " + j2.tieneSieteDeOros());
        System.out.println("  Escobas:     " + j2.obtenerEscobas());
        System.out.println("  PUNTOS:      " + j2.getPuntosRonda());
        
        System.out.println("================================================");
        
        if (j1.getPuntosRonda() > j2.getPuntosRonda()) {
            System.out.println("GANADOR DE LA RONDA: " + j1.getNombre());
        } else if (j1.getPuntosRonda() < j2.getPuntosRonda()) {
            System.out.println("GANADOR DE LA RONDA: " + j2.getNombre());
        } else {
            System.out.println("EMPATE");
        }
    }
    
    private void cargarRanking() {
        try {
            Scanner sc = new Scanner(new File(ficheroTexto));
            jugadores.obtenerJugador(jugador1).añadirPuntosAcumulados(sc.nextInt());
            jugadores.obtenerJugador(jugador2).añadirPuntosAcumulados(sc.nextInt());
            sc.close();
        } catch (Exception e) {
            System.out.println("No se encontró archivo de ranking. Iniciando desde 0.");
        }
    }
    
    private void guardarRanking() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(ficheroTexto)); 
            pw.println(jugadores.obtenerJugador(jugador1).getPuntosAcumulados());
            pw.println(jugadores.obtenerJugador(jugador2).getPuntosAcumulados());
            pw.close();
        } catch (Exception e) {
            System.out.println("Error al guardar el ranking.");
        }
    }
    
    private void mostrarRanking() {
        Jugador j1 = jugadores.obtenerJugador(jugador1);
        Jugador j2 = jugadores.obtenerJugador(jugador2);
        
        System.out.println("\n================| RANKING |================");
        System.out.println(j1.getNombre() + ": " + j1.getPuntosAcumulados() + " puntos.");
        System.out.println(j2.getNombre() + ": " + j2.getPuntosAcumulados() + " puntos.");
        
        if (j1.getPuntosAcumulados() >= PuntosFinalPartida) {
            System.out.println("GANADOR DE LA PARTIDA: " + j1.getNombre());
        } else if (j2.getPuntosAcumulados() >= PuntosFinalPartida) {
            System.out.println("GANADOR DE LA PARTIDA: " + j2.getNombre());
        }
    }
    
    public void jugarPartida() {
        cargarRanking();
        
        while (jugadores.obtenerJugador(jugador1).getPuntosAcumulados() < PuntosFinalPartida 
            && jugadores.obtenerJugador(jugador2).getPuntosAcumulados() < PuntosFinalPartida) {
            
            cartasMesa = new ListaCartasMesa();
            jugadores = new ListaJugadores();
            jugadores.agregarJugador(new JugadorPersona());
            jugadores.agregarJugador(new JugadorIA());
            
            Mazo mazo = Mazo.getMazo();
            mazo.resetear();
            mazo.generarMazo();
            mazo.barajarMazo();
            repartirCartas(true);
            
            Jugador ultimoEnCapturar = jugadores.obtenerJugador(jugador1);
            
            while (!jugadores.todosConSusManosVacias() || !mazo.estaVacio()) {
                
                for (int i = 0; i < jugadores.tamaño(); i++) {
                    Jugador j = jugadores.obtenerJugador(i);
                    ultimoEnCapturar = turnoJugador(j, ultimoEnCapturar);
                }
                
                if (jugadores.todosConSusManosVacias() && !mazo.estaVacio()) {
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
        }
        
        System.out.println("\n¡PARTIDA TERMINADA!");
    }
}
