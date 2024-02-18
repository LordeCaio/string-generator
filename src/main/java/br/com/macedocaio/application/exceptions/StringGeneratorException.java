package br.com.macedocaio.application.exceptions;

public abstract class StringGeneratorException extends RuntimeException {

    protected String message;

    public StringGeneratorException() {
        this.message = "Default Error Message";
    }

    public StringGeneratorException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
