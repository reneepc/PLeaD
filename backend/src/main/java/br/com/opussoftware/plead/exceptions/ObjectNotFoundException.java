package br.com.opussoftware.plead.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(Long id, Class classe) {
        super("Objeto não encontrado: " + id + ", tipo: " + classe.getSimpleName());
    }

    public ObjectNotFoundException(String str, Class classe) {
        super("Objeto não encontrado: " + str + ", tipo: " + classe.getSimpleName());
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
