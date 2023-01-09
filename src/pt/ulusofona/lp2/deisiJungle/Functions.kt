package pt.ulusofona.lp2.deisiJungle

enum class CommandType {GET, POST}


fun router() : (CommandType) -> Function2<GameManager,List<String>,String?>{
    return ::callFunction
}

fun callFunction(comand : CommandType) : Function2<GameManager,List<String>,String?>{
    if(comand==CommandType.GET){
        return ::getFunction
    }
    else{
        return ::postFunction
    }
}

fun getFunction(gameManager: GameManager, p2 : List<String>) : String? {

    when(p2[0]){
        "PLAYER_INFO" -> return getPlayerInfo(gameManager, p2[1])
        "PLAYERS_BY_SPECIE" -> return getPlayersBySpecie(gameManager, p2[1])
        "MOST_TRAVELED" -> return getMostTraveled(gameManager, "")
        "TOP_ENERGETIC_OMNIVORES" -> return getTopEnergeticOmnivores(gameManager, p2[1])
        "CONSUMED_FOODS" -> return getConsumedFood(gameManager, "")
    }
    return null
}

fun postFunction(gameManager: GameManager, p2 : List<String>) : String? {
    if(p2[0] == "MOVE"){
        return postMove(gameManager, p2[1])
    }
    return null
}

fun getPlayerInfo(manager: GameManager, name : String) : String{
    val player = manager.getPlayers().filter { it.name.equals(name) }

    if(player.isEmpty()){
        return "Inexistent player"
    }
    return player[0].kotlin_getPlayerInfo()
}

fun getPlayersBySpecie(manager: GameManager, specieId : String) : String{
    if(specieId==""){
        return ""
    }
    val id = specieId[0]
    val players = manager.getPlayers().filter { it.specie.identifier == id }

    if(players.isEmpty()){
        return ""
    }

    return players
            .map { it.name }
            .sortedWith{i, j -> j.compareTo(i)}
            .joinToString (",")
}

fun getMostTraveled(manager: GameManager, nothing : String) : String{
    if(manager.getMap().getMap().size ==0){
        return ""
    }

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
    val foods = manager.getMap().getMap()
            .filter { it.food != null }
            .map { it.food }
            .filter { it.timesEaten >= 1 }

    if(foods.isEmpty()){
        return ""
    }

    return foods
            .distinct()
            .map { it.name }
            .sortedWith{i, j -> i.compareTo(j)}
            .joinToString ("\n")
}

fun postMove(manager: GameManager, number : String) : String{
    val num = number.toInt()

    val move = manager.moveCurrentPlayer(num, true)
    if(move.code==MovementResultCode.CAUGHT_FOOD){
        return "Apanhou comida"
    }
    if(move.code==MovementResultCode.INVALID_MOVEMENT){
        return "Movimento invalido"
    }
    if(move.code==MovementResultCode.NO_ENERGY){
        return "Sem energia"
    }
    return "OK"
}




