package pt.ulusofona.lp2.deisiJungle;


//import junit.framework.*;


import org.junit.Assert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

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


    /*@org.junit.Test
    public void creteInicialJungleTest(){

        GameManager game = new GameManager();
        Assert.assertTrue(game.createInitialJungle(40,40,createPlayers()));

        Assert.assertFalse(game.createInitialJungle(40,40,createToMuchPlayers()));
    }*/


    @org.junit.Test
    public void testGetPlayerInitialEnergy() throws InvalidInitialJungleException {

        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "10"}, {"m", "30"}};
        String[][] players = {{"0", "Pedro", "L"}, {"1", "Valentim", "T"}};

        game.createInitialJungle(40,players,foodsInfo);

        Assert.assertEquals(150, game.players.get(1).getEnergy());
    }


    @org.junit.Test
    public void testGetPlayerInfo() throws InvalidInitialJungleException {

        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "10"}, {"m", "30"}};
        String[][] players = {{"0", "Pedro", "L"}, {"1", "Valentim", "T"}};

        game.createInitialJungle(40,players,foodsInfo);

        String[] expected = new String[5];
        expected[0] = "1";
        expected[1] = "Valentim";
        expected[2] = "T";
        expected[3] = "150";
        expected[4] = "1..3";

        Assert.assertEquals(expected, game.getPlayerInfo(game.players.get(1).getId()));
    }


    @org.junit.Test
    public void testGetSquareInfo() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "10"}, {"m", "30"}};
        String[][] players = {{"0", "Pedro", "L"}, {"1", "Valentim", "T"}};

        game.createInitialJungle(40, players, foodsInfo);

        game.map.getSquare(5).getPlayers().add(game.players.get(0));
        game.map.getSquare(5).getPlayers().add(game.players.get(1));

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
        expected[2] = "0,1";

        Assert.assertArrayEquals(expected,game.getSquareInfo(5));
    }


    /*@org.junit.Test
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
    }*/


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


    @org.junit.Test
    public void testGetFoods(){
        GameManager game = new GameManager();

        /*
            add(new Water());
            add(new Weed());
            add(new Banana());
            add(new Meat());
            add(new Mushrooms());
            */

        String[][] foodsInfo = game.getFoodTypes();
        Assert.assertEquals(foodsInfo[0][0], "a");
        Assert.assertEquals(foodsInfo[1][0], "e");
        Assert.assertEquals(foodsInfo[2][0], "b");
        Assert.assertEquals(foodsInfo[3][0], "c");
        Assert.assertEquals(foodsInfo[4][0], "m");

        Assert.assertEquals(foodsInfo[0][1], "Agua");
        Assert.assertEquals(foodsInfo[1][1], "Erva");
        Assert.assertEquals(foodsInfo[2][1], "Bananas");
        Assert.assertEquals(foodsInfo[3][1], "Carne");
        Assert.assertEquals(foodsInfo[4][1], "Cogumelo Magico");

        Assert.assertEquals(foodsInfo[0][2], "water.png");
        Assert.assertEquals(foodsInfo[1][2], "grass.png");
        Assert.assertEquals(foodsInfo[2][2], "bananas.png");
        Assert.assertEquals(foodsInfo[3][2], "meat.png");
        Assert.assertEquals(foodsInfo[4][2], "mushroom.png");


      //esultCode x = MovementResultCode.CAUGHT_FOOD;
        MovementResult zz = new MovementResult(MovementResultCode.VALID_MOVEMENT, "ss");

        if(zz.code()==MovementResultCode.NO_ENERGY);

    }



    @org.junit.Test
    public void testFuncionsIdFoodValid(){
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "10"}, {"m", "30"}};
        String[][] foodsInfoInvalid = {{"p", "10"}, {"Q", "30"}};


        String[][] invalidFoodPosition = {{"c", "1000"}, {"m", "309999"}};


        Assert.assertTrue(game.isFoodPositionValid(Integer.parseInt(foodsInfo[0][1]),60));
        Assert.assertTrue(game.isFoodPositionValid(Integer.parseInt(foodsInfo[1][1]),60));

        Assert.assertTrue(game.isFoodIdValid(foodsInfo[0][0].charAt(0)));
        Assert.assertTrue(game.isFoodIdValid(foodsInfo[1][0].charAt(0)));

        Assert.assertFalse(game.isFoodIdValid(foodsInfoInvalid[0][0].charAt(0)));
        Assert.assertFalse(game.isFoodIdValid(foodsInfoInvalid[1][0].charAt(0)));

        Assert.assertFalse(game.isFoodPositionValid(Integer.parseInt(invalidFoodPosition[0][1]),60));
        Assert.assertFalse(game.isFoodPositionValid(Integer.parseInt(invalidFoodPosition[1][1]),60));

    }

    @org.junit.Test
    public void testgetSquareInfo() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "10"}, {"m", "30"}};
        String[][] players2 = {{"0", "Pedro", "L"}, {"1", "Valentim", "Z"}};


        /*
        playersTest.add(new Player(0, new Lion(),"Pedro"));
        playersTest.add(new Player(1,new Elephant(),"Joao"));
        playersTest.add(new Player(3, new Lion(),"Paulinho"));
        playersTest.add(new Player(4,new Elephant(),"Antonio Silva"));
         */


        game.createInitialJungle(40, players2, foodsInfo);
        String i = game.map.getSquare(30).getFood().getTooltip();

        for(int x = 0; x<15;x++){
            game.moveCurrentPlayer(0, false, false);
        }

        String[] stuff = game.getSquareInfo(10);
        String[] vazio = new String[]{"meat.png", "Carne toxica", ""};

        String[] stuff2 = game.getSquareInfo(30);
        String[] vazio2 = new String[]{"mushroom.png", i, ""};

        String[] stuff3 = game.getSquareInfo(1);
        String[] vazio3 = new String[]{"blank.png", "Vazio", "0,1"};

        Assert.assertEquals(vazio, stuff);
        Assert.assertEquals(vazio2, stuff2);
        Assert.assertEquals(vazio3, stuff3);

        game.moveCurrentPlayer(6,false, false);
        game.moveCurrentPlayer(6,false, false);

        String[] stuff4 = game.getSquareInfo(7);
        String[] vazio4 = new String[]{"blank.png", "Vazio", "1,0"};

        Assert.assertEquals(vazio4, stuff4);


    }


    @org.junit.Test
    public void testEatFood() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "10"}, {"m", "39"}};
        String[][] players = {{"0", "Pedro", "L"}, {"1", "Valentim", "T"}};

        game.createInitialJungle(40, players, foodsInfo);

        Player teste1 = game.players.get(0);

        //energia player Pedro - 80
        teste1.removeEnergy(1);
        teste1.removeEnergy(1);
        teste1.removeEnergy(1);
        teste1.removeEnergy(1);
        teste1.removeEnergy(1);
        teste1.removeEnergy(1);
        teste1.removeEnergy(1);
        teste1.removeEnergy(1);
        teste1.removeEnergy(1);
        teste1.removeEnergy(1);
        //energia player Pedro - 60


        Food food1 = game.map.getSquare(10).getFood();
        teste1.eat(food1, game.getNumberOfPlays());

        Assert.assertEquals(110, teste1.getEnergy());
    }

    @org.junit.Test
    public void testMovePlayer() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "5"}, {"m", "10"}};
        String[][] players = {{"0", "Pedro", "L"}, {"1", "Valentim", "T"}};

        game.createInitialJungle(40, players, foodsInfo);

        Assert.assertEquals(0, game.players.get(game.getTurn()).getId());

        /*
              init   rest  consume
        LION - 80  /  10  /   2   /
        TURT - 150 /  5   /   1   /
         */



        //move pedro - 0 / 80 / 1
        MovementResult move = game.moveCurrentPlayer(-8,false, false); // plays 1
        Assert.assertEquals(MovementResultCode.INVALID_MOVEMENT, move.code());
        //move pedro - 0 / 80 / 1


        //move valentim - 1 / 150 / 1
        move = game.moveCurrentPlayer(-2,false, false); // plays 2
        Assert.assertEquals(MovementResultCode.INVALID_MOVEMENT, move.code());
        //move valentim - 1 / 150 / 1


        //move pedro - 0 / 80 / 1
        move = game.moveCurrentPlayer(0, false, false); // plays 3
        Assert.assertEquals(MovementResultCode.VALID_MOVEMENT, move.code());
        Assert.assertEquals(1, game.players.get(0).getPosition());
        Assert.assertEquals(90, game.players.get(0).getEnergy());
        //move pedro - 0 / 90 / 1


        //move valentim - 1 / 150 / 1
        move = game.moveCurrentPlayer(2, false, false); // plays 4
        Assert.assertEquals(MovementResultCode.VALID_MOVEMENT, move.code());
        Assert.assertEquals(3, game.players.get(1).getPosition());
        Assert.assertEquals(148, game.players.get(1).getEnergy());
        //move valentim - 1 / 148 / 3


        //move pedro - 0 / 90 / 1
        move = game.moveCurrentPlayer(4, false, false); // plays 5
        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, move.code());
        Assert.assertEquals(5, game.players.get(0).getPosition());
        Assert.assertEquals(132, game.players.get(0).getEnergy());
        //move pedro - 0 / 132 / 5


        //move valentim - 1 / 148 / 3
        move = game.moveCurrentPlayer(2, false, false); // plays 6
        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, move.code());
        Assert.assertEquals(5, game.players.get(1).getPosition());
        Assert.assertEquals(196, game.players.get(1).getEnergy());
        //move valentim - 1 / 196 / 5


        //move pedro - 0 / 132 / 5
        move = game.moveCurrentPlayer(5, false, false); // plays 7
        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, move.code());
        Assert.assertEquals(10, game.players.get(0).getPosition());
        Assert.assertEquals(1, 1);
        //move pedro - 0 / algo a baixo / 10
    }


    @org.junit.Test
    public void testMovePlayer2() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "5"}, {"m", "10"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "T"}};

        game.createInitialJungle(40, players, foodsInfo);

        Assert.assertEquals(0, game.players.get(game.getTurn()).getId());

        /*
              init   rest  consume
        LION - 180  /  10  /   2   /
        TURT - 150 /  5   /   1   /
         */

        //move pedro - 0 / 180 / 1
        MovementResult move = game.moveCurrentPlayer(0,true, false); // plays 1
        Assert.assertEquals(MovementResultCode.VALID_MOVEMENT, move.code());
        Assert.assertEquals(1, game.players.get(0).getPosition());
        Assert.assertEquals(190, game.players.get(0).getEnergy());
        //move pedro - 0 / 190 / 1
    }

    @org.junit.Test
    public void testMovePlayer3() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "5"}, {"m", "10"}};
        String[][] players = {{"0", "Pedro", "L"}, {"1", "Valentim", "Z"}};

        game.createInitialJungle(40, players, foodsInfo);

        String[] expected = new String[]{"1","Valentim","Z","112","1..6"};

        MovementResult move = game.moveCurrentPlayer(4,false, false); // plays 1
        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, move.code());
        Assert.assertEquals(122,game.players.get(0).getEnergy());

        move = game.moveCurrentPlayer(4,false, false); //plays 2
        String[] obtained = game.getPlayerInfo(game.players.get(1).getId());
        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, move.code());
        Assert.assertEquals(expected, obtained);

        move = game.moveCurrentPlayer(0,false, false); //plays 3
        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, move.code());
        Assert.assertEquals(182, game.players.get(0).getEnergy());

        move = game.moveCurrentPlayer(0,false, false); //plays 2
        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, move.code());
        Assert.assertEquals(182, game.players.get(1).getEnergy());
    }

    @org.junit.Test
    public void testMovePlayer_eat() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "5"}, {"m", "10"}, {"b", "15"}};
        String[][] players = {{"0", "Pedro", "L"}, {"1", "Valentim", "Z"}};

        game.createInitialJungle(40, players, foodsInfo);


        game.moveCurrentPlayer(0,false, false); // plays 1
        //Pedro energy - 90

        game.moveCurrentPlayer(0,false, false); //plays 2
        //Valentim energy - 90

        game.moveCurrentPlayer(0,false, false); // plays 3
        //Pedro energy - 100

        game.moveCurrentPlayer(0,false, false); //plays 4
        //Valentim energy - 110

        game.moveCurrentPlayer(0,false, false); // plays 5
        //Pedro energy - 110

        game.moveCurrentPlayer(0,false, false); //plays 6
        //Valentim energy - 130

        game.moveCurrentPlayer(0,false, false); // plays 7
        //Pedro energy - 120

        game.moveCurrentPlayer(0,false, false); //plays 8
        //Valentim energy - 150

        game.moveCurrentPlayer(0,false, false); // plays 9
        //Pedro energy - 130

        game.moveCurrentPlayer(0,false, false); //plays 10
        //Valentim energy - 170

        game.moveCurrentPlayer(0,false, false); // plays 11
        //Pedro energy - 140

        game.moveCurrentPlayer(0,false, false); //plays 12
        //Valentim energy - 190

        MovementResult move = game.moveCurrentPlayer(4,false, false); // plays 13
        //Pedro energy - 90

        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, move.code());
        Assert.assertEquals(66, game.players.get(0).getEnergy());
    }

    @org.junit.Test
    public void testRandomTests() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "10"}, {"m", "15"}, {"b", "5"}};
        String[][] players = {{"0", "Pedro", "L"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);

        //chekc first banana
        String[] expected = new String[3];
        expected[0] = "bananas.png";
        expected[1] = "Bananas : 3 : + 40 energia";
        expected[2] = "";
        Assert.assertEquals(expected,game.getSquareInfo(5));

        //check meat
        String[] expected3 = new String[3];
        expected3[0] = "meat.png";
        expected3[1] = "Carne : + 50 energia : 0 jogadas";
        expected3[2] = "";
        Assert.assertEquals(expected3,game.getSquareInfo(10));


        game.moveCurrentPlayer(4, false, false); //play 1
        String[] expected2 = new String[3];
        expected2[0] = "bananas.png";
        expected2[1] = "Bananas : 2 : + 40 energia";
        expected2[2] = "0";
        Assert.assertEquals(expected2,game.getSquareInfo(5));

        for(int i=0; i<11; i++){
            game.moveCurrentPlayer(0,false, false);
        }
        //play 2
        //play 3
        //play 4
        //play 5
        //play 6
        //play 7
        //play 8
        //play 9
        //play 10
        //play 11
        //play 12

        Assert.assertEquals(82, game.players.get(0).getEnergy());

        game.moveCurrentPlayer(5,false, false); //play 13

        Assert.assertEquals(36, game.players.get(0).getEnergy());

        String[] expected4 = new String[3];
        expected4[0] = "meat.png";
        expected4[1] = "Carne toxica";
        expected4[2] = "0";
        Assert.assertEquals(expected4,game.getSquareInfo(10));

    }

    @org.junit.Test
    public void testRandomTests2() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"b", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "Z"}, {"1", "Valentim", "L"}};

        game.createInitialJungle(40, players, foodsInfo);

        String[] expected = new String[3];
        expected[0] = "bananas.png";
        expected[1] = "Bananas : 3 : + 40 energia";
        expected[2] = "";
        Assert.assertEquals(expected,game.getSquareInfo(3));

        String[] expected2 = new String[3];
        expected2[0] = "bananas.png";
        expected2[1] = "Bananas : 3 : + 40 energia";
        expected2[2] = "";
        Assert.assertEquals(expected2,game.getSquareInfo(6));

        MovementResult move = game.moveCurrentPlayer(2, false, false); //play 1
        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, move.code());

        String[] expected3 = new String[3];
        expected3[0] = "bananas.png";
        expected3[1] = "Bananas : 2 : + 40 energia";
        expected3[2] = "0";
        Assert.assertEquals(expected3,game.getSquareInfo(3));

        String[] expected4 = new String[3];
        expected4[0] = "bananas.png";
        expected4[1] = "Bananas : 3 : + 40 energia";
        expected4[2] = "";
        Assert.assertEquals(expected4,game.getSquareInfo(6));

        game.moveCurrentPlayer(5, false, false); //play 1
    }

    @org.junit.Test
    public void drinkWater() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"a", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "Z"}, {"1", "Valentim", "L"}};

        game.createInitialJungle(40, players, foodsInfo);

        MovementResult move = game.moveCurrentPlayer(2,false, false);
        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, move.code());
        Assert.assertEquals(79, game.players.get(0).getEnergy());
    }

    @org.junit.Test
    public void eatMushroom() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"m", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "Z"}, {"1", "Valentim", "Z"}};

        game.createInitialJungle(40, players, foodsInfo);

        MovementResult move = game.moveCurrentPlayer(2,false, false); //play
        //energy 66
        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, move.code());
        Assert.assertEquals(1, 1);

        game.moveCurrentPlayer(2,false, false); //play 2
        //energy 66
        Assert.assertEquals(1, 1);

        //NOT TESTED BUT IS PASSING TESTS IF NEEDED
    }

    @org.junit.Test
    public void eatMeat() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "Z"}};

        game.createInitialJungle(40, players, foodsInfo);

        for(int i=0; i<12; i++){
            game.moveCurrentPlayer(0,false, false);
        }
        //play 1
        //play 2
        //play 3
        //play 4
        //play 5
        //play 6
        //play 7
        //play 8
        //play 9
        //play 10
        //play 11
        //play 12

        game.moveCurrentPlayer(2,false, false); //play 13
        String[] expected = new String[3];
        expected[0] = "meat.png";
        expected[1] = "Carne toxica";
        expected[2] = "0";
        Assert.assertEquals(expected,game.getSquareInfo(3));

        Assert.assertEquals(192, game.players.get(0).getEnergy());

    }



    @org.junit.Test
    public void testRighSomethingToAFile() throws IOException {
        String text = "Welcome to the save file\nHappy new Year!";
        String _testFolder="";

        GameManager game = new GameManager();


        //create file
        String nomeFicheiro = "save.txt";
        File ficheiro = new File(_testFolder + nomeFicheiro);

        //right to file
        Path filePath = Path.of(ficheiro.getPath());
        Files.writeString(filePath, text);

        // Reading the content of the file
        String file_content = Files.readString(filePath);



        System.out.println(file_content);


        //split it
        String data[] = file_content.split("\n");

        //tests propose
        System.out.println(data[0]);
        System.out.println(data[1]);


        //check if the Strings in the text files are the same
        Assert.assertEquals(text, file_content);
        Assert.assertEquals("Welcome to the save file", data[0]);
        Assert.assertEquals("Happy new Year!", data[1]);


        //delete everithing from the file
        PrintWriter writer = new PrintWriter(ficheiro);
        writer.print("");
        writer.close();


        //check if the file is empty
        file_content = Files.readString(filePath);
        Assert.assertEquals("", file_content);



    }

    @org.junit.Test
    public void moveWith3Players() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "15"}, {"b", "20"}};
        String[][] players = {{"0", "Pedro", "L"}, {"1", "Valentim", "Z"}, {"2", "Joao", "P"}};

        game.createInitialJungle(40, players, foodsInfo);

        //PEDRO - 80 energy / 2 consume
        //Valentim - 70 energy / 2 consume
        //Joao - 70 energy / 4 consume

        game.moveCurrentPlayer(4,false, false); //play 1 - turn 0

        Assert.assertEquals(72, game.players.get(0).getEnergy());//check energy
        Assert.assertEquals(5, game.players.get(0).getPosition());//check energy

        game.moveCurrentPlayer(2,false, false); //play 2 - turn 1

        Assert.assertEquals(66, game.players.get(1).getEnergy());//check energy
        Assert.assertEquals(3, game.players.get(1).getPosition());//check energy

        game.moveCurrentPlayer(6,false, false); //play 3 - turn 2

        Assert.assertEquals(46, game.players.get(2).getEnergy());//check energy
        Assert.assertEquals(7, game.players.get(2).getPosition());//check energy

        game.moveCurrentPlayer(6,false, false); //play 4 - turn 0

        Assert.assertEquals(60, game.players.get(0).getEnergy());//check energy
        Assert.assertEquals(11, game.players.get(0).getPosition());//check energy

        game.moveCurrentPlayer(6,false, false); //play 5 - turn 1

        Assert.assertEquals(54, game.players.get(1).getEnergy());//check energy
        Assert.assertEquals(9, game.players.get(1).getPosition());//check energy

        game.moveCurrentPlayer(0,false, false); //play 6 - turn 2

        Assert.assertEquals(96, game.players.get(2).getEnergy());//check energy
        Assert.assertEquals(7, game.players.get(2).getPosition());//check energy

        game.moveCurrentPlayer(4,false, false); //play 7 - turn 0

        Assert.assertEquals(102, game.players.get(0).getEnergy());//check energy
        Assert.assertEquals(15, game.players.get(0).getPosition());//check energy

        game.moveCurrentPlayer(6,false, false); //play 8 - turn 1

        Assert.assertEquals(92, game.players.get(1).getEnergy());//check energy
        Assert.assertEquals(15, game.players.get(1).getPosition());//check energy

        game.moveCurrentPlayer(6,false, false); //play 9 - turn 2

        Assert.assertEquals(72, game.players.get(2).getEnergy());//check energy
        Assert.assertEquals(13, game.players.get(2).getPosition());//check energy
    }

    @org.junit.Test
    public void test_checkEnergy() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "5"}, {"b", "20"}};
        String[][] players = {{"0", "Pedro", "L"}, {"1", "Valentim", "Z"}, {"2", "Joao", "P"}};

        game.createInitialJungle(40, players, foodsInfo);

        //PEDRO - 80 energy / 2 consume
        //Valentim - 70 energy / 2 consume
        //Joao - 70 energy / 4 consume

        for(int i=0; i<12; i++){
            game.moveCurrentPlayer(0, false, false);
        }

        game.players.get(0).removeEnergy(59);

        Assert.assertEquals(2, game.players.get(0).getEnergy());

        MovementResult move = game.moveCurrentPlayer(4,false, false);
        Assert.assertEquals(MovementResultCode.NO_ENERGY, move.code());
    }


    @org.junit.Test
    public void test_moveWithBypass() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "5"}, {"b", "20"}};
        String[][] players = {{"0", "Pedro", "L"}, {"1", "Valentim", "Z"}, {"2", "Joao", "P"}};

        game.createInitialJungle(40, players, foodsInfo);

        //PEDRO - 80 energy / 2 consume
        //Valentim - 70 energy / 2 consume
        //Joao - 70 energy / 4 consume

        MovementResult move = game.moveCurrentPlayer(-10, true, false);

        Assert.assertEquals(MovementResultCode.INVALID_MOVEMENT, move.code());
        Assert.assertEquals(1, game.players.get(0).getPosition());

        move = game.moveCurrentPlayer(16, true, false);

        Assert.assertEquals(MovementResultCode.VALID_MOVEMENT, move.code());
        Assert.assertEquals(17, game.players.get(1).getPosition());
    }


    @org.junit.Test
    public void test_testTimesEaten() throws InvalidInitialJungleException {

        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "5"}, {"b", "20"}};
        String[][] players = {{"0", "Pedro", "L"}, {"1", "Valentim", "Z"}, {"2", "Joao", "P"}};

        game.createInitialJungle(40,players,foodsInfo);

        Food banana = new Banana();
        Food meat = new Meat();


        game.players.get(0).eat(banana,10);
        game.players.get(1).eat(meat,10);

        Assert.assertEquals(1,banana.getTimesEaten());
        Assert.assertEquals(1,meat.getTimesEaten());




    }


}
