package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameManager {

    Specie elephant = new Specie("Elefante","elephant.png", 'E');
    Specie lion = new Specie("Leão", "lion.png",'L' );
    Specie tarzan = new Specie("Tarzan", "tarzan.png", 'Z');
    Specie turtle = new Specie("Tartaruga", "turtle.png",'T');
    Specie bird = new Specie("Pássaro", "bird.png",'P');

    ArrayList<Specie> species = new ArrayList<>(Arrays.asList(elephant,lion,tarzan,turtle,bird));

    Map map;



    public String[][] getSpecies(){
        String[][] result = new String[species.size()][3];

        for(int i = 0; i < species.size(); i++){

            result[i][0] = species.get(i).specieIdentifier+"";
            result[i][1] = species.get(i).name;
            result[i][2] = species.get(i).image;

        }
        return result;
    }


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

        for(int i = 0; i < playersInfo.length; i++){
        }



        map.generateMap(jungleSize);

        return true;
    }



















    public int[] getPlayerIds(int squareNr){
        return null;
    }

    public String[] getSquareInfo(int squareNr){
        return null;
    }

    public String[] getPlayerInfo(int playerId){
        return null;
    }

    public String[] getCurrentPlayerInfo(){
        return null;
    }

    public String[][] getPlayersInfo(){
        return null;
    }

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