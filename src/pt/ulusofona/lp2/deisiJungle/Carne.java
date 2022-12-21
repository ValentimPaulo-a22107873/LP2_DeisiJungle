package pt.ulusofona.lp2.deisiJungle;

public class Carne extends Food{
    @Override
    char getIdentifier() {
        return 'c';
    }

    @Override
    String getImage() {
        return "meat.png";
    }
}
