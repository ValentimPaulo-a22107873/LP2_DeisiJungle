package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Turtle extends Omnivero{


    public Turtle() {
    }


    @Override
    String getName() {
        return "Tartaruga";
    }

    @Override
    String getImage() {
        return "turtle.png";
    }

    @Override
    char getIdentifier() {
        return 'T';
    }

    @Override
    int getInitialEnergy() {
        return 150;
    }

    @Override
    int getEnergyConsume() {
        return 1;
    }

    @Override
    int getEnrgyEarnedByRest() {
        return 5;
    }

    @Override
    int[] getSpeed() {
        return new int[]{1,3};
    }

    @Override
    char getType() {
        return 'c';
    }
}
