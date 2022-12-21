package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Lion extends Carnivoros{

    public Lion() {
    }

    @Override
    String getName() {
        return "Leão";
    }

    @Override
    String getImage() {
        return "lion.png";
    }

    @Override
    char getIdentifier() {
        return 'L';
    }

    @Override
    int[] getSpeed() {
        return new int[]{4,6};
    }

    @Override
    char getType() {
        return 'c';
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
