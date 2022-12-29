package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
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

    //FOODS FOR VERIFICATIONS
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
    ArrayList<Player> playersByPosition = new ArrayList<>();

    //OTHERS
     Map map;
     private int turn = 0;
     private int numberOfPlays=1;



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
        }

        //create map
        map = new Map(jungleSize);
        playersByPosition = players;
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

                try{
                    if(!isFoodPositionValid(Integer.parseInt(foodsInfo[i][1]), jungleSize)){
                        return new InitializationError("posição de comida inválida");
                    }

                    //END OF VERIFICATIONS================================================================
                    //COMO JA PASSOU AS VALIDAÇOES COLOCA AS VARIAVEIS

                    map.placeFood(Integer.parseInt(foodsInfo[i][1]), newFood(foodsInfo[i][0].charAt(0)));

                }catch (NumberFormatException e) {
                    return new InitializationError("posição de comida inválida");
                }
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
        squareInfo[1] = square.getType(numberOfPlays-1);
        squareInfo[2] = square.getIdPlayersInString();

        return squareInfo;
    }
    ///DONE


    //////SIXTH FUNCTION - getPlayerInfo()
    public String[] getPlayerInfo(int playerId){

        String[] playerInfo = new String[5];
        Player player = new Player();

        for(int i=0; i<players.size(); i++){
            if(players.get(i).getId()==playerId){
                player = players.get(i);
            }
        }

        playerInfo[0] = player.getId()+"";
        playerInfo[1] = player.getName();
        playerInfo[2] = player.getSpecie().getIdentifier()+"";
        playerInfo[3] = player.getEnergy()+"";

        int first = player.getSpecie().getSpeed()[0];
        int last = player.getSpecie().getSpeed()[1];
        String speed = ""+first+".."+last;
        playerInfo[4] = speed;

        return playerInfo;
    }
    ///DONE


    //////SEVENTH FUNCTION - getCurrentPlayerInfo()
    public String[] getCurrentPlayerInfo(){

        String[] playerInfo = new String[5];

        playerInfo[0] = players.get(turn).getId()+"";
        playerInfo[1] = players.get(turn).getName();
        playerInfo[2] = players.get(turn).getSpecie().getIdentifier()+"";
        playerInfo[3] = players.get(turn).getEnergy()+"";

        int first = players.get(turn).getSpecie().getSpeed()[0];
        int last = players.get(turn).getSpecie().getSpeed()[1];
        String speed = ""+first+".."+last;
        playerInfo[4] = speed;


        return playerInfo;
    }
    ///DONE


    //////EIGHT FUNCTION - getCurrentPlayerEnergyInfo()
    public String[] getCurrentPlayerEnergyInfo(int nrPositions){
        Player currentPlayer = players.get(turn);

        String energyConsumed = (currentPlayer.getSpecie().getEnergyConsume()*Math.abs(nrPositions)) + "";
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
        nextTurn();

        //VERIFICATIONS================================================================
        //CHECK IF NUMBER OF SQUARES IS VALID
        if(!bypassValidations){
            if(nrSquares<-6 || nrSquares>6){
                numberOfPlays++;
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT,null);
            }
        }

        //CHECK IF PLAYER CHOOSES TO GO BACKWARDS WONT GO BEHIND FIRST HOUSE
        if(currentPlayer.getPosition()+nrSquares<1){
            numberOfPlays++;
            return new MovementResult(MovementResultCode.INVALID_MOVEMENT,null);
        }

        //CHECK IF WANTS TO REST
        if(nrSquares==0){
            currentPlayer.rest();
        }else{

            int valid = currentPlayer.move(nrSquares, map.getSize());

            if(valid == 3){
                numberOfPlays++;
                return new MovementResult(MovementResultCode.NO_ENERGY,null);
            }

            if(valid == 2 && !bypassValidations){
                numberOfPlays++;
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT,null);
            }
        }

        Square desiredSquare = map.getSquare(currentPlayer.getPosition());
        if(nrSquares!=0){
            initialSquare.removePlayer(currentPlayer);
            desiredSquare.addPlayer(currentPlayer);
        }

        if(desiredSquare.getFood()!=null){

            boolean valid = currentPlayer.eat(desiredSquare.getFood(), numberOfPlays);

            if(valid){
                numberOfPlays++;
                return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou "+ desiredSquare.getFood().getName());
            }
            numberOfPlays++;
            return new MovementResult(MovementResultCode.VALID_MOVEMENT,null);
        }
        numberOfPlays++;
        return new MovementResult(MovementResultCode.VALID_MOVEMENT,null);
    }
    ///ON GOING !!!


    //////ELEVENTH FUNCTION - getWinnerInfo()
    public String[] getWinnerInfo(){

        if(!checkIfGameEnded()){
            return null;
        }

        if(playerToFar()){
            return getPlayerInfo(playersByPosition.get(1).getId());
        }
        return getPlayerInfo(map.getSquare(map.getSize()).getPlayers().get(0).getId());
    }
    ///DONE


    //////TWELFTH FUNCTION - getGameResults()
    public ArrayList<String> getGameResults(){

        ArrayList<String> result =  new ArrayList<>();
        int counter = 1;

        for(int x = map.getSize() ; x >= 1; x--){

            ArrayList<Player> players = sortPlayersById(map.getSquare(x).getPlayers());

            for(int y=0; y<players.size(); y++){

                Player player = players.get(y);
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

        //System.out.println(turn);
    }
    //ASSUMINDO QUE CADA JOGADA DE CADA JOGADOR CONTA COMO UM TURNO, VERIFICAR

    boolean checkIfGameEnded(){

        //o jogo acaba se todos estiverem sem energia
        if(playerToFar()){
            return true;
        }

        // chek if someone ios on the last
        if(map.getSquare(map.getSize()).getPlayers().size() >= 1){
            return true;
        }

        return false;
    }

    void sortPlayersByPocision(){
        //https://www.geeksforgeeks.org/sorting-in-java/--
        for (int i = 0; i < playersByPosition.size(); i++) {

            // Inner nested loop pointing 1 index ahead
            for (int j = i + 1; j < playersByPosition.size(); j++) {

                // Checking elements
                Player temp;
                if (playersByPosition.get(j).getPosition() > playersByPosition.get(i).getPosition()) {

                    // Swapping
                    temp = playersByPosition.get(i);
                    playersByPosition.set(i, playersByPosition.get(j));
                    playersByPosition.set(j, temp);
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
        return (!(position >= jnglSz || position < 1));
    }

    boolean playerToFar(){

        sortPlayersByPocision();

        int firstPosition = playersByPosition.get(0).getPosition();
        int secondPosition = playersByPosition.get(1).getPosition();

        if((firstPosition-secondPosition) > map.getSize()/2){
            return true;
        }

        return false;
    }

    Food newFood(char id){
        if(id == 'a'){
            return new Water();
        }
        if(id == 'e'){
            return new Grass();
        }
        if(id == 'm'){
            return new Mushrooms();
        }
        if(id == 'c'){
            return new Meat();
        }
        return new Banana();
    }
}