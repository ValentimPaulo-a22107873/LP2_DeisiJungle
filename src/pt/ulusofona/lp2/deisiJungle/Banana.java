package pt.ulusofona.lp2.deisiJungle;

public class Banana extends Food{

    public Banana() {
    }

    @Override
    String getName() {
        return "Banana";
    }

    @Override
    char getIdentifier() {
        return 'b';
    }

    @Override
    String getImage() {
        return "bananas.png";
    }
}
