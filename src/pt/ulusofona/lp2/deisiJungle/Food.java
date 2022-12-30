package pt.ulusofona.lp2.deisiJungle;

public abstract class Food{

    public Food(){

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
