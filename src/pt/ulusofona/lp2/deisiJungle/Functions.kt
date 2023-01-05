package pt.ulusofona.lp2.deisiJungle

enum class CommandType {GET, POST}

fun getPlayerInfo(manager: GameManager, name : String) : String{

    if(manager.getPlayerByName(name)==null){
        return "Inexistent player"
    }
    return manager.getPlayerByName(name)
            .kotlin_getPlayerInfo()
}

fun getPlayersBySpecie(manager: GameManager, specieId : Char) : String{

    if(!manager.isSpecieValid(specieId)){
        return ""
    }
    if(manager.getPlayerBySpecieId(specieId).size==0){
        return "";
    }
    return manager.getPlayerBySpecieId(specieId)
            .map { it.name }
            .joinToString (",")
}

fun getMostTraveled(manager: GameManager) : String{
    val total = manager.getPlayers()
            .map { it.distanceWalked }
            .sum()

    return manager.getPlayers()
            .sortedWith { i, j -> j.distanceWalked-i.distanceWalked }
            .map { it.kotlin_getMostTraveled() }
            .joinToString("\n") + "\n" +"Total:"+total
}

fun getTopEnergeticOmnivores(manager : GameManager, maxResults: Int) : String{
    return manager.getPlayers()
            .filter { it.specie.type == 'o'}
            .sortedWith{i , j -> j.energy - i.energy}
            .take(maxResults)
            .map{it.kotlin_getTopEnergeticOmnivores()}
            .joinToString("\n")

}

fun getConsumedFood(manager: GameManager) : String{
    return manager.getMap().getMap()
            .filter { it.food != null }
            .map { it.food }
            .filter { it.timesEaten >= 1 }
            .distinct()
            .map { it.name }
            .sortedWith{i, j -> i.compareTo(j)}
            .joinToString ("\n")
}




