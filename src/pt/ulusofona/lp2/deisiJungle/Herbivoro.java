package pt.ulusofona.lp2.deisiJungle;

public abstract class Herbivoro extends Specie {
    @Override
    public int eat(char foodId, int energy, int turn){

        if(foodId == 'e'){
            return 20;
        }

        if(foodId == 'a'){
             return 15;
        }

        if(foodId == 'c'){
            return 0;
        }
        return -1;
    }
}
