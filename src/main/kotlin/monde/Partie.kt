package org.example.monde

import org.example.dresseur.Entraineur
import org.example.especeAquamy
import org.example.especeFlamkip
import org.example.especeSpringleaf
import org.example.monstre.IndividuMonstre

class Partie(var id: Int, var joueur: Entraineur, var zone: Zone) {
    fun choixStarter() {
        val monstre1 = IndividuMonstre(1, especeSpringleaf.nom, especeSpringleaf, null, 0.0)
        val monstre2 = IndividuMonstre(1, especeFlamkip.nom, especeSpringleaf, null, 0.0)
        val monstre3 = IndividuMonstre(1, especeAquamy.nom, especeSpringleaf, null, 0.0)
        val monstres: List<IndividuMonstre> = listOf(monstre1, monstre2, monstre3)
        val taille: Int = monstres.size

        monstres.forEach { monstre ->
            monstre.afficherDetail()
        }
        print("choix : ")
        var choix: Int = readlnOrNull()?.toIntOrNull() ?: 0
        var starter: IndividuMonstre = monstres.let {  it.getOrElse(choix, {_ -> it.last() }) }
        starter.renommer()
        joueur.equipeMonstre.add(starter)
        starter.entraineur = joueur
    }
}