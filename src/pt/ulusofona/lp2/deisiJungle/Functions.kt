package pt.ulusofona.lp2.deisiJungle

enum class CommandType {GET, POST}


fun router() : (CommandType) -> Function2<GameManager,List<String>,String>{
    return ::callFunction
}

fun callFunction(comand : CommandType) : Function2<GameManager,List<String>,String>{
    if(comand==CommandType.GET){
        return ::getFunction
    }
    else{
        return ::postFunction
    }
}

fun getFunction(gameManager: GameManager, p2 : List<String>) : String{

    val function = p2[0]
    val parameter = p2[1]

    when(function){
        "PLAYER_INFO" -> return getPlayerInfo(gameManager, parameter)
        "PLAYERS_BY_SPECIE" -> return getPlayersBySpecie(gameManager, parameter)
        "MOST_TRAVELED" -> return getMostTraveled(gameManager, parameter)
        "TOP_ENERGETIC_OMNIVORES" -> return getTopEnergeticOmnivores(gameManager, parameter)
        "CONSUMED_FOODS" -> return getConsumedFood(gameManager, parameter)
    }
    return ""
}

fun postFunction(gameManager: GameManager, p2 : List<String>) : String{
    return postMove(gameManager, p2[1])
}

fun getPlayerInfo(manager: GameManager, name : String) : String{

    if(manager.getPlayerByName(name)==null){
        return "Inexistent player"
    }
    return manager.getPlayerByName(name)
            .kotlin_getPlayerInfo()
}

fun getPlayersBySpecie(manager: GameManager, specieId : String) : String{
    val id = specieId[0]

    if(!manager.isSpecieValid(id)){
        return ""
    }
    if(!manager.getPlayerBySpecieId(id)){
        return "";
    }
    return manager.getPlayers()
            .filter { it.specie.identifier == id }
            .map { it.name }
            .sortedWith{i, j -> j.compareTo(i)}
            .joinToString (",")
}

fun getMostTraveled(manager: GameManager, nothing : String) : String{
    if(manager.getPlayers().size==0){
        return ""
    }

    val total = manager.getPlayers()
            .map { it.distanceWalked }
            .sum()

    return manager.getPlayers()
            .sortedWith { i, j -> j.distanceWalked-i.distanceWalked }
            .map { it.kotlin_getMostTraveled() }
            .joinToString("\n") + "\n" +"Total:"+total
}

fun getTopEnergeticOmnivores(manager : GameManager, maxResults: String) : String{
    val num = maxResults.toInt()

    return manager.getPlayers()
            .filter { it.specie.type == 'o'}
            .sortedWith{i , j -> j.energy - i.energy}
            .map{it.kotlin_getTopEnergeticOmnivores()}
            .take(num)
            .joinToString("\n")

}

fun getConsumedFood(manager: GameManager, nothing : String) : String{
    if(manager.getMap().map.size ==0){
        return ""
    }

    return manager.getMap().getMap()
            .filter { it.food != null }
            .map { it.food }
            .filter { it.timesEaten >= 1 }
            .distinct()
            .map { it.name }
            .sortedWith{i, j -> i.compareTo(j)}
            .joinToString ("\n")
}

fun postMove(manager: GameManager, number : String) : String{
    val num = number.toInt()

    var bypass = false
    if(num<-6||num>6){
        bypass=true
    }

    val move = manager.moveCurrentPlayer(num, bypass)
    if(move.code==MovementResultCode.CAUGHT_FOOD){
        return "POST MOVE $number\nApanhou comida"
    }
    if(move.code==MovementResultCode.INVALID_MOVEMENT){
        return "POST MOVE $number\nMovimento invalido"
    }
    if(move.code==MovementResultCode.NO_ENERGY){
        return "POST MOVE $number\nSem energia"
    }
    return "POST MOVE $number\nOK"
}




