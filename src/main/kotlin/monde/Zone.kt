package org.example.monde

import org.example.monstre.EspeceMonstre


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

    //TODO faire la méthode genereMonstre()
    //TODO faire la méthode rencontreMonstre()
}