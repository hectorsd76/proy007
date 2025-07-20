package es.cic.curso2025.proy007;

public class EntradaIdNotNullException extends RuntimeException{
    public EntradaIdNotNullException() {

    }

    public EntradaIdNotNullException(String message) {
        super(message);
    }

    public EntradaIdNotNullException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
