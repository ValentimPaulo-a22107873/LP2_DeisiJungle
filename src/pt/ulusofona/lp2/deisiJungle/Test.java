package pt.ulusofona.lp2.deisiJungle;


//import junit.framework.*;


import org.junit.Assert;

import java.util.ArrayList;

public class Test {


    /*        GameManager game = new GameManager();

        ArrayList<Player> playersTest = new ArrayList<>();




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

    public String[][] create2Players(){
        String[][] players = new String[2][3];

        players[0][0]="62";
        players[0][1]="Pedro";
        players[0][2]="E";

        players[1][0]="1";
        players[1][1]="Paulinho";
        players[1][2]="Z";

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

        playersTest.add(new Player(0, 10, new Lion("leao", "leao.png", 'L'),
                "Pedro"));
        playersTest.add(new Player(1,10,new Elephant("elephant", "elephant.png", 'E'),
                "Joao"));
        playersTest.add(new Player(3, 10, new Lion("leao", "leao.png", 'L'),
                "Paulinho"));
        playersTest.add(new Player(4,10,new Elephant("elephant", "elephant.png", 'E'),
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


    @org.junit.Test
    public void testGetPlayerInfo(){

        GameManager game = new GameManager();

        // [0] => O ID do jogador
        //- [1] => O Nome do jogador
        //- [2] => O ID da espécie associada ao
        //jogador.
        //- [3] => A energia atual do jogador,
        //medida em unidades de energia

        //public Player(int id, char specieId, int energy, String name) {
        game.players.add(new Player(1,'E',30, "Pedro"));
        game.players.add(new Player(2,'L',30, "Joao"));

        String[] expected = new String[4];
        expected[0] = "1";
        expected[1] = "Pedro";
        expected[2] = "E";
        expected[3] = "30";

        String[][] helper = new String[2][3];
        //- [0] => O ID do jogador
        //- [1] => O Nome do jogador
        //- [2] => O ID da espécie
        helper[0][0] = "1";
        helper[0][1] = "Pedro";
        helper[0][2] = "E";

        helper[1][0] = "2";
        helper[1][1] = "Joao";
        helper[1][2] = "L";

        game.createInitialJungle(40,30,helper);

        Assert.assertArrayEquals(expected,game.getPlayerInfo(1));
        Assert.assertFalse(expected == game.getPlayerInfo(2) );
    }


    @org.junit.Test
    public void testGetSquareInfo(){
        GameManager game = new GameManager();

        String[][] helper = new String[2][3];
        //- [0] => O ID do jogador
        //- [1] => O Nome do jogador
        //- [2] => O ID da espécie
        helper[0][0] = "1";
        helper[0][1] = "Pedro";
        helper[0][2] = "E";

        helper[1][0] = "2";
        helper[1][1] = "Joao";
        helper[1][2] = "L";

        game.createInitialJungle(10,10, helper);

        game.map.getSquare(5).players.add(game.players.get(0));

        //- [0] => Nome do ficheiro com a
        //imagem a colocar nesse posição
        //- [1] => Uma descrição textual do que
        //existe nessa posição (nesta fase
        //pode ser apenas “Vazio” ou “Meta”)
        //- [2] => Uma String contendo os
        //identificadores dos jogadores que
        //estão nessa posição, separados por
        //vírgula (ex: “3,5” - estão lá os
        //jogadores 3 e 5). blank.png

        String[] expected = new String[3];
        expected[0] = "blank.png";
        expected[1] = "Vazio";
        expected[2] = "1";

        Assert.assertArrayEquals(expected,game.getSquareInfo(5));
    }


    @org.junit.Test
    public void testMoveCurrentPlayer(){
        //Test Valid move

        GameManager game = new GameManager();

        //        players[0][0]="62";
        //        players[0][1]="Pedro";
        //        players[0][2]="E";
        //
        //        players[1][0]="1";
        //        players[1][1]="Paulinho";
        //        players[1][2]="Z";
        //
        //        players[2][0]="23";
        //        players[2][1]="Joao";
        //        players[2][2]="P";

        game.createInitialJungle(40,40,createPlayers());

        Player currentPlayer = game.players.get(game.turn);

        game.moveCurrentPlayer(3, false);

        Assert.assertTrue(currentPlayer.energy == 38);

        Assert.assertTrue(currentPlayer.getPosition() == 4);

    }

    @org.junit.Test
    public void testMoveCurrentPlayer1(){

        //Test Invalid Move

        GameManager game = new GameManager();

        //        players[0][0]="62";
        //        players[0][1]="Pedro";
        //        players[0][2]="E";
        //
        //        players[1][0]="1";
        //        players[1][1]="Paulinho";
        //        players[1][2]="Z";
        //
        //        players[2][0]="23";
        //        players[2][1]="Joao";
        //        players[2][2]="P";

        game.createInitialJungle(40,40,createPlayers());

        Player currentPlayer = game.players.get(game.turn);

        game.moveCurrentPlayer(10, false);

        Assert.assertTrue(currentPlayer.energy == 40);

        Assert.assertTrue(currentPlayer.getPosition() == 1);

    }
    @org.junit.Test
    public void testMoveCurrentPlayer3(){
        //test id the game ends

        GameManager game = new GameManager();

        //        players[0][0]="62";
        //        players[0][1]="Pedro";
        //        players[0][2]="E";
        //
        //        players[1][0]="1";
        //        players[1][1]="Paulinho";
        //        players[1][2]="Z";
        //
        //        players[2][0]="23";
        //        players[2][1]="Joao";
        //        players[2][2]="P";

        game.createInitialJungle(40,40,createPlayers());

        game.moveCurrentPlayer(35,true); //move Paulinho
        game.moveCurrentPlayer(36,true); // move Joao
        game.moveCurrentPlayer(37,true); //move Pedro

        Player currentPlayer = game.players.get(game.turn);
        game.moveCurrentPlayer(5,false); //move Paulinho to the last case

        Assert.assertTrue(game.checkIfGameEnded());
        Assert.assertEquals(41, currentPlayer.getPosition());
    }


    @org.junit.Test
    public void testgetInicialEnergy(){
        Specie l = new Lion();
        Assert.assertEquals(80, l.getInitialEnergy());

        System.out.println();
    }

    @org.junit.Test
    public void testGetSpecies(){
        GameManager game = new GameManager();

        /*
        Specie elephant = new Elephant("Elefante","elephant.png", 'E');
        Specie lion = new Lion("Leão", "lion.png",'L' );
        Specie tarzan = new Tarzan("Tarzan", "tarzan.png", 'Z');
        Specie turtle = new Turtle("Tartaruga", "turtle.png",'T');
        Specie bird = new Bird("Pássaro", "bird.png",'P');*/

