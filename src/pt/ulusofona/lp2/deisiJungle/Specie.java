package pt.ulusofona.lp2.deisiJungle;

public class Specie {

    protected String name, image;
    protected char specieIdentifier;
    protected int initialEnergy;
    protected int energyConsume;
    protected int enrgyEarnedByRest;
    protected int[] speed;


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
