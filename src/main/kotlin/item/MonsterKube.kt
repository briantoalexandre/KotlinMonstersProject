package org.example.item

import org.example.dresseur.Entraineur
import org.example.joueur
import org.example.monstre.IndividuMonstre
import kotlin.random.Random

//val magicFormula = {x:Double -> ((x-1.0)/(0.0-1.0))*(1.5-0.5)}

class MonsterKube(id: Int, nom: String, description: String, var chanceCapture: Double): Item(id, nom, description), Utilisable {
    override fun utiliser(cible: IndividuMonstre): Boolean {
        println("Vous lancez la poké- le Monster Kube!")
        if (cible.entraineur != null) {
            println("Le monstre ne peut être capturé.")
        }
        var rationVie = cible.pv / cible.pvMax
        var chanceEffective = chanceCapture * (1.5 - rationVie)
        chanceEffective = chanceCapture.coerceAtLeast(5.0)
        var nbAleatoire = Random.nextInt(0, 101)
        if (nbAleatoire < chanceEffective) {
            println("Le monstre est capturé !")
            cible.renommer()
            if (joueur.equipeMonstre.size >= 6) {
                joueur.boiteMonstre.add(cible)
            } else {
                joueur.equipeMonstre.add(cible)
            }
            cible.entraineur = joueur
        } else {
            println("Presque ! Le Kube n'a pas pu capturer le monstre!")
        }
        return true
    }


}