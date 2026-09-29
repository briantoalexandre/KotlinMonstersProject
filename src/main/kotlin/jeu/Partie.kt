package org.example.jeu

import org.example.dresseur.Entraineur
import org.example.especeAquamy
import org.example.especeFlamkip
import org.example.especeSpringleaf
import org.example.monde.Zone
import org.example.monstre.IndividuMonstre

class Partie(var id: Int, var joueur: Entraineur, var zone: Zone) {
    /**
     * Choisie
     */
    fun choixStarter() {
        val monstre1 = IndividuMonstre(1, especeSpringleaf.nom, especeSpringleaf, null, 0.0)
        val monstre2 = IndividuMonstre(1, especeFlamkip.nom, especeFlamkip, null, 0.0)
        val monstre3 = IndividuMonstre(1, especeAquamy.nom, especeAquamy, null, 0.0)
        val monstres: List<IndividuMonstre> = listOf(monstre1, monstre2, monstre3)
        val taille: Int = monstres.size

        monstres.forEach { monstre ->
            monstre.afficherDetail()
        }
        print("choix : ")
        var choix: Int = readlnOrNull()?.toIntOrNull() ?: 0
        var starter: IndividuMonstre = monstres.let {  it.getOrElse(choix, {_ -> it.first() }) }
        starter.renommer()
        joueur.equipeMonstre.add(starter)
        starter.entraineur = joueur
    }

    /**
     * Permet de modifier l'odre de l'équipe du joueur
     */
    fun modifierOrdreEquipe() {
        if (joueur.boiteMonstre.size > 1) {
            joueur.boiteMonstre.forEachIndexed { index, monstre ->
                println("${monstre.nom} ($index)")
            }

            println("Pok- Monstre à échanger")
            var choix1 = readln().toIntOrNull() ?: 0
            println("avec")
            var choix2 = readln().toIntOrNull()?.coerceAtLeast(1) ?: 1

            joueur.boiteMonstre.let { it1 -> with(it1.get(choix1)) { it1.remove(this@with) } }
        }
    }
}