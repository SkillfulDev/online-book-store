package ua.chernonog.onlinebookstore.exception;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException() {
    }

    public DuplicateEntityException(String message) {
        super(message);
    }
}
