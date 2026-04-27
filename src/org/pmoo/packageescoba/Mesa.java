package org.pmoo.packageescoba;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;

/**
 * Clase Mesa - Controla el flujo completo del juego de Escoba del 15
 * 
 * ⚠️ DECISIÓN DE DISEÑO: Esta implementación está diseñada EXCLUSIVAMENTE
 * para partidas de 2 jugadores: 1 JugadorPersona (humano) contra 1 JugadorIA.
 * 
 * Esta decisión se tomó para:
 * - Simplificar la lógica de turnos y puntuación
 * - Mantener la coherencia con las reglas tradicionales de Escoba
 * - Facilitar la implementación del ranking acumulado
 * 
 * Para soportar más modos de juego (2 humanos, 2 IAs, +2 jugadores),
 * sería necesario refactorizar:
 * - El sistema de turnos (actualmente secuencial 1↔2)
 * - La lógica de puntuación (actualmente comparativa 1vs2)
 * - El almacenamiento del ranking (actualmente 2 entradas fijas)
 * 
 * Implementa el patrón Singleton (solo una instancia de Mesa)
 */
public class Mesa {
    
    // ==================== ATRIBUTOS ====================
    
    // ⚠️ DISEÑO: ListaJugadores contiene EXACTAMENTE 2 jugadores
    // Índice 0 = JugadorPersona, Índice 1 = JugadorIA
    private ListaJugadores jugadores;
    
    // Cartas que están actualmente sobre la mesa
    private ListaCartasMesa cartasMesa;
    
    // Instancia única de Mesa (patrón Singleton)
    private static Mesa miMesa = null;
    
    // ⚠️ DISEÑO: Constantes que reflejan la limitación a 2 jugadores
    private static final int NUM_JUGADORES = 2;
    private static final int INDICE_JUGADOR_HUMANO = 0;
    private static final int INDICE_JUGADOR_IA = 1;
    private static final int PUNTOS_OBJETIVO_PARTIDA = 15;
    
    // Archivo para persistencia del ranking
    private static final String FICHERO_RANKING = "ranking.txt";
    
    
    // ==================== CONSTRUCTORA ====================
    
    /**
     * Constructora privada (Singleton)
     * 
     * ⚠️ DISEÑO: Inicializa EXCLUSIVAMENTE 1 JugadorPersona + 1 JugadorIA
     * No hay parámetros para configurar otros modos de juego.
     */
    private Mesa() {
        jugadores = new ListaJugadores();
        
        // ⚠️ DISEÑO EXPLÍCITO: Orden fijo de jugadores
        jugadores.agregarJugador(new JugadorPersona());  // Índice 0 = Humano
        jugadores.agregarJugador(new JugadorIA());       // Índice 1 = IA
        
        cartasMesa = new ListaCartasMesa();
    }
    
    
    // ==================== PATRÓN SINGLETON ====================
    
