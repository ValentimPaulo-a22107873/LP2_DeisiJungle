package pt.ulusofona.lp2.deisiJungle;

import java.util.ArrayList;

public class Map {

    ArrayList<Square> map = new ArrayList<>();

    public Map(int size){
        generateMap(size);
    }

    public ArrayList<Square> generateMap(int squares){

        for(int i=1; i<=squares; i++){

            if(i==squares){
                map.add(new Square(i, "Meta", "finish.png", null));
            }else{
                map.add(new Square(i, "Vazio", "blank.png", null));
            }
        }

        return map;
    }

    Square getSquare(int number){
        return map.get(number-1);
    }

    int getSize(){
        return map.size();
    }

    boolean isSquareValid(int number){
        if(number<1 || number>map.size()){
            return false;
        }
        return true;
    }

    public void placeFood(int squareIndex, Food food){
        getSquare(squareIndex).setFood(food);
    }
}
