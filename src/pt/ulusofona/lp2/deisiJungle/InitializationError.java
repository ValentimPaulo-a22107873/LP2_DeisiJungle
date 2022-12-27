package pt.ulusofona.lp2.deisiJungle;

public class InitializationError {

    private String message;

    public InitializationError(String message) {
        this.message = message;
    }

    String getMessage(){
        return this.message;
    }
}
