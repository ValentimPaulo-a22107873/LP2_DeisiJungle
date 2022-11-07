package pt.ulusofona.lp2.deisiJungle;

public class Player {

    int id, energy, position=1;
    char specieId;
    Specie specie;
    String name;

    public Player() {
    }

    public Player(int id, char specieId, int energy, String name) {
        this.id = id;
        this.specieId = specieId;
        this.energy = energy;
        this.name = name;
    }

    public Player(int id, int energy, Specie specie, String name) {
        this.id = id;
        this.energy = energy;
        this.specie = specie;
        this.name = name;
    }


    //CONSTRUTORES

    Specie getSpecie(){
        return specie;
    }

    int getId(){
        return id;
    }

    char getSpecieId(){
        return specieId;
    }

    int getEnergy(){
        return energy;
    }

    int getPosition(){
        return position;
    }

    String getName(){
        return name;
    }

    //FUNCOES PARA OBTER QUALQUER INFO DO PLAYER









}
