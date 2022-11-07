package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Map {

    ArrayList<Square> map;

    ArrayList<Square> generateMap(int squares){

        for(int i=0; i<squares; i++){
            if(i==squares-1)
            {
                map.add(new Square(i, "Meta"));
            }
            else
            {
                map.add(new Square(i, "Vazio"));
            }
        }

        return map;
    }
}
