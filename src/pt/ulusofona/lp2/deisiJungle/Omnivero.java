package pt.ulusofona.lp2.deisiJungle;

public abstract class Omnivero extends Specie{

    @Override
    public int eat(char foodId, int initialEnergy, int turn){
        if(foodId == 'e'){
            return 20;
        }
        // Se ingerido por omnívoros, aumenta a energia em 20%
        if(foodId == 'a'){
            return (int)(0.2 * initialEnergy);
        }

        if(foodId == 'c'){
            if(turn > 12){
                return -50;
            }
            return 50;
        }
        return -1;
    }
}
