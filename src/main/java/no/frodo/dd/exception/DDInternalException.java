package no.frodo.dd.exception;

public class DDInternalException extends RuntimeException {
    public DDInternalException() {
        super("Something went wrong");
    }
}
