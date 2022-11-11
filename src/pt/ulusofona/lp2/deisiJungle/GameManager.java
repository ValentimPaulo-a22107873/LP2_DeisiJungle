package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class GameManager {

    //SPECIES
    Specie elephant = new Specie("Elefante","elephant.png", 'E');
    Specie lion = new Specie("Leão", "lion.png",'L' );
    Specie tarzan = new Specie("Tarzan", "tarzan.png", 'Z');
    Specie turtle = new Specie("Tartaruga", "turtle.png",'T');
    Specie bird = new Specie("Pássaro", "bird.png",'P');

    ArrayList<Specie> species = new ArrayList<>()
    {
        {
            add(elephant);
            add(lion);
            add(tarzan);
            add(turtle);
            add(bird);
        }
    };

    //PLAYERS
    ArrayList<Player> players = new ArrayList<>();
    HashMap<Integer, Player> playersById = new HashMap<>();

    //OTHERS
    Map map;
    int turn;




    //MANDITORY FUNCTIONS

    public String[][] getSpecies(){
        String[][] result = new String[species.size()][3];

        for(int i = 0; i < species.size(); i++){

            result[i][0] = species.get(i).getIdentifier()+"";
            result[i][1] = species.get(i).getName();
            result[i][2] = species.get(i).getImage();

        }
        return result;
    }
    //DONE


    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo){

        players = new ArrayList<>();
        playersById = new HashMap<>();

        //O número de jogadores.
        if(playersInfo.length < 2 || playersInfo.length > 4){
            return false;
        }

        //O mapa tem de ter, pelo menos, duas posições por cada jogador que esteja em jogo.
        if (playersInfo.length*2 > jungleSize){
            return false;
        }

        ArrayList<Integer> playerIds = new ArrayList<>();

        for(int i=0; i<playersInfo.length; i++){

            try{
                if(!playerIds.contains(Integer.parseInt(playersInfo[i][0]))){
                    playerIds.add(Integer.parseInt(playersInfo[i][0]));
                }else {
                    return false;
                }
            }catch (NumberFormatException e){
                return false;
            }

            //Verifica se a espécie existe
            boolean validSpecie=false;

            for(Specie specie : species){
                if(specie.getIdentifier() == playersInfo[i][2].charAt(0)){
                    validSpecie=true;
                    break;
                }
            }

            if(!validSpecie){
                return false;
            }

            //Verifica se o nome e valido
            if (playersInfo[i][1] == null || playersInfo[i][1].equals("")) {
                return false;
            }

            //COMO JA PASSOU AS VALIDAÇOES COLOCA AS VARIAVEIS
            int id=Integer.parseInt(playersInfo[i][0]);
            String name=playersInfo[i][1];
            char specieIdentifier=playersInfo[i][2].charAt(0);

            players.add(new Player(id,initialEnergy,getSpicieById(specieIdentifier),name));
            playersById.put(id, new Player(id,initialEnergy,getSpicieById(specieIdentifier),name));

        }
        map = new Map(jungleSize);
        sortPlayersById(players);

        //System.out.println(players);

        //Preenche a 1 casa
        for(Player player : players){
            map.getSquare(1).addPlayer(player);
        }

        return true;
    }
    //ON GOING


    public int[] getPlayerIds(int squareNr){

        if(!map.isSquareValid(squareNr)){
            return new int[]{};
        }

        ArrayList<Player> playersInSquare = map.getSquare(squareNr).getPlayers();

        int[] playerIDs = new int[playersInSquare.size()];

        if(playersInSquare.size()==0){
            return new int[]{};
        }

        for(int i=0; i<playersInSquare.size(); i++){
            playerIDs[i]=playersInSquare.get(i).getId();
        }

        return playerIDs;
    }
    //DONE


    public String[] getSquareInfo(int squareNr){

        if(!map.isSquareValid(squareNr)){
            return null;
        }

        Square square = map.getSquare(squareNr);
        String[] squareInfo = new String[3];

        squareInfo[0] = square.getImage();
        squareInfo[1] = square.getType();
        squareInfo[2] = square.getIdPlayersInString();

        return squareInfo;
    }
    //DONE


    public String[] getPlayerInfo(int playerId){

        String[] playerInfo = new String[4];

        playerInfo[0] = playersById.get(playerId).getId()+"";
        playerInfo[1] = playersById.get(playerId).getName();
        playerInfo[2] = playersById.get(playerId).getSpecie().getIdentifier()+"";
        playerInfo[3] = playersById.get(playerId).getEnergy()+"";

        return playerInfo;
    }
    //DONE


    public String[] getCurrentPlayerInfo(){

        String[] playerInfo = new String[4];

        playerInfo[0] = players.get(turn).getId()+"";
        playerInfo[1] = players.get(turn).getName();
        playerInfo[2] = players.get(turn).getSpecie().getIdentifier()+"";
        playerInfo[3] = players.get(turn).getEnergy()+"";

        return playerInfo;
    }
    //ON GOING


    public String[][] getPlayersInfo(){

        String[][] playersInfo = new String[players.size()][4];

        for(int i=0; i< players.size();i++){

            playersInfo[i][0] = players.get(i).getId()+"";
            playersInfo[i][1] = players.get(i).getName();
            playersInfo[i][2] = players.get(i).getSpecie().getIdentifier()+"";
            playersInfo[i][3] = players.get(i).getEnergy()+"";
        }

        return playersInfo;
    }
    //DONE

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations){

        Player currentPlayer = players.get(turn);
        Square initialSquare = map.getSquare(currentPlayer.getPosition());
        Square desiredSquare;

        if(bypassValidations){

            if(currentPlayer.getEnergy()<2){
                nextTurn();
                return false;
            }

            if(currentPlayer.getPosition()+nrSquares >= map.getSize()){
                desiredSquare = map.getSquare(map.getSize());

                initialSquare.removePlayer(currentPlayer);
                desiredSquare.addPlayer(currentPlayer);
                currentPlayer.energy -= 2;
                currentPlayer.position += nrSquares;

                nextTurn();
                return true;
            }

            desiredSquare = map.getSquare(currentPlayer.getPosition()+nrSquares);

            initialSquare.removePlayer(currentPlayer);
            desiredSquare.addPlayer(currentPlayer);
            currentPlayer.energy -= 2;
            currentPlayer.position += nrSquares;

            nextTurn();
            return true;
        }

        if(nrSquares<1 || nrSquares>6){
            nextTurn();
            return false;
        }
        if(currentPlayer.getEnergy()<2){
            nextTurn();
            return false;
        }

        if(currentPlayer.getPosition()+nrSquares >= map.getSize()){
            desiredSquare = map.getSquare(map.getSize());

            initialSquare.removePlayer(currentPlayer);
            desiredSquare.addPlayer(currentPlayer);
            currentPlayer.energy -= 2;
            currentPlayer.position += nrSquares;

            nextTurn();
            return true;
        }

        desiredSquare = map.getSquare(currentPlayer.getPosition()+nrSquares);

        initialSquare.removePlayer(currentPlayer);
        desiredSquare.addPlayer(currentPlayer);
        currentPlayer.energy -= 2;
        currentPlayer.position += nrSquares;

        nextTurn();
        return true;

    }

    public String[] getWinnerInfo(){

        if(!checkIfGameEnded()){
            return null;
        }

        sortPlayersByPocision();

        int nbrPlayersSamePosition = 0;
        int positionOfFitstPlace = players.get(0).getPosition();

        for(int i = 1; i < players.size(); i++){
            if(players.get(i).getPosition() == positionOfFitstPlace){
                nbrPlayersSamePosition++;
            }
        }

        ArrayList<Player> helper = new ArrayList<>();

        if(nbrPlayersSamePosition >= 1){
            for(int x = 0 ; x <  nbrPlayersSamePosition; x++){
                helper.add(players.get(x));
            }
            sortPlayersById(helper);

            return getPlayerInfo(helper.get(0).getId());
        }
        return getPlayerInfo(players.get(0).getId());
    }

    public ArrayList<String> getGameResults(){

        ArrayList<String> result =  new ArrayList<>();

        int counter = 1;


        for(int i = map.getSize() ; i >= 1; i--){

            if(map.getSquare(i).getPlayers().size() > 1){
                sortPlayersById(map.getSquare(i).getPlayers());

                for(Player player : map.getSquare(i).getPlayers()){
                    result.add("#"+counter + " " + player.getName()+ ", " + player.getSpecie().getName() + ", " + player.getPosition());
                    counter++;
                }
            }
            if(map.getSquare(i).getPlayers().size() == 1){
                Player player = map.getSquare(i).getPlayers().get(0);
                result.add("#"+counter + " " + player.getName()+ ", " + player.getSpecie().getName() + ", " + player.getPosition());
                counter++;
            }
        }

        return result;
    }

    public JPanel getAuthorsPanel(){

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300,300,300,300));

        JButton button = new JButton("PNG - Tenis da Nike");

        panel.add(button, BorderLayout.CENTER);
        return null;
    }

    public String whoIsTaborda(){
        return "Wrestling";
    }
    //DONE












    //ADDITIONAL FUNCTIONS

    Specie getSpicieById(char id){
        for(Specie specie : species){
            if(specie.getIdentifier() == id){
                return specie;
            }
        }
        return null;
    }

    ArrayList<Player> sortPlayersById(ArrayList<Player> players){
        for (int i = 0; i < players.size(); i++) {

            // Inner nested loop pointing 1 index ahead
            for (int j = i + 1; j < players.size(); j++) {

                // Checking elements
                Player temp;
                if (players.get(j).getId() < players.get(i).getId()) {

                    // Swapping
                    temp = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, temp);
                }
            }
        }
        return players;
    }


    void nextTurn(){
        if(turn == players.size()-1){
            turn = 0;
            //System.out.println(turn);
        }else {
            turn++;
        }

        //System.out.println(turn);
    }




    boolean checkIfGameEnded(){

        //o jogo acaba se todos estiverem sem energia

        boolean resultA = true;
        boolean resultB = true;

        for(Player player : players){
            if(player.getEnergy() >= 2){
                resultA = false;
            }
        }

        // chek if someone ios on the last
        if(map.getSquare(map.getSize()).getPlayers().size() < 1){
            resultB = false;
        }

        return resultA || resultB;
    }

    void sortPlayersByPocision(){
        for (int i = 0; i < players.size(); i++) {

            // Inner nested loop pointing 1 index ahead
            for (int j = i + 1; j < players.size(); j++) {

                // Checking elements
                Player temp;
                if (players.get(j).getPosition() > players.get(i).getPosition()) {

                    // Swapping
                    temp = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, temp);
                }
            }
        }
    }


}