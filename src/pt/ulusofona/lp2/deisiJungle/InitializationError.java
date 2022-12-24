package pt.ulusofona.lp2.deisiJungle;

public class InitializationError {

    private String message;

    public static InitializationError createInicializacionError(String message){
        if(message == null | message == ""){
            return null;
        }
        return new InitializationError(message);
    }
    private InitializationError(String message){
        this.message = message;
    }

    public String getMessage(){
        return "erro na criação do terreno";
    }
}
