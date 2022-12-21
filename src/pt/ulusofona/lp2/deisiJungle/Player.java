package pt.ulusofona.lp2.deisiJungle;

public class Player {

    private int id, energy, position=1;
    private char specieId;
    private Specie specie;
    private String name;

    private int distanceWalked;



    public Player() {
    }


    public void eat(){
        energy += specie.eat('h');
    }

    public Player(int id, char specieId, int energy, String name) {
        this.id = id;
        this.specieId = specieId;
        this.energy = energy;
        this.name = name;
    }

    public Player(int id, int energy, Specie specie, String name) {
        this.id = id;
        this.energy = energy;
        this.specie = specie;
        this.name = name;
    }


    //CONSTRUTORES

    Specie getSpecie(){
        return specie;
    }

    int getId(){
        return id;
    }
    public int getDistanceWalked() {
        return distanceWalked;
    }
    public void addDistanceWalked(int dist){
        distanceWalked+=dist;
    }

    char getSpecieId(){
        return specieId;
    }

    int getEnergy(){
        return energy;
    }

    int getPosition(){
        return position;
    }

    String getName(){
        return name;
    }

    void removeEnergy(){
        energy -= 2;
    }

    void updatePosition(int numberOfSquares){
        position+=numberOfSquares;
    }

    void addEnergy(int energyToAdd){
        energy+=energyToAdd;
    }
}
