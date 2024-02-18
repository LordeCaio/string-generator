package br.com.macedocaio.application.exceptions;

public class InvalidStringLengthException extends StringGeneratorException {

    public InvalidStringLengthException(String message, Object... args) {
        this.message = message.formatted(args);
    }
}
