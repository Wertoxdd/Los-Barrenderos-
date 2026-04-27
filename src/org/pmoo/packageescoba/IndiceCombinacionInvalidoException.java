package org.pmoo.packageescoba;

public class IndiceCombinacionInvalidoException extends Exception {

    public IndiceCombinacionInvalidoException() {
        super("Índice de combinación inválido");
    }
    
    public IndiceCombinacionInvalidoException(int indice, int maximo) {
        super("Índice de combinación inválido: " + indice + 
              " (debe estar entre 1 y " + maximo + ")");
    }

}
