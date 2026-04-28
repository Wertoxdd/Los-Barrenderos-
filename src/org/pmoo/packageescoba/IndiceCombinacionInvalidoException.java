package org.pmoo.packageescoba;

/**
 * Excepción personalizada que se lanza cuando el usuario selecciona una 
 * combinación de captura inexistente entre las opciones presentadas.
 */
public class IndiceCombinacionInvalidoException extends Exception {

    /**
     * Constructor por defecto que asigna un mensaje genérico de error.
     */
    public IndiceCombinacionInvalidoException() {
        super("Indice de combinación inválido");
    }
    
    /**
     * Constructor que detalla el índice erroneo y la cantidad máxima de combinaciones disponibles.
     * 
     * @param indice: El valor numérico introducido incorrectamente por el usuario.
     * @param maximo: El número total de combinaciones entre las que se podía elegir.
     */
    public IndiceCombinacionInvalidoException(int indice, int maximo) {
        super("Indice de combinación inválido: " + indice + 
              " (debe estar entre 1 y " + maximo + ")");
    }
}
