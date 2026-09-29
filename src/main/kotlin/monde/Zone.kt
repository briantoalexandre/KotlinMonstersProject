package org.example.monde

import org.example.jeu.CombatMonstre
import org.example.joueur
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre
import kotlin.random.Random


/**
 * Représente une zone du jeu contenant des espèces de monstres
 * et pouvant être reliée à d'autres zones.
 *
 * @property id Identifiant unique de la zone.
 * @property nom Nom de la zone.
 * @property expZone Quantité d'expérience associée à la zone.
 * @property especeMonstres Liste des espèces de monstres présentes dans la zone.
 * @property zoneSuivante Zone accessible après la zone actuelle, si elle existe.
 * @property zonePrecedante Zone accessible avant la zone actuelle, si elle existe.
 */

class Zone(var id : Int,var nom: String, var expZone: Int, var especeMonstres: MutableList<EspeceMonstre>, var zoneSuivante: Zone?, var zonePrecedante: Zone?) {

    fun genererMonstre(): IndividuMonstre {
        var expAleatoire = this.expZone * when (Random.nextInt(0, 3)) {1 -> 0.8; 2 -> 1.0; else -> 1.2}
        var especeMonstre: EspeceMonstre = especeMonstres.random() // especeMonstres[Random.nextInt(0,especeMonstres.size)]
        var individuMonstre = IndividuMonstre(1, especeMonstre.nom, especeMonstre, null, expAleatoire)
        return individuMonstre

    }

    fun recontreMonstre() {
        val monstreSauvage = this.genererMonstre()
        val premierMonstre = joueur.equipeMonstre.let { it.getOrElse(it.map { monstre -> monstre.pv > 0 }.indexOf(true), { _ -> it.last() }) }
        val combatMonstre = CombatMonstre(premierMonstre, monstreSauvage)
        //TODO A verifier
        //combatMonstre.lanceCombat()
    }
}