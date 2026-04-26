package org.pmoo.packageescoba;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;

/**
 * Clase Mesa - Controla el flujo completo del juego de Escoba del 15
 * Implementa el patrón Singleton (solo una instancia de Mesa)
 */
public class Mesa {
    
    // ==================== ATRIBUTOS ====================
    
    // Lista que contiene todos los jugadores (Persona + IA)
    private ListaJugadores jugadores;
    
    // Cartas que están actualmente sobre la mesa
    private ListaCartasMesa cartasMesa;
    
    // Instancia única de Mesa (patrón Singleton)
    private static Mesa miMesa = null;
    
    // Puntos de la ronda actual
    private int puntosJugador = 0;
    private int puntosIA = 0;
    
    // Puntos acumulados de todas las rondas (para el ranking)
    private int puntosAcumuladosJugador = 0;
    private int puntosAcumuladosIA = 0;
    
    // Nombre del archivo donde se guarda el ranking
    private static final String ficheroRanking = "ranking.txt";
    
    
    // ==================== CONSTRUCTORA ====================
    
    /**
     * Constructora privada (Singleton)
     * Inicializa los jugadores y la mesa
     */
    private Mesa() {
        jugadores = new ListaJugadores();
        jugadores.agregarJugador(new JugadorPersona());  // Jugador humano
        jugadores.agregarJugador(new JugadorIA());       // Jugador IA
        cartasMesa = new ListaCartasMesa();
        puntosJugador = 0;
        puntosIA = 0;
        puntosAcumuladosJugador = 0;
        puntosAcumuladosIA = 0;
    }
    
    
    // ==================== PATRÓN SINGLETON ====================
    
    /**
     * Devuelve la única instancia de Mesa
     * Si no existe, la crea primero
     */
    public static Mesa getMesa() {
        if (miMesa == null) {
            miMesa = new Mesa();
        }
        return miMesa;
    }
    
    
    // ==================== MÉTODOS DE REPARTO ====================
    
    /**
     * Reparte cartas a los jugadores y a la mesa
     * @param esRepartoInicial true si es el primer reparto de la ronda
     */
    private void repartirCartas(boolean esRepartoInicial) {
        Mazo mazo = Mazo.getMazo();
        
        // En el reparto inicial, se ponen 4 cartas en la mesa
        if (esRepartoInicial) {
            for (int i = 0; i < 4; i++) {
                cartasMesa.agregarCarta(mazo.darCarta());
            }
        }
        
        // Se reparten 3 cartas a cada jugador
        for (int i = 0; i < jugadores.tamaño(); i++) {
            Jugador j = jugadores.obtenerJugador(i);
            for (int k = 0; k < 3; k++) {
                j.recibirCarta(mazo.darCarta());
            }
        }
    }
    
    
    // ==================== MÉTODOS DE JUEGO ====================
    
    /**
     * Comprueba si hay escoba (mesa vacía después de captura)
     * @return true si la mesa está vacía
     */
    private boolean esEscoba() {
        return cartasMesa.estaVacia();
    }
    
    /**
     * Gestiona el turno de un jugador
     * @param pJugador El jugador cuyo turno es
     * @param ultimoEnCapturar El último jugador que capturó cartas
     * @return El jugador que acaba de capturar (o el mismo si no capturó)
     */
    private Jugador turnoJugador(Jugador pJugador, Jugador ultimoEnCapturar) {
        // Mostrar información del turno
        System.out.println("\n================| TURNO DE " + pJugador.getNombre() + " |================");
        System.out.println("Cartas restantes en el mazo: " + Mazo.getMazo().tamaño());
        System.out.println("\nCartas en la mesa:");
        
        // Mostrar cartas de la mesa (sin usar iterador público)
        for (int i = 0; i < cartasMesa.tamaño(); i++) {
            System.out.print(cartasMesa.obtenerCarta(i).toString() + "  ");
        }
        System.out.println();
        
        // El jugador elige una carta de su mano
        Carta cartaElegida = pJugador.elegirCarta();
        
        // Comprueba si se puede sumar 15 con cartas de la mesa
        if (cartasMesa.sumaQuince(cartaElegida)) {
            System.out.println("\nCombinaciones posibles: ");
            
            // El jugador elige qué cartas capturar
            ListaCartasMonton cartasCapturadas = pJugador.elegirCaptura(cartasMesa, cartaElegida);
            
            // Eliminar cartas capturadas de la mesa (desde el final para no desfasar índices)
            for (int i = cartasCapturadas.tamaño() - 1; i >= 0; i--) {
                Carta c = cartasCapturadas.obtenerCarta(i);
                cartasMesa.eliminarCarta(c);
            }
            
            // Añadir la carta jugada al montón de capturadas
            cartasCapturadas.agregarCarta(cartaElegida);
            pJugador.agregarCapturadas(cartasCapturadas);
            ultimoEnCapturar = pJugador;
            
            System.out.println("\nCartas en tu montón: " + pJugador.totalCartas());
            
            // Si la mesa quedó vacía, es escoba
            if (esEscoba()) {
                pJugador.añadirEscoba();
                System.out.println("¡ESCOBA!");
            }
        } else {
            // No hay captura, la carta se queda en la mesa
            cartasMesa.agregarCarta(cartaElegida);
            System.out.println("Sin captura. El " + cartaElegida.getValor() + " de " + 
                cartaElegida.getPalo() + " se añade a la mesa.");
        }
        
        return ultimoEnCapturar;
    }
    
    
    // ==================== PUNTUACIÓN ====================
    
