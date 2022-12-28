package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class GameManager {

    //SPECIES
    ArrayList<Specie> species = new ArrayList<>()
    {
        {
            add(new Elephant());
            add(new Lion());
            add(new Turtle());
            add(new Bird());
            add(new Tarzan());

        }
    };

    //FOODS
    ArrayList<Food> foods = new ArrayList<>()
    {
        {
            add(new Water());
            add(new Grass());
            add(new Banana());
            add(new Meat());
            add(new Mushrooms());

        }
    };


    //PLAYERS
    ArrayList<Player> players = new ArrayList<>();
    HashMap<Integer, Player> playersById = new HashMap<>();

    //OTHERS
     Map map;
     private int turn = 0;
     private int numberOfPlays=0;



    ///*---------- MANDITORY FUNCTIONS ----------*///

    //////FIRST FUNCTION - getSpecies()
    public String[][] getSpecies(){
        String[][] result = new String[species.size()][7];

        int cnt = 0;
        for (Specie specie : species) {
            result[cnt][0] = specie.getIdentifier()+"";
            result[cnt][1] = specie.getName();
            result[cnt][2] = specie.getImage();
            result[cnt][3] = specie.getInitialEnergy()+"";
            result[cnt][4] = specie.getEnergyConsume()+"";
            result[cnt][5] = specie.getEnrgyEarnedByRest()+"";
            result[cnt][6] = specie.getSpeed()[0] + ".." + specie.getSpeed()[1];
            cnt++;
        }
        return result;
    }
    ///DONE


    //////SECOND FUNCTION - getFoodTypes()
    public String[][] getFoodTypes(){
        String[][] result = new String[foods.size()][3];

        int cnt = 0;
        for (Food food : foods) {
            result[cnt][0] = food.getIdentifier()+"";
            result[cnt][1] = food.getName();
            result[cnt][2] = food.getImage();

            cnt++;
        }
        return result;
    }
    ///DONE


    //////THIRD FUNCTION - createInitialJungle()
    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo){

        reset();

        players = new ArrayList<>();
        playersById = new HashMap<>();

        ArrayList<Integer> repeated = new ArrayList<>();



        //VERIFICATIONS================================================================
        //check if the number of players is valid
        if(playersInfo.length < 2 || playersInfo.length > 4){
            return new InitializationError("numero de jogadores invalidos");
        }

        //O mapa tem de ter, pelo menos, duas posições por cada jogador que esteja em jogo.
        if (playersInfo.length*2 > jungleSize){
            return new InitializationError("O mapa tem de ter, pelo menos, duas posições por cada jogador que esteja em jogo");
        }
        //END OF VERIFICATIONS================================================================


        //VERIFICATIONS================================================================
        //check if playersInfo is valid
        for (int i=0; i<playersInfo.length; i++) {

            //check if there are any players Ids duplicated
            try{
                if(!repeated.contains(Integer.parseInt(playersInfo[i][0]))){
                    repeated.add(Integer.parseInt(playersInfo[i][0]));
                }else {
                    return new InitializationError("id de jgador invalido");
                }
            }catch (NumberFormatException e){
                return new InitializationError("id de jgador invalido");
            }

            //check if the character os the specie passed on players info is valid
            if(!isSpecieValid(playersInfo[i][2].charAt(0))){
                return new InitializationError("especie invalida");
            }

            //check if the name of the player is valid
            if (playersInfo[i][1] == null || playersInfo[i][1].equals("")) {
                return new InitializationError("nome invalido");
            }

            //END OF VERIFICATIONS================================================================

            //COMO JA PASSOU AS VALIDAÇOES COLOCA AS VARIAVEIS
            int id=Integer.parseInt(playersInfo[i][0]);
            String name=playersInfo[i][1];
            Specie specie = getSpeciByID(playersInfo[i][2].charAt(0));

            players.add(new Player(id,specie.getInitialEnergy(), specie, name));
            playersById.put(id, new Player(id,specie.getInitialEnergy(), specie, name));
        }

        //create map
        map = new Map(jungleSize);
        sortPlayersById(players);

        //Preenche a 1 casa
        for(Player player : players){
            map.getSquare(1).addPlayer(player);
        }

        //VERIFICATIONS================================================================
        //check if foodsInfo is valid and place it on the map
        if(foodsInfo != null){

            for(int i=0; i<foodsInfo.length; i++){

                if(foodsInfo[i][0] == null || Objects.equals(foodsInfo[i][0], "")){
                    return new InitializationError("id de comida inválido");
                }

                if(!isFoodIdValid(foodsInfo[i][0].charAt(0))){
                    return new InitializationError("id de comida inválido");
                }

                if(!isFoodPositionValid(Integer.parseInt(foodsInfo[i][1]), jungleSize)){
                    return new InitializationError("posição de comida inválida");
                }

                //END OF VERIFICATIONS================================================================
                //COMO JA PASSOU AS VALIDAÇOES COLOCA AS VARIAVEIS

                map.placeFood(Integer.parseInt(foodsInfo[i][1]), getFoodById(foodsInfo[i][0].charAt(0)));
            }
        }

        return null;
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo){
        return createInitialJungle(jungleSize, playersInfo, null);
    }
    ///DONE


    //////FOURTH FUNCTION - getPlayerIds()
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
    ///DONE


    //////FIFTH FUNCTION - getSquareInfo()
    public String[] getSquareInfo(int squareNr){

        if(!map.isSquareValid(squareNr)){
            return null;
        }

        Square square = map.getSquare(squareNr);
        String[] squareInfo = new String[3];

        squareInfo[0] = square.getImage();
        squareInfo[1] = square.getType(numberOfPlays);
        squareInfo[2] = square.getIdPlayersInString();

        return squareInfo;
    }
    ///DONE


    //////SIXTH FUNCTION - getPlayerInfo()
    public String[] getPlayerInfo(int playerId){

        String[] playerInfo = new String[5];

        playerInfo[0] = playersById.get(playerId).getId()+"";
        playerInfo[1] = playersById.get(playerId).getName();
        playerInfo[2] = playersById.get(playerId).getSpecie().getIdentifier()+"";
        playerInfo[3] = playersById.get(playerId).getEnergy()+"";

        int first = playersById.get(playerId).getSpecie().getSpeed()[0];
        int last = playersById.get(playerId).getSpecie().getSpeed()[1];
        String speed = ""+first+".."+last;
        playerInfo[4] = speed;

        return playerInfo;
    }
    ///DONE


    //////SEVENTH FUNCTION - getCurrentPlayerInfo()
    public String[] getCurrentPlayerInfo(){

        String[] playerInfo = new String[4];

        playerInfo[0] = players.get(turn).getId()+"";
        playerInfo[1] = players.get(turn).getName();
        playerInfo[2] = players.get(turn).getSpecie().getIdentifier()+"";
        playerInfo[3] = players.get(turn).getEnergy()+"";

        return playerInfo;
    }
    ///DONE


    //////EIGHT FUNCTION - getCurrentPlayerEnergyInfo()
    public String[] getCurrentPlayerEnergyInfo(int nrPositions){
        Player currentPlayer = players.get(turn);

        String energyConsumed = (currentPlayer.getSpecie().getEnergyConsume()*nrPositions) + "";
        String energyRest = currentPlayer.getSpecie().getEnrgyEarnedByRest()+"";

        return new String[]{energyConsumed, energyRest};
    }
    ///DONE


    //////NINTH FUNCTION - getPlayersInfo()
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
    ///DONE


    //////TENTH FUNCTION - moveCurrentPlayer()
    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations){

        Player currentPlayer = players.get(turn);
        Square initialSquare = map.getSquare(currentPlayer.getPosition());


        if(!bypassValidations){
            if(nrSquares<1 || nrSquares>6){
                nextTurn();
                return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
            }
        }

        if(currentPlayer.getEnergy()<2){
            nextTurn();
            return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
        }

        if(currentPlayer.getPosition()+nrSquares >= map.getSize()){
            Square desiredSquare = map.getSquare(map.getSize());

            initialSquare.removePlayer(currentPlayer);
            desiredSquare.addPlayer(currentPlayer);
            currentPlayer.removeEnergy();
            currentPlayer.updatePosition(nrSquares);

            nextTurn();
            return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
        }



        Square desiredSquare = map.getSquare(currentPlayer.getPosition()+nrSquares);

        initialSquare.removePlayer(currentPlayer);
        desiredSquare.addPlayer(currentPlayer);
        currentPlayer.removeEnergy();
        currentPlayer.updatePosition(nrSquares);

        nextTurn();
        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);

    }
    ///ON GOING !!!


    //////ELEVENTH FUNCTION - getWinnerInfo()
    public String[] getWinnerInfo(){

        if(!checkIfGameEnded()){
            return null;
        }

        sortPlayersByPocision();

        int nbrPlayersSamePosition = 0;
        int positionOfFirstPlace = players.get(0).getPosition();

        for(int i = 1; i < players.size(); i++){
            if(players.get(i).getPosition() == positionOfFirstPlace){
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
    ///DONE


    //////TWELFTH FUNCTION - getGameResults()
    public ArrayList<String> getGameResults(){

        ArrayList<String> result =  new ArrayList<>();

        int counter = 1;


        for(int i = map.getSize() ; i >= 1; i--){

            if(map.getSquare(i).getPlayers().size() > 1){
                sortPlayersById(map.getSquare(i).getPlayers());

                for(Player player : map.getSquare(i).getPlayers()){
                    result.add("#"+counter+" "+player.getName()+", "+player.getSpecie().getName()+", "+player.getPosition()
                            +", "+player.getDistanceWalked()+", "+player.getFoodEaten());
                    counter++;
                }
            }
            if(map.getSquare(i).getPlayers().size() == 1){
                Player player = map.getSquare(i).getPlayers().get(0);
                result.add("#"+counter+" "+player.getName()+", "+player.getSpecie().getName()+", "+player.getPosition()
                        +", "+player.getDistanceWalked()+", "+player.getFoodEaten());
                counter++;
            }
        }

        return result;
    }
    ///DONE


    //////THIRTEENTH FUNCTION - getAuthorsPanel()
    public JPanel getAuthorsPanel(){

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300,300));

        JTextArea text = new JTextArea("ola o meu nome e pedro");

        panel.setBackground(Color.BLACK);

        JButton buttom = new JButton("Teste");
        //panel.add(buttom);
        panel.add(text);

        return panel;
    }
    ///ON GOING !!!


    //////FOURTEENTH FUNCTION - whoIsTaborda()
    public String whoIsTaborda(){
        return "Wrestling";
    }
    ///DONE


    //////FIFTEENTH FUNCTION - saveGame()
    public boolean saveGame(File file){
        return true;
    }
    ///ON GOING !!!


    //////SIXTEENTH FUNCTION - loadGame()
    public boolean loadGame(File file){
        return true;
    }
    ///ON GOING !!!



    ///*---------- OTHER FUNCTIONS ----------*///

    ///GETTERS
    public Map getMap() {
        return map;
    }

    public int getTurn() {
        return turn;
    }

    public int getNumberOfPlays() {
        return numberOfPlays;
    }
    ///GETTERS

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

        numberOfPlays++;
        //System.out.println(turn);
    }
    //ASSUMINDO QUE CADA JOGADA DE CADA JOGADOR CONTA COMO UM TURNO, VERIFICAR

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
        //https://www.geeksforgeeks.org/sorting-in-java/--
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

    public void reset() {
        map = null;
        players = new ArrayList<>();
    }

    public Specie getSpeciByID(char id){
        for (Specie specie : species) {
            if(id == specie.getIdentifier()){
                return specie;
            }
        }
        return null;
    }

    public Food getFoodById(char id){
        for (Food food : foods) {
            if(id == food.getIdentifier()){
                return food;
            }
        }
        return null;
    }

    public boolean isSpecieValid(char specieId){
        for(Specie specie : species){
            if(specie.getIdentifier() == specieId){
                return true;
            }
        }
        return false;
    }

    public boolean isFoodIdValid(char foodId){
        for (Food food : foods) {
            if(food.getIdentifier() == foodId){
                return true;
            }
        }
        return false;
    }

    public boolean isFoodPositionValid(int position, int jnglSz){
        return (!(position >= jnglSz || position <= 0));
    }
}