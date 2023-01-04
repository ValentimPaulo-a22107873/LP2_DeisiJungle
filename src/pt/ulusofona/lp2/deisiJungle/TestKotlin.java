package pt.ulusofona.lp2.deisiJungle;

import org.junit.Assert;

public class TestKotlin {

    @org.junit.Test
    public void kotlin_getPlayerInfo_1() {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "Z"}};

        game.createInitialJungle(40, players, foodsInfo);


        Assert.assertEquals("0 | Pedro | Elefante | 180 | 1", FunctionsKt.getPlayerInfo(game, "Pedro"));
    }

    @org.junit.Test
    public void kotlin_getPlayerInfo_2() {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        game.moveCurrentPlayer(4, false);
        game.moveCurrentPlayer(6,false);

        Assert.assertEquals("0 | Pedro | Elefante | 164 | 5", FunctionsKt.getPlayerInfo(game, "Pedro"));
        Assert.assertEquals("1 | Valentim | Passaro | 46 | 7", FunctionsKt.getPlayerInfo(game, "Valentim"));
    }

    @org.junit.Test
    public void kotlin_getPlayersBySpecie_1() {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "E"}};

        game.createInitialJungle(40, players, foodsInfo);


        Assert.assertEquals("Pedro,Valentim", FunctionsKt.getPlayersBySpecie(game, 'E'));
    }

    @org.junit.Test
    public void kotlin_getPlayersBySpecie_2() {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "E"}};

        game.createInitialJungle(40, players, foodsInfo);


        Assert.assertEquals("", FunctionsKt.getPlayersBySpecie(game, 'H'));
    }

    @org.junit.Test
    public void kotlin_getPlayersBySpecie_3() {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "E"}};

        game.createInitialJungle(40, players, foodsInfo);


        Assert.assertEquals("", FunctionsKt.getPlayersBySpecie(game, 'L'));
    }

    @org.junit.Test
    public void kotlin_getMostTraveled_1() {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        game.moveCurrentPlayer(4, false);
        game.moveCurrentPlayer(6,false);


        Assert.assertEquals("Valentim:P:6\n" + "Pedro:E:4\n" + "Total:10", FunctionsKt.getMostTraveled(game));
    }

    @org.junit.Test
    public void kotlin_getMostTraveled_2() {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);


        Assert.assertEquals("Pedro:E:0\n" + "Valentim:P:0\n" + "Total:0", FunctionsKt.getMostTraveled(game));
    }
}
