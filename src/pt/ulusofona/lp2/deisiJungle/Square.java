package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Square {

    int number;
    String type;
    ArrayList<Player> players;

    public Square(){
    }

    public Square(int number, String type) {
        this.number = number;
        this.type = type;
    }

    int getNumber(){
        return number;
    }

    String getType(){
        return type;
    }

    ArrayList<Player> getPlayers(){
        return players;
    }

    String getIdPlayersInString(){
        String result = "";

        for(int i=0; i<players.size(); i++){

            if(i== players.size()-1){
                result += ""+players.get(i).id;
            }
            result+= ""+players.get(i).id+",";
        }

        return result;
    }

}
