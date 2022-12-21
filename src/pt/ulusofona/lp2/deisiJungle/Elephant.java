package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Elephant extends Specie {

    public Elephant() {
    }

    public Elephant(String name, String image, char specieIdentifier) {
        super(name, image, specieIdentifier);
    }

    @Override
    char getSpecieIdentifier() {
        return this.specieIdentifier;
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

}
