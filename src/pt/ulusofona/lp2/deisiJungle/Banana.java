package pt.ulusofona.lp2.deisiJungle;

public class Banana extends Food{

    private int numberBananas;

    public Banana() {
        this.numberBananas = 3;
    }


    @Override
    String getName() {
        return "Bananas";
    }

    @Override
    char getIdentifier() {
        return 'b';
    }

    @Override
    String getImage() {
        return "bananas.png";
    }

    @Override
    String getTooltip() {
        return "Bananas : "+numberBananas+" : + 40 energia";
    }

    public boolean bananaAvailable(){
        if(numberBananas == 0){
            return false;
        }
        numberBananas--;
        return true;
    }

    public int getNumberBananas() {
        return numberBananas;
    }

    public void updateBanana(int number){
        this.numberBananas = number;
    }
}