    /**
     * Devuelve la única instancia de Mesa
     * @return La instancia única de Mesa
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
     * 
     * @param esRepartoInicial true si es el primer reparto de la ronda
     *                         (pone 4 cartas en mesa), false si es reparto
     *                         intermedio (solo a jugadores)
     */
    private void repartirCartas(boolean esRepartoInicial) {
        Mazo mazo = Mazo.getMazo();
        
        // En el reparto inicial, se ponen 4 cartas en la mesa
        if (esRepartoInicial) {
            for (int i = 0; i < 4; i++) {
                cartasMesa.agregarCarta(mazo.darCarta());
            }
        }
        
        // ⚠️ DISEÑO: Se reparten 3 cartas a CADA jugador (los 2)
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
     * 
     * @param pJugador El jugador cuyo turno es
     * @param ultimoEnCapturar El último jugador que capturó cartas
     * @return El jugador que acaba de capturar (para trackear quién se lleva 
     *         las cartas restantes al final)
     */
    private Jugador turnoJugador(Jugador pJugador, Jugador ultimoEnCapturar) {
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
     * 
     * ⚠️ DISEÑO: Lógica de puntuación diseñada para EXACTAMENTE 2 jugadores
     * Las comparaciones son 1vs1 (si hay empate, nadie suma en esas categorías)
     * 
     * Reglas de puntuación de la Escoba:
     * - 1 punto por más cartas (empate = 0 puntos para ambos)
     * - 1 punto por más oros (empate = 0 puntos para ambos)
     * - 1 punto por más sietes (empate = 0 puntos para ambos)
     * - 1 punto por tener el 7 de oros (cada jugador suma si lo tiene)
     * - 1 punto por cada escoba
     */
    private void contarPuntos() {
        // ⚠️ DISEÑO: Acceso por índice fijo (0 = Humano, 1 = IA)
        Jugador j1 = jugadores.obtenerJugador(INDICE_JUGADOR_HUMANO);
        Jugador j2 = jugadores.obtenerJugador(INDICE_JUGADOR_IA);
        
        // Reiniciar puntos de la ronda
        j1.resetearPuntosRonda();
        j2.resetearPuntosRonda();
        
        // 1. Más cartas (si hay empate, nadie suma)
        if (j1.totalCartas() > j2.totalCartas()) {
            j1.añadirPuntosRonda(1);
        } else if (j2.totalCartas() > j1.totalCartas()) {
            j2.añadirPuntosRonda(1);
        }
        
        // 2. Más oros
        if (j1.totalOros() > j2.totalOros()) {
            j1.añadirPuntosRonda(1);
        } else if (j2.totalOros() > j1.totalOros()) {
            j2.añadirPuntosRonda(1);
        }
        
        // 3. Más sietes
        if (j1.totalSietes() > j2.totalSietes()) {
            j1.añadirPuntosRonda(1);
        } else if (j2.totalSietes() > j1.totalSietes()) {
            j2.añadirPuntosRonda(1);
        }
        
        // 4. 7 de oros (cada jugador suma si lo tiene, no es comparativo)
        if (j1.tieneSieteDeOros()) {
            j1.añadirPuntosRonda(1);
        }
        if (j2.tieneSieteDeOros()) {
            j2.añadirPuntosRonda(1);
        }
        
        // 5. Escobas (1 punto por cada escoba)
        j1.añadirPuntosRonda(j1.obtenerEscobas());
        j2.añadirPuntosRonda(j2.obtenerEscobas());
        
        // Acumular puntos totales para el ranking
        j1.añadirPuntosAcumulados(j1.getPuntosRonda());
        j2.añadirPuntosAcumulados(j2.getPuntosRonda());
    }
    
    
    // ==================== MOSTRAR RESULTADOS ====================
    
    /**
     * Muestra por pantalla los resultados de la ronda
     * 
     * ⚠️ DISEÑO: Formato específico para 2 jugadores (Humano vs IA)
     */
    private void mostrarResultados() {
        Jugador j1 = jugadores.obtenerJugador(INDICE_JUGADOR_HUMANO);
        Jugador j2 = jugadores.obtenerJugador(INDICE_JUGADOR_IA);
        
        System.out.println("\n================| RESULTADO FINAL |================");
        
        // Resultados del jugador humano
        System.out.println("\n" + j1.getNombre() + ":");
        System.out.println("  Cartas:      " + j1.totalCartas());
        System.out.println("  Oros:        " + j1.totalOros());
        System.out.println("  7s:          " + j1.totalSietes());
        System.out.println("  7 de Oros:   " + j1.tieneSieteDeOros());
        System.out.println("  Escobas:     " + j1.obtenerEscobas());
        System.out.println("  PUNTOS:      " + j1.getPuntosRonda());
        
        // Resultados de la IA
        System.out.println("\n" + j2.getNombre() + ":");
        System.out.println("  Cartas:      " + j2.totalCartas());
        System.out.println("  Oros:        " + j2.totalOros());
        System.out.println("  7s:          " + j2.totalSietes());
        System.out.println("  7 de Oros:   " + j2.tieneSieteDeOros());
        System.out.println("  Escobas:     " + j2.obtenerEscobas());
        System.out.println("  PUNTOS:      " + j2.getPuntosRonda());
        
        System.out.println("================================================");
        
        // Anunciar ganador de la ronda
        if (j1.getPuntosRonda() > j2.getPuntosRonda()) {
            System.out.println("GANADOR DE LA RONDA: " + j1.getNombre());
        } else if (j1.getPuntosRonda() < j2.getPuntosRonda()) {
            System.out.println("GANADOR DE LA RONDA: " + j2.getNombre());
        } else {
            System.out.println("EMPATE");
        }
    }
    
    
    // ==================== GESTIÓN DEL RANKING ====================
    
    /**
     * Carga los puntos acumulados desde el archivo ranking.txt
     * 
     * ⚠️ DISEÑO: El archivo contiene EXACTAMENTE 2 líneas:
     * Línea 1: Puntos del Jugador Humano
     * Línea 2: Puntos de la IA
     */
    private void cargarRanking() {
        try {
            Scanner sc = new Scanner(new File(FICHERO_RANKING));
            jugadores.obtenerJugador(INDICE_JUGADOR_HUMANO).añadirPuntosAcumulados(sc.nextInt());
            jugadores.obtenerJugador(INDICE_JUGADOR_IA).añadirPuntosAcumulados(sc.nextInt());
            sc.close();
        } catch (Exception e) {
            // Si no existe el archivo, empezar desde 0
            System.out.println("No se encontró archivo de ranking. Iniciando desde 0.");
        }
    }
    
    /**
     * Guarda los puntos acumulados en el archivo ranking.txt
     * 
     * ⚠️ DISEÑO: El archivo contiene EXACTAMENTE 2 líneas
     */
    private void guardarRanking() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FICHERO_RANKING)); 
            pw.println(jugadores.obtenerJugador(INDICE_JUGADOR_HUMANO).getPuntosAcumulados());
            pw.println(jugadores.obtenerJugador(INDICE_JUGADOR_IA).getPuntosAcumulados());
            pw.close();
        } catch (Exception e) {
            System.out.println("Error al guardar el ranking.");
        }
    }
    
    /**
     * Muestra el ranking acumulado de todas las partidas
     * 
     * ⚠️ DISEÑO: Muestra EXACTAMENTE 2 jugadores
     */
    private void mostrarRanking() {
        Jugador j1 = jugadores.obtenerJugador(INDICE_JUGADOR_HUMANO);
        Jugador j2 = jugadores.obtenerJugador(INDICE_JUGADOR_IA);
        
        System.out.println("\n================| RANKING |================");
        System.out.println(j1.getNombre() + ": " + j1.getPuntosAcumulados() + " puntos.");
        System.out.println(j2.getNombre() + ": " + j2.getPuntosAcumulados() + " puntos.");
        
        if (j1.getPuntosAcumulados() >= PUNTOS_OBJETIVO_PARTIDA) {
            System.out.println("🏆 GANADOR DE LA PARTIDA: " + j1.getNombre());
        } else if (j2.getPuntosAcumulados() >= PUNTOS_OBJETIVO_PARTIDA) {
            System.out.println("🏆 GANADOR DE LA PARTIDA: " + j2.getNombre());
        }
    }
    
    
    // ==================== MÉTODO PRINCIPAL DEL JUEGO ====================
    
    /**
     * Método principal que controla todo el flujo de la partida
     * 
     * ⚠️ DISEÑO: La partida se juega hasta que un jugador alcance 15 puntos
     * acumulados. Está diseñado EXCLUSIVAMENTE para 2 jugadores.
     * 
     * Flujo:
     * 1. Cargar ranking previo
     * 2. Bucle de rondas hasta que alguien llegue a 15 puntos
     * 3. Cada ronda: repartir → turnos → contar puntos → guardar ranking
     * 4. Anunciar ganador de la partida
     */
    public void jugarPartida() {
        // Cargar ranking previo si existe
        cargarRanking();
        
        // Bucle principal: se juega hasta que alguien llegue a 15 puntos
        while (jugadores.obtenerJugador(INDICE_JUGADOR_HUMANO).getPuntosAcumulados() < PUNTOS_OBJETIVO_PARTIDA 
            && jugadores.obtenerJugador(INDICE_JUGADOR_IA).getPuntosAcumulados() < PUNTOS_OBJETIVO_PARTIDA) {
            
            // --- INICIO DE RONDA ---
            
            // Reiniciar estado de la ronda
            cartasMesa = new ListaCartasMesa();
            jugadores = new ListaJugadores();
            jugadores.agregarJugador(new JugadorPersona());  // Índice 0
            jugadores.agregarJugador(new JugadorIA());       // Índice 1
            
            // Preparar el mazo
            Mazo mazo = Mazo.getMazo();
            mazo.resetear();           // Vaciar mazo anterior
            mazo.generarMazo();        // Crear 40 cartas nuevas
            mazo.barajarMazo();        // Mezclar cartas
            repartirCartas(true);      // Reparto inicial (4 en mesa, 3 por jugador)
            
            // Variable para trackear quién capturó último (importante para el final)
            Jugador ultimoEnCapturar = jugadores.obtenerJugador(INDICE_JUGADOR_HUMANO);
            
            // --- BUCLE DE TURNOS ---
            
            // Se juega hasta que todos los jugadores se queden sin cartas Y el mazo esté vacío
            while (!jugadores.todosConSusManosVacias() || !mazo.estaVacio()) {
                
                // ⚠️ DISEÑO: Turnos secuenciales en orden de índice (0 → 1)
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
            
            // Guardar y mostrar ranking actualizado
            guardarRanking();
            mostrarRanking();
        }
        
        System.out.println("\n🎉 ¡PARTIDA TERMINADA! 🎉");
    }
}
