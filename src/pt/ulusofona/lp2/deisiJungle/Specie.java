package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public abstract class  Specie {

    protected String name;
    protected String image;
    protected char specieIdentifier;
    protected int initialEnergy;
    protected int energyConsume;
    protected int enrgyEarnedByRest;
    protected int[] speed;

    abstract char getSpecieIdentifier();

    abstract int getInitialEnergy();

    abstract int getEnergyConsume();

    abstract int getEnrgyEarnedByRest();

    abstract int[] getSpeed();



    public Specie() {
    }

    public Specie(String name, String image, char specieIdentifier) {
        this.name = name;
        this.image = image;
        this.specieIdentifier = specieIdentifier;
    }

    String getName(){
        return name;
    }

    String getImage(){
        return image;
    }

    char getIdentifier(){
        return specieIdentifier;
    }

}
