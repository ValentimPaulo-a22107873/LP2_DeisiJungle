package pt.ulusofona.lp2.deisiJungle;

public abstract class Herbivoro extends Specie {
    @Override
    public int eat(char foodId){

        if(foodId == 'e'){
            return 20;
        }
        if(foodId == 'a'){
            return 15;
        }
    }
}
