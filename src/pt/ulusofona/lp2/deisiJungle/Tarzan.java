package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Tarzan extends Omnivero{

    public Tarzan() {
    }


    @Override
    String getName() {
        return "Tarzan";
    }

    @Override
    String getImage() {
        return "tarzan.png";
    }

    @Override
    char getIdentifier() {
        return 'Z';
    }

    @Override
    int getInitialEnergy() {
        return 70;
    }

    @Override
    int getEnergyConsume() {
        return 2;
    }

    @Override
    int getEnrgyEarnedByRest() {
        return 20;
    }

    @Override
    int[] getSpeed() {
        return new int[]{1,6};
    }

    @Override
    char getType() {
        return 'o';
    }
}
