package no.frodo.dd.exception;

public class IllegalPoemException extends RuntimeException {

    public IllegalPoemException() {
        super("Illegal poem");
    }
}
