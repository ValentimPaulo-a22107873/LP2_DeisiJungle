package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Turtle extends Specie{


    public Turtle() {
    }

    public Turtle(String name, String image, char specieIdentifier) {
        super(name, image, specieIdentifier);
    }

    @Override
    char getSpecieIdentifier() {
        return this.specieIdentifier;
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
}
