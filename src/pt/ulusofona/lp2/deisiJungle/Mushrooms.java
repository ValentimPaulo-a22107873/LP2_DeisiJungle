package pt.ulusofona.lp2.deisiJungle;

import java.util.Random;

public class Mushrooms extends Food {

    int energy;

    public Mushrooms() {
        Random number = new Random();
        this.energy = number.nextInt(50)+10;
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    String getName() {
        return "Cogumelo Magico";
    }

    @Override
    char getIdentifier() {
        return 'm';
    }

    @Override
    String getImage() {
        return "mushroom.png";
    }

    @Override
    String getTooltip() {
        return "Cogumelo Magico: +- "+energy+"% energia";
    }
}
