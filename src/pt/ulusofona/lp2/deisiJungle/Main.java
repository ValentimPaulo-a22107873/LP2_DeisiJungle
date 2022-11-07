package pt.ulusofona.lp2.deisiJungle;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        GameManager game = new GameManager();

        System.out.println(Arrays.deepToString(game.getSpecies()));
    }
}