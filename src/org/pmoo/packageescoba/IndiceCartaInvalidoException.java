package org.pmoo.packageescoba;

public class IndiceCartaInvalidoException extends Exception {

    public IndiceCartaInvalidoException() {
        super("Índice de carta inválido");
    }
    
    public IndiceCartaInvalidoException(int indice, int maximo) {
        super("Índice de carta inválido: " + indice + 
              " (debe estar entre 1 y " + maximo + ")");
    }

}
