package org.example.monstre

import java.io.File

/**
 * Représente une créature avec ses caractéristiques, ses statistiques
 * de combat et ses particularités.
 *
 * @param id Identifiant unique de la créature.
 * @param nom Nom de la créature.
 * @param type Type ou catégorie de la créature.
 * @param baseAttaque Valeur de base de l'attaque physique.
 * @param baseDefense Valeur de base de la défense physique.
 * @param baseVitesse Valeur de base de la vitesse.
 * @param baseAttaqueSpe Valeur de base de l'attaque spéciale.
 * @param baseDefenseSpe Valeur de base de la défense spéciale.
 * @param basePv Nombre de points de vie de base.
 * @param modAttaque Modificateur appliqué à l'attaque physique.
 * @param modDefense Modificateur appliqué à la défense physique.
 * @param modVitesse Modificateur appliqué à la vitesse.
 * @param modAttaqueSpe Modificateur appliqué à l'attaque spéciale.
 * @param modDefenseSpe Modificateur appliqué à la défense spéciale.
 * @param modPv Modificateur appliqué aux points de vie.
 * @param description Description générale de la créature.
 * @param particularites Particularités ou capacités spécifiques de la créature.
 * @param caracteres Caractères ou traits distinctifs de la créature.
 */

class EspeceMonstre(var id : Int,var nom: String,var type: String,val baseAttaque: Int,val baseDefense: Int,val baseVitesse: Int,val baseAttaqueSpe: Int,val baseDefenseSpe: Int,val basePv: Int,val modAttaque: Double,val modDefense: Double,val modVitesse: Double,val modAttaqueSpe: Double,val modDefenseSpe: Double,val modPv: Double,val description: String = "",val particularites: String = "",val caracteres: String = "") {
    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */
    fun afficheArt(deFace: Boolean=true): String{
        val nomFichier = if(deFace) "front" else "back";
        val art =  File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}
