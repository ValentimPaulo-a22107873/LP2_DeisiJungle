package pt.ulusofona.lp2.deisiJungle;

public class Player {

    private int id;
    private int energy;
    private int position = 1;
    private char specieId;
    private Specie specie;
    private String name;
    private int distanceWalked = 0;
    private int bananaEaten=0;
    private int foodEaten = 0;



    public Player() {
    }

    public Player(int id, int energy, Specie specie, String name) {
        this.id = id;
        this.energy = energy;
        this.specie = specie;
        this.name = name;
    }

    public Player(int id, int energy, Specie specie, String name, int position, int bananaEaten) {
        this.id = id;
        this.energy = energy;
        this.specie = specie;
        this.name = name;
        this.position = position;
        this.bananaEaten = bananaEaten;
    }

    //CONSTRUTORES


    public int getBananaEaten() {
        return bananaEaten;
    }

    public int getFoodEaten() {
        return foodEaten;
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

    void removeEnergy(int times){
        energy -= specie.getEnergyConsume() * times;
    }

    void updatePosition(int positionExpected){
        position=positionExpected;
    }

    void addEnergy(int energyToAdd){
        if(energy + energyToAdd >= 200){
            energy=200;
        }else{
            energy+=energyToAdd;
        }

    }

    int move(int distance, int mapSize, boolean bypass){

        if(energy < specie.getEnergyConsume()*distance){
            return 3;
        }
        if((Math.abs(distance)<specie.getSpeed()[0] || Math.abs(distance)>specie.getSpeed()[1]) && bypass){
            return 2;
        }

        //CHECKED IF MOVEMENT IS VALID TO HAPPEN
        removeEnergy(Math.abs(distance));

        if(position+distance >= mapSize){
            updatePosition(mapSize);
        }else{
            updatePosition(position+distance);
        }
        distanceWalked+=Math.abs(distance);

        return 1;
    }

    void rest(){
        addEnergy(specie.getEnrgyEarnedByRest());
    }

    public String kotlin_getPlayerInfo(){
        return this.id+" | "+this.name+" | "+this.specie.getName()+" | "+this.energy+" | "+this.position;
    }

    public String kotlin_getMostTraveled(){
        return this.name+":"+this.specie.getIdentifier()+":"+distanceWalked;
    }

    //FUNCTIONS RELATED WITH THE METHOD - eat()

    public boolean eat(Food food, int turn){
        if(food.getIdentifier()=='a'){
            addEnergy(specie.eat('a', energy, turn));
            foodEaten++;
            return true;
        }

        else if(food.getIdentifier()=='e'){
            addEnergy(specie.eat('e', energy, turn));
            foodEaten++;
            return true;
        }

        else if(food.getIdentifier()=='c'){
            if(specie.eat('c', energy, turn)!=0){
                addEnergy(specie.eat('c', energy, turn));
                foodEaten++;
                return true;
            }
            return false;
        }

        else if(food.getIdentifier()=='b'){
            return eatBanana((Banana) food);
        }

        else if(food.getIdentifier()=='m'){
            eatMushroom((Mushrooms) food, turn);
            foodEaten++;
            return true;
        }

        else{
            return false;
        }
    }

    boolean eatBanana(Banana banana){
        if(banana.bananaAvailable()){

            if(bananaEaten==0){
                foodEaten++;
                addEnergy(40);
            }else{
                foodEaten++;
                addEnergy(-40);
            }
            addBanana();
            return true;
        }
        return false;
    }

    void eatMushroom(Mushrooms mushroom, int turn){
        int value = this.energy * mushroom.getEnergy();

        if(turn%2 == 0){
            addEnergy(value/100);
        }else{
            addEnergy(-value/100);
        }
    }
}
