package pt.ulusofona.lp2.deisiJungle;

public abstract class Omnivero extends Specie{

    @Override
    public int eat(char foodId){
        if(foodId == 'e'){
            return 20;
        }
        // Se ingerido por omnívoros, aumenta a energia em 20%
    }
}
