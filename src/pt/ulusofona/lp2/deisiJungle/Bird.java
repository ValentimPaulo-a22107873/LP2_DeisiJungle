package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Bird extends Specie{

    public Bird() {
    }

    public Bird(String name, String image, char specieIdentifier) {
        super(name, image, specieIdentifier);
    }

    @Override
    char getSpecieIdentifier() {
        return this.specieIdentifier;
    }

    @Override
    int getInitialEnergy() {
        return 70;
    }

    @Override
    int getEnergyConsume() {
        return 4;
    }

    @Override
    int getEnrgyEarnedByRest() {
        return 50;
    }

    @Override
    int[] getSpeed() {
        return new int[]{5, 6};
    }
}