    /**
     * Calcula los puntos de cada jugador al final de la ronda
     * Reglas de puntuación de la Escoba:
     * - 1 punto por más cartas
     * - 1 punto por más oros
     * - 1 punto por más sietes
     * - 1 punto por tener el 7 de oros
     * - 1 punto por cada escoba
     */
    private void contarPuntos() {
        Jugador j1 = jugadores.obtenerJugador(0);  // Jugador humano
        Jugador j2 = jugadores.obtenerJugador(1);  // IA
        
        // Reiniciar puntos de la ronda
        puntosJugador = 0;
        puntosIA = 0;
        
        // 1. Más cartas (si hay empate, nadie suma)
        if (j1.totalCartas() > j2.totalCartas()) {
            puntosJugador += 1;
        } else if (j2.totalCartas() > j1.totalCartas()) {
            puntosIA += 1;
        }
        
        // 2. Más oros
        if (j1.totalOros() > j2.totalOros()) {
            puntosJugador += 1;
        } else if (j2.totalOros() > j1.totalOros()) {
            puntosIA += 1;
        }
        
        // 3. Más sietes
        if (j1.totalSietes() > j2.totalSietes()) {
            puntosJugador += 1;
        } else if (j2.totalSietes() > j1.totalSietes()) {
            puntosIA += 1;
        }
        
        // 4. 7 de oros (cada jugador suma si lo tiene)
        if (j1.tieneSieteDeOros()) {
            puntosJugador += 1;
        }
        if (j2.tieneSieteDeOros()) {
            puntosIA += 1;
        }
        
        // 5. Escobas (1 punto por cada escoba)
        puntosJugador += j1.obtenerEscobas();
        puntosIA += j2.obtenerEscobas();
    }
    
    
    // ==================== MOSTRAR RESULTADOS ====================
    
    /**
     * Muestra por pantalla los resultados de la ronda
     */
    private void mostrarResultados() {
        System.out.println("\n================| RESULTADO FINAL |================");
        
        // Resultados del jugador humano
        System.out.println("\n" + jugadores.obtenerJugador(0).getNombre() + ":");
        System.out.println("  Cartas:      " + jugadores.obtenerJugador(0).totalCartas());
        System.out.println("  Oros:        " + jugadores.obtenerJugador(0).totalOros());
        System.out.println("  7s:          " + jugadores.obtenerJugador(0).totalSietes());
        System.out.println("  7 de Oros:   " + jugadores.obtenerJugador(0).tieneSieteDeOros());
        System.out.println("  Escobas:     " + jugadores.obtenerJugador(0).obtenerEscobas());
        System.out.println("  PUNTOS:      " + puntosJugador);
        
        // Resultados de la IA
        System.out.println("\n" + jugadores.obtenerJugador(1).getNombre() + ":");
        System.out.println("  Cartas:      " + jugadores.obtenerJugador(1).totalCartas());
        System.out.println("  Oros:        " + jugadores.obtenerJugador(1).totalOros());
        System.out.println("  7s:          " + jugadores.obtenerJugador(1).totalSietes());
        System.out.println("  7 de Oros:   " + jugadores.obtenerJugador(1).tieneSieteDeOros());
        System.out.println("  Escobas:     " + jugadores.obtenerJugador(1).obtenerEscobas());
        System.out.println("  PUNTOS:      " + puntosIA);
        
        System.out.println("================================================");
        
        // Anunciar ganador de la ronda
        if (puntosJugador > puntosIA) {
            System.out.println("GANADOR DE LA RONDA: " + jugadores.obtenerJugador(0).getNombre());
        } else if (puntosJugador < puntosIA) {
            System.out.println("GANADOR DE LA RONDA: " + jugadores.obtenerJugador(1).getNombre());
        } else {
            System.out.println("EMPATE");
        }
    }
    
    
    // ==================== GESTIÓN DEL RANKING ====================
    
