package pt.ulusofona.lp2.deisiJungle;

public class InvalidInitialJungleException extends Exception {
    String message;
    boolean playerInvalid;
    boolean foodInvalid;

    public InvalidInitialJungleException(String message, boolean playerInvalid, boolean foodInvalid) {
        this.message = message;
        this.playerInvalid = playerInvalid;
        this.foodInvalid = foodInvalid;
    }

    public String getMessage() {
        return message;
    }

    public boolean isPlayerValid() {
        return playerInvalid;
    }

    public boolean isFood() {
        return foodInvalid;
    }
}
