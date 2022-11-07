package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameManager {

    //SPECIES
    Specie elephant = new Specie("Elefante","elephant.png", 'E');
    Specie lion = new Specie("Leão", "lion.png",'L' );
    Specie tarzan = new Specie("Tarzan", "tarzan.png", 'Z');
    Specie turtle = new Specie("Tartaruga", "turtle.png",'T');
    Specie bird = new Specie("Pássaro", "bird.png",'P');
    ArrayList<Specie> species = new ArrayList<>(Arrays.asList(elephant,lion,tarzan,turtle,bird));

    //PLAYERS
    ArrayList<Player> players = new ArrayList<>();
    HashMap<Integer, Player> playersById = new HashMap<>();



    Map map;



    //FUNCTIONS

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

        //check if tuhe players name are null or empty Strings
        for(int i = 0; i < playersInfo.length; i++){
            if(playersInfo[i][1] == "" || playersInfo[i][1] == null){
                return false;
            }
        }

        //O número de jogadores.
        if(playersInfo.length < 2 || playersInfo.length > 4){
            return false;
        }

        //O mapa tem de ter, pelo menos, duas posições por cada jogador que esteja em jogo.
        if (playersInfo.length*2 < jungleSize){
            return false;
        }








        //ASSUMINDO QUE JA PASSOU NAS VERIFICAÇOES TODAS
        //guardar os players num arraylist para utilizar ao longo do programa
        for(int i = 0; i < playersInfo.length; i++){

            int id=Integer.parseInt(playersInfo[i][0]);
            String name=playersInfo[i][1];
            char specieIdentifier=playersInfo[i][2].charAt(0);

            players.add(new Player(id, specieIdentifier,initialEnergy,name));
            playersById.put(id, new Player(id, specieIdentifier,initialEnergy,name));
        }

        map.size=jungleSize;
        map.generateMap(jungleSize);

        return true;
    }
    //ON GOING


    public int[] getPlayerIds(int squareNr){

        ArrayList<Player> playersInSquare = new ArrayList<>(map.getSquare(squareNr).getPlayers());
        int[] playerIDs = new int[playersInSquare.size()];

        if(playersInSquare.size()==0 || (squareNr<1 || squareNr>map.getSize())){
            return new int[]{};
        }

        for(int i=0; i<playersInSquare.size(); i++){
            playerIDs[i]=playersInSquare.get(i).getId();
        }

        return playerIDs;
    }
    //DONE


    public String[] getSquareInfo(int squareNr){

        if(squareNr<1 || squareNr>map.getSize()){
            return null;
        }

        Square square = map.getSquare(squareNr);
        String[] squareInfo = new String[3];

        if(square.type=="Meta"){
            squareInfo[0] = "finish.png";
        }
        squareInfo[0] = "blank.png";

        squareInfo[1] = square.getType();
        squareInfo[2] = square.getIdPlayersInString();

        return squareInfo;
    }
    //DONE


    public String[] getPlayerInfo(int playerId){

        String[] playerInfo = new String[4];

        playerInfo[0] = playersById.get(playerId).getId()+"";
        playerInfo[1] = playersById.get(playerId).getName();
        playerInfo[2] = playersById.get(playerId).getSpecieId()+"";
        playerInfo[3] = playersById.get(playerId).getEnergy()+"";

        return playerInfo;
    }
    //DONE


    public String[] getCurrentPlayerInfo(){
        return null;
    }

    public String[][] getPlayersInfo(){

        String[][] playersInfo = new String[players.size()][4];

        for(int i=0; i< players.size();i++){

            playersInfo[i][0] = players.get(i).getId()+"";
            playersInfo[i][1] = players.get(i).getName();
            playersInfo[i][2] = players.get(i).getSpecieId()+"";
            playersInfo[i][3] = players.get(i).getEnergy()+"";
        }

        return playersInfo;
    }
    //DONE

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations){
        return true;
    }

    public String[] getWinnerInfo(){
        return null;
    }

    public ArrayList<String> getGameResults(){
        return new ArrayList<>();
    }

    public JPanel getAuthorsPanel(){
        return null;
    }

    public String whoIsTaborda(){
        return "Wrestling";
    }
}