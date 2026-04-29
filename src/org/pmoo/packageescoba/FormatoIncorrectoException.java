package org.pmoo.packageescoba;

public class FormatoIncorrectoException extends Exception {

    public FormatoIncorrectoException() {
        super("Error: Se ha introducido un carácter no numérico.");
    }

    public FormatoIncorrectoException(String mensaje) {
        super(mensaje);
    }
}
