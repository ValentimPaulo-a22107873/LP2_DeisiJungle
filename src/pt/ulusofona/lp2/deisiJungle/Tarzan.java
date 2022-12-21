package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Tarzan extends Specie{

    public Tarzan() {
    }

    public Tarzan(String name, String image, char specieIdentifier) {
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
        return 2;
    }

    @Override
    int getEnrgyEarnedByRest() {
        return 20;
    }

    @Override
    ArrayList<Integer> getSpeed() {
        return new ArrayList<>(){
            {
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
                add(6);
            }
        };
    }
}
