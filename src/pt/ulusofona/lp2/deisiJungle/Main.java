package pt.ulusofona.lp2.deisiJungle;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {



        String[][] players = new String[3][3];
        //- [0] => O ID do jogador
        //- [1] => O Nome do jogador
        //- [2] => O ID da espécie

        players[0][0]="1";
        players[0][1]="Pedro";
        players[0][2]="E";

        players[1][0]="2";
        players[1][1]="Paulinho";
        players[1][2]="Z";

        players[2][0]="3";
        players[2][1]="Joao";
        players[2][2]="P";



        GameManager game = new GameManager();

        if(game.createInitialJungle(6,0,players)) System.out.println("deu true");
        else
            System.out.println("falso");


    }
}