package org.example.jeu

import org.example.dresseur.Entraineur
import org.example.especeAquamy
import org.example.especeFlamkip
import org.example.especeSpringleaf
import org.example.monde.Zone
import org.example.monstre.IndividuMonstre

class Partie(var id: Int, var joueur: Entraineur, var zone: Zone) {
    /**
     * Choisie un Pok- monstre et l'ajoute dans l'equipe du [joueur]
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
        if (joueur.equipeMonstre.size > 1) {
            joueur.equipeMonstre.forEachIndexed { index, monstre ->
                println("${monstre.nom} ($index)")
            }

            println("Pok- Monstre à échanger")
            var choix1 = readln().toIntOrNull()?.coerceIn(0, joueur.equipeMonstre.size-1) ?: 0
            println("avec")
            var choix2: Int
            do {
                choix2 = readln().toIntOrNull()?.coerceIn(0, joueur.equipeMonstre.size-1) ?: 1
            } while (choix1 == choix2)
            joueur.equipeMonstre.let {
                // https://stackoverflow.com/questions/69936845/how-to-swap-elements-in-mutablelist-in-kotlin
                val tmp = it[choix1]
                it[choix1] = it[choix2]
                it[choix2] = tmp
            }
        }
    }

    /**
     * Permet d'examiner l'equipe, montre les [Entraineur.equipeMonstre] du joueur
     * puis offre le choix d'afficher les stats d'un [IndividuMonstre]
     */
    fun examineEquipe() {
        if (joueur.equipeMonstre.size > 0) {
            joueur.equipeMonstre.forEachIndexed { index, monstre ->
                println("${monstre.nom} ($index)")
            }
            var choix1 = readln().toIntOrNull()?.coerceIn(0, joueur.equipeMonstre.size-1) ?: 0
            var monstreAExaminer = joueur.equipeMonstre.get(choix1)
            monstreAExaminer.afficherDetail()
        } else {
            println("Pas de monstre dans l'equipe")
        }
    }

    /**
     * Offre un choix au joueur
     */
    fun jouer() {
        when (readln().toIntOrNull()?.coerceIn(1, 4)) {
            1 -> this.zone.genererMonstre()
            2 -> this.examineEquipe()
            3 -> {
                if (this.zone.zoneSuivante != null) { this.zone = zone.zoneSuivante!! }
            }
            4 -> {
                if (this.zone.zonePrecedante != null) { this.zone = zone.zonePrecedante!! }
            }
            else -> this.jouer()
        }
    }
}