package Excepciones;

public class UsernameEnUsoException extends Exception {
    public UsernameEnUsoException(String message) {
        super(message);
    }
}
