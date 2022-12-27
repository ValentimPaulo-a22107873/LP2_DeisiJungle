package pt.ulusofona.lp2.deisiJungle;

public class Grass extends Food{

    public Grass() {
    }

    @Override
    String getName() {
        return "Erva";
    }

    @Override
    char getIdentifier() {
        return 'e';
    }

    @Override
    String getImage() {
        return "grass.png";
    }

    @Override
    String getTooltip() {
        return "Erva : +- 20 energia";
    }
}
