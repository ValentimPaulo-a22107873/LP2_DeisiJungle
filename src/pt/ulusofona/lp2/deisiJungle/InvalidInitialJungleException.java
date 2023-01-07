package pt.ulusofona.lp2.deisiJungle;

public class InvalidInitialJungleException extends Exception {
    String message;
    boolean isInvalidPlayer;
    boolean isInvalidFood;

    public InvalidInitialJungleException(String message, boolean isInvalidPlayer, boolean isInvalidFood) {
        this.message = message;
        this.isInvalidPlayer = isInvalidPlayer;
        this.isInvalidFood = isInvalidFood;
    }

    public String getMessage() {
        return message;
    }

    public boolean isInvalidPlayer() {
        return isInvalidPlayer;
    }

    public boolean isInvalidFood() {
        return isInvalidFood;
    }
}
