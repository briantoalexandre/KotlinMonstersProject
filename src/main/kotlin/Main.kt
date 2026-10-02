package org.example

import org.example.dresseur.Entraineur
import org.example.item.MonsterKube
import org.example.jeu.CombatMonstre
import org.example.jeu.Partie
import org.example.monde.Zone
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre
import org.example.monstre.PalierEvolution

/**
 * Change la couleur du message donné selon le nom de la couleur spécifié.
 * Cette fonction utilise les codes d'échappement ANSI pour appliquer une couleur à la sortie console. Si un nom de couleur
 * non reconnu ou une chaîne vide est fourni, aucune couleur n'est appliquée.
 *
 * @param message Le message auquel la couleur sera appliquée.
 * @param couleur Le nom de la couleur à appliquer (ex : "rouge", "vert", "bleu"). Par défaut, c'est une chaîne vide, ce qui n'applique aucune couleur.
 * @return Le message coloré sous forme de chaîne, ou le même message si aucune couleur n'est appliquée.
 */
fun changeCouleur(message: String, couleur:String=""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        else -> "" // pas de couleur si non reconnue
    }
    return "$codeCouleur$message$reset"
}

/**
 * Permet de démarrer une nouvelle partie, nomme le joueur, ...
 */
fun nouvellePartie(): Partie {
    println("Bienvenue sur KotlinMonsters.\n")
    println("veuillez entre vôtre nom.")
    var nomJoueur: String
    do {
        println("nom : ")
        nomJoueur = readln().trim()
    } while (nomJoueur.isBlank()) //https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/is-blank.html
    rival.nom = nomJoueur
    var nomRival: String
    do {
        println("nom : ")
        nomRival = readln().trim()
    } while (nomRival.isBlank())
    rival.nom = nomRival
    return Partie(1, joueur, route1)
}

/**
 * Déclaration du joueur principal
 */
var joueur = Entraineur(1, "Sacha", 100)
var rival = Entraineur(2,"Rival",500)

/**
 * Définition des espèces de monstres disponible dans le jeu.
 */
val palierEvolutionPyrokip = EspeceMonstre(5, "pyrokip", "Animal", 18, 12, 15, 22, 11, 70, 12.0, 8.0, 11.0, 12.5, 8.0, 15.0,)
var especeSpringleaf = EspeceMonstre(1, "Springleaf", "Graine", 9, 11, 10, 12, 14, 34, 6.5, 9.0, 8.0, 7.0, 10.0, 60.0,)
var especeFlamkip = EspeceMonstre(4, "Flamkip", "Animal", 12, 8, 13, 16, 7, 22, 10.0, 5.5, 9.5, 9.5, 6.5, 50.0, )
var especeAquamy = EspeceMonstre(7, "Aquamy", "Meteo", 10, 11, 9, 14, 14, 27, 9.0, 10.0, 7.5, 12.0, 12.0, 55.0, )
var especeLaoumi = EspeceMonstre(8, "Laoumi", "Animal", 11, 10, 9, 8, 11, 23, 11.0, 8.0, 7.0, 6.0, 11.5, 58.0, )
var especeBugsyface = EspeceMonstre(10, "Bugsyface", "Insecte", 10, 13, 8, 7, 13, 21, 7.0, 11.0, 6.5, 8.0, 11.5, 45.0, )
var especeGalum = EspeceMonstre(13, "Galum", "Minéral", 12, 15, 6, 8, 12, 13, 9.0, 13.0, 4.0, 6.5, 10.5, 55.0, )
//var especeDragon = EspeceMonstre(5, "G", "Dragon", 1500, 1500, 3000, 1500, 1500, 24000, 30.0, 30.0, 30.0, 30.0, 30.0, 50.0)

/** evolutions */


//var m1 = IndividuMonstre(1, "111", especeSpringleaf, null, 0.0)
var m2 = IndividuMonstre(1, "flamkip", especeFlamkip, null, 0.0)
//var m3 = IndividuMonstre(1, "...", especeGalum, null, 0.0)
//var gran = IndividuMonstre(1, "G????????", especeDragon, null, 0.0)

/**
 * Definition des zones de jeu.
 */
var route1 = Zone(1, "Route 1", 50, mutableListOf(especeBugsyface, especeLaoumi))
var route2 = Zone(1, "Route 2", 50, mutableListOf(especeFlamkip, especeGalum))
//var partie1 = Partie(1, joueur, route1)

/**
 * Definition des items.
 */
var pokeball = MonsterKube(1, "Pokeball©", "Objet qui permet d'attraper des Po- Monstres!", 50.0)


fun main() {
    especeFlamkip.palierEvolution = PalierEvolution(0, 7, palierEvolutionPyrokip)
    while (m2.niveau < 8) {
        m2.exp += 500
    }
//    println(especeDragon.afficheArt(true))
//    route1.zoneSuivante = route2
//    route2.zonePrecedante = route1
//    joueur.sacAItems.add(pokeball)
//    val partie = nouvellePartie()
//    partie.choixStarter()
//    partie.jouer()

}
//https://gist.github.com/lunatic-fox/1b7161ef47d96a3d0d73ce8d5181a279