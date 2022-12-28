package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Bird extends Omnivero{

    public Bird() {
    }

    @Override
    String getName() {
        return "Pássaro";
    }

    @Override
    String getImage() {
        return "bird.png";
    }

    @Override
    char getIdentifier() {
        return 'P';
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

    @Override
    char getType() {
        return 'o';
    }


}
