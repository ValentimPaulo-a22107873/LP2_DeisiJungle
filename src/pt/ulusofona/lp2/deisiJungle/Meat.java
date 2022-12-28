package pt.ulusofona.lp2.deisiJungle;

public class Meat extends Food{

    int turn=0;

    public Meat(){

    }

    @Override
    String getName() {
        return "Carne";
    }

    @Override
    char getIdentifier() {
        return 'c';
    }

    @Override
    String getImage() {
        return "meat.png";
    }

    @Override
    String getTooltip() {

        if(turn > 12){
            return "Carne toxica";
        }else{
            return "Carne : + 50 energia : "+turn+" jogadas";
        }
    }

    void updateTurn(int turn){
        this.turn=turn;
    }
}
