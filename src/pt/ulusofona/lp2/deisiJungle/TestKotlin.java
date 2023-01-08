package pt.ulusofona.lp2.deisiJungle;

import org.junit.Assert;

public class TestKotlin {

    @org.junit.Test
    public void kotlin_getPlayerInfo_1() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "Z"}};

        game.createInitialJungle(40, players, foodsInfo);


        Assert.assertEquals("0 | Pedro | Elefante | 180 | 1", FunctionsKt.getPlayerInfo(game, "Pedro"));
    }

    @org.junit.Test
    public void kotlin_getPlayerInfo_2() throws InvalidInitialJungleException {
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
    public void kotlin_getPlayersBySpecie_1() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "E"}};

        game.createInitialJungle(40, players, foodsInfo);


        Assert.assertEquals("Valentim,Pedro", FunctionsKt.getPlayersBySpecie(game, "E"));
    }

    @org.junit.Test
    public void kotlin_getPlayersBySpecie_2() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "E"}};

        game.createInitialJungle(40, players, foodsInfo);


        Assert.assertEquals("", FunctionsKt.getPlayersBySpecie(game, "H"));
    }

    @org.junit.Test
    public void kotlin_getPlayersBySpecie_3() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "E"}};

        game.createInitialJungle(40, players, foodsInfo);


        Assert.assertEquals("", FunctionsKt.getPlayersBySpecie(game, "L"));
    }

    @org.junit.Test
    public void kotlin_getMostTraveled_1() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        game.moveCurrentPlayer(4, false);
        game.moveCurrentPlayer(6,false);


        Assert.assertEquals("Valentim:P:6\n" + "Pedro:E:4\n" + "Total:10", FunctionsKt.getMostTraveled(game, ""));
    }

    @org.junit.Test
    public void kotlin_getMostTraveled_2() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "E"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);


        Assert.assertEquals("Pedro:E:0\n" + "Valentim:P:0\n" + "Total:0", FunctionsKt.getMostTraveled(game, ""));
    }

    @org.junit.Test
    public void kotlin_getTopEnergeticOmnivores_1() throws InvalidInitialJungleException{
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "Z"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        game.moveCurrentPlayer(4, false);

        Assert.assertEquals("Valentim:70\nPedro:62", FunctionsKt.getTopEnergeticOmnivores(game,"5"));
    }

    @org.junit.Test
    public void kotlin_getTopEnergeticOmnivores_2() throws InvalidInitialJungleException{
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "Z"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        game.moveCurrentPlayer(4, false);
        game.moveCurrentPlayer(6, false);

        Assert.assertEquals("Pedro:62", FunctionsKt.getTopEnergeticOmnivores(game,"1"));
    }

    @org.junit.Test
    public void kotlin_getTopEnergeticOmnivores_3() throws InvalidInitialJungleException{
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "Z"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        game.moveCurrentPlayer(4, false);
        game.moveCurrentPlayer(6, false);

        Assert.assertEquals("", FunctionsKt.getTopEnergeticOmnivores(game,"0"));
    }

    @org.junit.Test
    public void kotlin_getConsumedFood_1() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"b", "6"}, {"c", "3"}};
        String[][] players = {{"0", "Pedro", "T"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        game.moveCurrentPlayer(2,false);
        game.moveCurrentPlayer(5,false);

        Assert.assertEquals("Bananas\nCarne", FunctionsKt.getConsumedFood(game, ""));
        Assert.assertEquals("Bananas\nCarne", FunctionsKt.getConsumedFood(game, ""));
    }

    @org.junit.Test
    public void kotlin_getConsumedFood_2() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "T"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);

        Assert.assertEquals("", FunctionsKt.getConsumedFood(game, ""));
    }

    @org.junit.Test
    public void kotlin_postMove_1() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "T"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        String result = FunctionsKt.postMove(game,"-2");
        Assert.assertEquals(1, game.getPlayers().get(0).getPosition());
        Assert.assertEquals("Movimento invalido", result);
    }

    @org.junit.Test
    public void kotlin_postMove_2() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "T"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        String result = FunctionsKt.postMove(game,"3");
        Assert.assertEquals(4, game.getPlayers().get(0).getPosition());
        Assert.assertEquals("OK", result);
    }

    @org.junit.Test
    public void kotlin_postMove_3() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "T"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        String result = FunctionsKt.postMove(game,"2");
        Assert.assertEquals(3, game.getPlayers().get(0).getPosition());
        Assert.assertEquals("Apanhou comida", result);
    }

    @org.junit.Test
    public void kotlin_postMove_4() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "T"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        game.moveCurrentPlayer(1,false);
        game.moveCurrentPlayer(6,false);
        game.moveCurrentPlayer(1,false);
        game.moveCurrentPlayer(6,false);
        game.moveCurrentPlayer(1,false);
        String result = FunctionsKt.postMove(game,"6");


        Assert.assertEquals(4, game.getPlayers().get(0).getPosition());
        Assert.assertEquals(13, game.getPlayers().get(1).getPosition());
        Assert.assertEquals("Sem energia", result);
    }

    @org.junit.Test
    public void kotlin_postMove_5() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "T"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        String result = FunctionsKt.postMove(game,"-7");
        Assert.assertEquals(1, game.getPlayers().get(0).getPosition());
        Assert.assertEquals("Movimento invalido", result);
    }

    @org.junit.Test
    public void kotlin_postMove_6() throws InvalidInitialJungleException {
        GameManager game = new GameManager();

        String[][] foodsInfo = {{"c", "3"}, {"b", "6"}};
        String[][] players = {{"0", "Pedro", "T"}, {"1", "Valentim", "P"}};

        game.createInitialJungle(40, players, foodsInfo);
        String result = FunctionsKt.postMove(game,"10");
        Assert.assertEquals(11, game.getPlayers().get(0).getPosition());
        Assert.assertEquals("OK", result);
    }
}
