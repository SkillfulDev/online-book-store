package ua.chernonog.onlinebookstore.exception;

public class EntitySaveException extends RuntimeException {
    public EntitySaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
