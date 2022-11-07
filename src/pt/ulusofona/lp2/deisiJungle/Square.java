package pt.ulusofona.lp2.deisiJungle;

public class Square {

    int number;
    String type;

    public Square() {
    }

    public Square(int number, String type) {
        this.number = number;
        this.type = type;
    }

    int getSquareNumber(){
        return number;
    }
}
