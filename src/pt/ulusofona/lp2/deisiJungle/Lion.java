package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Lion extends Specie{

    public Lion() {
    }

    public Lion(String name, String image, char specieIdentifier) {
        super(name, image, specieIdentifier);
    }

    @Override
    char getSpecieIdentifier() {
        return this.specieIdentifier;
    }

    @Override
    int[] getSpeed() {
        return new int[]{4,6};
    }

    @Override
    public int getInitialEnergy() {
        return 80;
    }

    @Override
    int getEnergyConsume() {
        return 2;
    }

    @Override
    int getEnrgyEarnedByRest() {
        return 10;
    }
}