        String[][] espiciesInfo = game.getSpecies();
        Assert.assertEquals(espiciesInfo[0][3], "180");
        Assert.assertEquals(espiciesInfo[1][3], "80");
        Assert.assertEquals(espiciesInfo[2][3], "150");
        Assert.assertEquals(espiciesInfo[3][3], "70");
        Assert.assertEquals(espiciesInfo[4][3], "70");

        Assert.assertEquals(espiciesInfo[0][4], "4");
        Assert.assertEquals(espiciesInfo[1][4], "2");
        Assert.assertEquals(espiciesInfo[2][4], "1");
        Assert.assertEquals(espiciesInfo[3][4], "4");
        Assert.assertEquals(espiciesInfo[4][4], "2");

        Assert.assertEquals(espiciesInfo[0][5], "10");
        Assert.assertEquals(espiciesInfo[1][5], "10");
        Assert.assertEquals(espiciesInfo[2][5], "5");
        Assert.assertEquals(espiciesInfo[3][5], "50");
        Assert.assertEquals(espiciesInfo[4][5], "20");

        Assert.assertEquals(espiciesInfo[0][6], "1..6");
        Assert.assertEquals(espiciesInfo[1][6], "4..6");
        Assert.assertEquals(espiciesInfo[2][6], "1..3");
        Assert.assertEquals(espiciesInfo[3][6], "5..6");
        Assert.assertEquals(espiciesInfo[4][6], "1..6");

    }



}
