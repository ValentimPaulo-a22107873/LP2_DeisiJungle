package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Elephant extends Herbivoro {

    public Elephant() {
    }

    @Override
    String getName() {
        return "Elefante";
    }

    @Override
    String getImage() {
        return "elephant.png";
    }

    @Override
    char getIdentifier() {
        return 'E';
    }


    @Override
    int getInitialEnergy() {
        return 180;
    }

    @Override
    int getEnergyConsume() {
        return 4;
    }

    @Override
    int getEnrgyEarnedByRest() {
        return 10;
    }

    @Override
    int[] getSpeed() {
        return new int[]{1,6};
    }

    @Override
    char getType() {
        return 'h';
    }

}
