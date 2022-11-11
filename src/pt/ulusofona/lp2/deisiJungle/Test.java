package pt.ulusofona.lp2.deisiJungle;


//import junit.framework.*;


import org.junit.Assert;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

public class Test {


    /*        GameManager game = new GameManager();

        ArrayList<Player> playersTest = new ArrayList<>();


        //int id, int energy, Specie specie, String name
        playersTest.add(new Player(0, 10, new Specie("leao", "leao.png", 'L'),
                "Pedro"));
        playersTest.add(new Player(1,10,new Specie("elephant", "elephant.png", 'E'),
                "Joao"));

        game.players = playersTest;*/


    public String[][] createPlayers(){
        String[][] players = new String[3][3];

        players[0][0]="62";
        players[0][1]="Pedro";
        players[0][2]="E";

        players[1][0]="1";
        players[1][1]="Paulinho";
        players[1][2]="Z";

        players[2][0]="23";
        players[2][1]="Joao";
        players[2][2]="P";

        return players;
    }

    public String[][] createToMuchPlayers(){
        String[][] players = new String[10][3];

        players[0][0]="62";
        players[0][1]="Pedro";
        players[0][2]="E";

        players[1][0]="1";
        players[1][1]="Paulinho";
        players[1][2]="Z";

        players[2][0]="23";
        players[2][1]="Joao";
        players[2][2]="P";

        players[3][0]="2101";
        players[3][1]="Paulinho";
        players[3][2]="Z";

        players[4][0]="32109321";
        players[4][1]="Paulinho";
        players[4][2]="Z";

        players[5][0]="27772";
        players[5][1]="Paulinho";
        players[5][2]="Z";
        return players;
    }


    public ArrayList<Player> createSomePlayers (){

        ArrayList<Player> playersTest = new ArrayList<>();

        playersTest.add(new Player(0, 10, new Specie("leao", "leao.png", 'L'),
                "Pedro"));
        playersTest.add(new Player(1,10,new Specie("elephant", "elephant.png", 'E'),
                "Joao"));
        playersTest.add(new Player(3, 10, new Specie("leao", "leao.png", 'L'),
                "Paulinho"));
        playersTest.add(new Player(4,10,new Specie("elephant", "elephant.png", 'E'),
                "Antonio Silva"));

        return playersTest;
    }


    //@Test
    //public void testeGetSpecies(){

       /*Specie elephant = new Specie("Elefante","elephant.png", 'E');
        Specie lion = new Specie("Leão", "lion.png",'L' );
        Specie tarzan = new Specie("Tarzan", "tarzan.png", 'Z');
        Specie turtle = new Specie("Tartaruga", "turtle.png",'T');
        Specie bird = new Specie("Pássaro", "bird.png",'P');

        ArrayList<Specie> speciesTest = new ArrayList<>()
        {
            {
                add(elephant);
                add(lion);
                add(tarzan);
                add(turtle);
                add(bird);
            }
        };

        GameManager game = new GameManager();

        String[][] testResult = new String[game.species.size()][3];
        //[0] => identificador da espécie
        //● [1] => nome da espécie
        //● [2] => nome do ficheiro com a

        testResult[0][0] = "E";
        testResult[0][1] = "Elefante";
        testResult[0][2] = "elephant.png";

        testResult[1][0] = "L";
        testResult[1][1] = "Leão";
        testResult[1][2] = "lion.png";

        testResult[2][0] = "E";
        testResult[3][1] = "E";
        testResult[][2] = "E";


        game.getSpecies();

        Assert.assertArrayEquals();
    }*/


    @org.junit.Test
    public void creteInicialJungleTest(){

        GameManager game = new GameManager();
        Assert.assertTrue(game.createInitialJungle(40,40,createPlayers()));

        Assert.assertFalse(game.createInitialJungle(40,40,createToMuchPlayers()));
    }


    @org.junit.Test
    public void testGetPlayersIds(){

        GameManager game = new GameManager();

        game.map = new Map(30);

        game.map.getSquare(3).players = createSomePlayers();

        int[] expected = {0,1,3,4};

        Assert.assertArrayEquals(expected, game.getPlayerIds(3));

    }






}
