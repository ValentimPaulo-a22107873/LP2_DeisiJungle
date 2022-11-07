package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Map {

    ArrayList<Square> map;
    int size;

    ArrayList<Square> generateMap(int squares){

        for(int i=1; i<=squares; i++){
            if(i==squares) {
                map.add(new Square(i, "Meta"));
            }
            else{
                map.add(new Square(i, "Vazio"));
            }
        }

        return map;
    }

    Square getSquare(int number){
        return map.get(number);
    }

    int getSize(){
        return size;
    }
}