    /**
     * Carga los puntos acumulados desde el archivo ranking.txt
     */
    private void cargarRanking() {
        try {
            Scanner sc = new Scanner(new File(ficheroRanking));
            puntosAcumuladosJugador = sc.nextInt();
            puntosAcumuladosIA = sc.nextInt();
            sc.close();
        } catch (Exception e) {
            // Si no existe el archivo, empezar desde 0
            puntosAcumuladosJugador = 0;
            puntosAcumuladosIA = 0;
        }
    }
    
    /**
     * Guarda los puntos acumulados en el archivo ranking.txt
     */
    private void guardarRanking() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(ficheroRanking)); 
            pw.println(puntosAcumuladosJugador);
            pw.println(puntosAcumuladosIA);
            pw.close();
        } catch (Exception e) {
            System.out.println("Error al guardar el ranking.");
        }
    }
    
    /**
     * Muestra el ranking acumulado de todas las partidas
     */
    private void mostrarRanking() {
        System.out.println("\n================| RANKING |================");
        System.out.println(jugadores.obtenerJugador(0).getNombre() + ": " + puntosAcumuladosJugador + " puntos.");
        System.out.println(jugadores.obtenerJugador(1).getNombre() + ": " + puntosAcumuladosIA + " puntos.");
        
        if (puntosAcumuladosJugador >= 15) {
            System.out.println("🏆 GANADOR DE LA PARTIDA: " + jugadores.obtenerJugador(0).getNombre());
        } else if (puntosAcumuladosIA >= 15) {
            System.out.println("🏆 GANADOR DE LA PARTIDA: " + jugadores.obtenerJugador(1).getNombre());
        }
    }
    
    
    // ==================== MÉTODO PRINCIPAL DEL JUEGO ====================
    
    /**
     * Método principal que controla todo el flujo de la partida
     * Se juega hasta que un jugador alcance 15 puntos acumulados
     */
    public void jugarPartida() {
        // Cargar ranking previo si existe
        cargarRanking();
        
        // Bucle principal: se juega hasta que alguien llegue a 15 puntos
        while (puntosAcumuladosJugador < 15 && puntosAcumuladosIA < 15) {
            
            // --- INICIO DE RONDA ---
            
            // Reiniciar estado de la ronda
            cartasMesa = new ListaCartasMesa();
            jugadores = new ListaJugadores();
            jugadores.agregarJugador(new JugadorPersona());
            jugadores.agregarJugador(new JugadorIA());
            puntosJugador = 0;
            puntosIA = 0;
            
            // Preparar el mazo
            Mazo mazo = Mazo.getMazo();
            mazo.resetear();           // Vaciar mazo anterior
            mazo.generarMazo();        // Crear 40 cartas nuevas
            mazo.barajarMazo();        // Mezclar cartas
            repartirCartas(true);      // Reparto inicial (4 en mesa, 3 por jugador)
            
            // Variable para trackear quién capturó último (importante para el final)
            Jugador ultimoEnCapturar = jugadores.obtenerJugador(0);
            
            // --- BUCLE DE TURNOS ---
            
            // Se juega hasta que todos los jugadores se queden sin cartas Y el mazo esté vacío
            while (!jugadores.todosConSusManosVacias() || !mazo.estaVacio()) {
                
                // Cada jugador hace su turno en orden
                for (int i = 0; i < jugadores.tamaño(); i++) {
                    Jugador j = jugadores.obtenerJugador(i);
                    ultimoEnCapturar = turnoJugador(j, ultimoEnCapturar);
                }
                
                // Si todos se quedan sin cartas pero el mazo no está vacío, repartir más
                if (jugadores.todosConSusManosVacias() && !mazo.estaVacio()) {
                    repartirCartas(false);  // false = solo a jugadores, no a la mesa
                }
            }
            
            // --- FIN DE RONDA ---
            
            // Las cartas que queden en la mesa van para el último que capturó
            ListaCartasMonton restoDeLaMesa = new ListaCartasMonton();
            for (int i = cartasMesa.tamaño() - 1; i >= 0; i--) {
                restoDeLaMesa.agregarCarta(cartasMesa.obtenerCarta(i));
            }
            ultimoEnCapturar.agregarCapturadas(restoDeLaMesa);
            
            // Calcular y mostrar resultados
            contarPuntos();
            mostrarResultados();
            
            // Actualizar ranking acumulado
            puntosAcumuladosJugador += puntosJugador;
            puntosAcumuladosIA += puntosIA;
            guardarRanking();
            mostrarRanking();
        }
        
        System.out.println("\n🎉 ¡PARTIDA TERMINADA! 🎉");
    }
}
