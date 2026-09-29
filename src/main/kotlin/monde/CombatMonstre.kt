package org.example.monde

import org.example.item.Utilisable
import org.example.joueur
import org.example.monstre.IndividuMonstre


/**
 * Représente un combat entre un monstre contrôlé par un joueur
 * et un monstre sauvage.
 *
 * @property monstreJoueur Monstre appartenant au joueur.
 * @property monstreSauvage Monstre sauvage affronté par le joueur.
 */
class CombatMonstre(var monstreJoueur: IndividuMonstre, var monstreSauvage: IndividuMonstre) {
    /**
     * Numéro du round actuel du combat.
     */
    private var round = 1;

    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */
    fun gameOver(): Boolean {
        //none() https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/none.html
        return (joueur.equipeMonstre.none { it.pv > 0})
    }

    fun joueurGagne(): Boolean {
        if (this.monstreSauvage.pv <= 0) {
            println("[${joueur.nom}] a gagné !")
            var gainExp = this.monstreSauvage.exp * 0.20
            monstreJoueur.exp += gainExp
            println("[${monstreJoueur.nom}] gagne [${gainExp}] exp")
            return true
        } else if (monstreSauvage.entraineur == joueur) {
            println("[${monstreSauvage.nom}] a été capturé !")
            return true
        } else {
            return false
        }
    }
    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) {
            monstreSauvage.attaquer(monstreJoueur)
        }
    }

    fun actionJoueur(): Boolean {
        if (gameOver()) {
            return false
        }
        println("attaquer (1)\nutiliser un item (2)\nchanger de monstre (3)")
        when (readlnOrNull()?.toIntOrNull()) {
            1 -> monstreJoueur.attaquer(monstreSauvage)
            2 -> {
                joueur.sacAItems.forEachIndexed { index, item ->
                    println("$item ($index)")
                }
                var indexChoix = readln().toIntOrNull()
                if (indexChoix != null) {
                    var objetChoisi = joueur.sacAItems[indexChoix % joueur.sacAItems.size]
                    if (objetChoisi is Utilisable) {
                        var captureReussie = objetChoisi.utiliser(monstreSauvage)
                        if (captureReussie) {
                            return false
                        }
                    } else {
                        println("Objet non utilisable")
                    }
                } else {
                    println("Objet pas trouvé")
                }
            }
            3 -> {
                joueur.equipeMonstre.forEachIndexed { index, monstre ->
                    if (monstre.pv > 0) println("$monstre ($index)")
                }

                var indexChoix = readln().toIntOrNull()
                if (indexChoix != null) {
                    var choixMonstre = joueur.equipeMonstre[indexChoix % joueur.equipeMonstre.size]
                    if (choixMonstre.pv <= 0) {
                        println("Impossible ! Ce monstre est Ko")
                    } else {
                        println("[${choixMonstre}] remplace [${monstreJoueur}]")
                        monstreJoueur = choixMonstre
                    }

                } else {
                    println("monstre pas trouvé")
                }
            }
            else -> {}
        }
        return true

    }

    fun afficheCombat() {
        println("== Début Round: $round ==")
        println("Niveau : ${monstreSauvage.niveau}")
        println("PV : ${monstreSauvage.pv}/${monstreSauvage.pvMax}")
        println(monstreSauvage.especeMonstre.afficheArt())
        println(monstreJoueur.especeMonstre.afficheArt(false))
        println("Niveau : ${monstreJoueur.niveau}")
        println("PV: ${monstreJoueur.pv}/${monstreJoueur.pvMax}")
    }

    fun jouer() {
        val joueurPlusRapide = (monstreJoueur.vitesse >= monstreSauvage.vitesse)
        afficheCombat()
        var continuer: Boolean
        if (joueurPlusRapide) {
            continuer = actionJoueur()
            if (continuer == false) {
                return
            }
            actionAdversaire()
        } else {
            actionAdversaire()
            if (gameOver() == false) {
                continuer = actionJoueur()
                if (continuer == false) {
                    return
                }
            }
        }

        /**
         * Lance le combat et gère les rounds jusqu'à la victoire ou la défaite.
         *
         * Affiche un message de fin si le joueur perd et restaure les PV
         * de tous ses monstres.
         */
        fun lanceCombat() {
            while (!gameOver() && !joueurGagne()) {
                this.jouer()
                println("======== Fin du Round : $round ========")
                round++
            }
            if (gameOver()) {
                joueur.equipeMonstre.forEach { it.pv = it.pvMax }
                println("Game Over !")
            }
        }


    }

}