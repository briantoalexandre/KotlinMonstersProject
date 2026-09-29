package org.example

import org.example.dresseur.Entraineur
import org.example.item.MonsterKube
import org.example.jeu.Partie
import org.example.monde.Zone
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre

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


var joueur = Entraineur(1, "Sacha", 100)
var rival = Entraineur(2,"Regis",200)

var especeSpringleaf = EspeceMonstre(1, "Springleaf", "Graine", 9, 11, 10, 12, 14, 34, 6.5, 9.0, 8.0, 7.0, 10.0, 60.0, )
var especeFlamkip = EspeceMonstre(4, "Flamkip", "Animal", 12, 8, 13, 16, 7, 22, 10.0, 5.5, 9.5, 9.5, 6.5, 50.0, )
var especeAquamy = EspeceMonstre(7, "Aquamy", "Meteo", 10, 11, 9, 14, 14, 27, 9.0, 10.0, 7.5, 12.0, 12.0, 55.0, )
var especeLaoumi = EspeceMonstre(8, "Laoumi", "Animal", 11, 10, 9, 8, 11, 23, 11.0, 8.0, 7.0, 6.0, 11.5, 58.0, )
var especeBugsyface = EspeceMonstre(10, "Bugsyface", "Insecte", 10, 13, 8, 7, 13, 21, 7.0, 11.0, 6.5, 8.0, 11.5, 45.0, )
var especeGalum = EspeceMonstre(13, "Galum", "Minéral", 12, 15, 6, 8, 12, 13, 9.0, 13.0, 4.0, 6.5, 10.5, 55.0, )

var m1 = IndividuMonstre(1, "nomDeMonstreOriginal1", especeSpringleaf, null, 0.0)
var m2 = IndividuMonstre(1, "flamkip", especeSpringleaf, null, 0.0)
var m3 = IndividuMonstre(1, "...", especeSpringleaf, null, 0.0)

var pokeball = MonsterKube(1, "Pokeball", "oui", 50.0)

var route1 = Zone(1, "", 50, mutableListOf(), null, null)
var partie1 = Partie(1, joueur, route1)

fun main() {

}