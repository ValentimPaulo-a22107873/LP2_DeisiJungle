package pt.ulusofona.lp2.deisiJungle;

public class Meat extends Food{

    public Meat(){

    }

    @Override
    String getName() {
        return "Carne";
    }

    @Override
    char getIdentifier() {
        return 'c';
    }

    @Override
    String getImage() {
        return "meat.png";
    }
}
