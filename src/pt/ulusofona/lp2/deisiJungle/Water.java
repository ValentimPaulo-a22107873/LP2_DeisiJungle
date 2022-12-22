package pt.ulusofona.lp2.deisiJungle;

public class Water extends Food{

    public Water(){
    }

    @Override
    String getName() {
        return "Agua";
    }

    @Override
    char getIdentifier() {
        return 'a';
    }

    @Override
    String getImage() {
        return "water.png";
    }
}
