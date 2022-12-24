package pt.ulusofona.lp2.deisiJungle;

public class Player {

    private int id;
    private int energy;
    private int position = 1;
    private char specieId;
    private Specie specie;
    private String name;

    private int distanceWalked;
    private int bananaEaten=0;



    public Player() {
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

    public Player(int id, Specie specie, String name) {
        this.id = id;
        this.specie = specie;
        this.name = name;
    }

    //CONSTRUTORES

    public void eat(char food, int initialEnergy, int turn, int mushroomEnergy){
        if(food=='b' && bananaEaten>=1){
            addEnergy(-40);
            bananaEaten++;
        }
        else if(food=='b' && bananaEaten<1){
            addEnergy(40);
            bananaEaten++;
        }
        else if(food=='m' && (turn%2)==0) {
            addEnergy((mushroomEnergy / 100) * getEnergy());
        }
        else if((food=='m' && (turn%2)!=0)){
            addEnergy(-(mushroomEnergy / 100) * getEnergy());
        }
        else{
            addEnergy(specie.eat(food, initialEnergy, turn));
        }
    }


    public void defineInitialEnergy(){
        energy = specie.getInitialEnergy();
    }



    public int getBananaEaten() {
        return bananaEaten;
    }

    public void addBanana(){
        bananaEaten++;
    }

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
        energy -= specie.getEnergyConsume();
    }

    void updatePosition(int numberOfSquares){
        position+=numberOfSquares;
    }

    void addEnergy(int energyToAdd){
        if(energy + energyToAdd >= specie.getInitialEnergy()){
            energy=specie.getInitialEnergy();
        }else{
            energy+=energyToAdd;
        }

    }
}
