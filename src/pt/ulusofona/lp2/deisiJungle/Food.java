package pt.ulusofona.lp2.deisiJungle;

public abstract class Food{

    private int timesEaten = 0;
    public Food(){

    }

    public int getTimesEaten(){
        return timesEaten;
    }

    public void addTimesEaten(){
        timesEaten++;
    }

    abstract String getName();

    abstract char getIdentifier();

    abstract String getImage();

    abstract String getTooltip();

    public int getBananaEaten(Banana banana){
        return banana.getNumberBananas();
    }

    public int getMushroomEnergy(Mushrooms mushroom){
        return mushroom.getEnergy();
    }
}
