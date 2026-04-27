package org.pmoo.packageescoba;

/**
 * Excepción personalizada que se lanza cuando se intenta acceder a una carta 
 * mediante un índice que no existe en la mano del jugador.
 */
public class IndiceCartaInvalidoException extends Exception {

    /**
     * Constructor por defecto que asigna un mensaje genérico de error.
     */
    public IndiceCartaInvalidoException() {
        super("Índice de carta inválido");
    }
    
    /**
     * Constructor que detalla el índice erróneo y el rango de valores permitidos.
     * 
     * @param indice El valor numérico introducido incorrectamente.
     * @param maximo El límite superior de los índices válidos.
     */
    public IndiceCartaInvalidoException(int indice, int maximo) {
        super("Índice de carta inválido: " + indice + 
              " (debe estar entre 1 y " + maximo + ")");
    }
}
