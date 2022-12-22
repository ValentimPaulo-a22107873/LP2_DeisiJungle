package pt.ulusofona.lp2.deisiJungle;

public class Mushrooms extends Food {

    public Mushrooms() {
    }

    @Override
    String getName() {
        return "Cogumelo";
    }

    @Override
    char getIdentifier() {
        return 'm';
    }

    @Override
    String getImage() {
        return "mushroom.png";
    }
}
