package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public abstract class  Specie {
    public Specie() {
    }

    abstract int getInitialEnergy();

    abstract int getEnergyConsume();

    abstract int getEnrgyEarnedByRest();

    abstract int[] getSpeed();

    abstract char getType();

    abstract String getName();

    abstract String getImage();

    abstract char getIdentifier();

    abstract int eat(char foodId);

}
