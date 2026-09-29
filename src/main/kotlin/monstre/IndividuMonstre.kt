package org.example.monstre

import org.example.dresseur.Entraineur
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * Représente un individu d'une espèce de monstre, associé à un entraîneur.
 *
 * @property id Identifiant unique de l'individu.
 * @property nom Nom donné à l'individu du monstre.
 * @property especeMonstre Espèce à laquelle appartient le monstre.
 * @property entraineur Entraîneur auquel le monstre est associé.
 * @param expInit experience initale du monstre.
 */
class IndividuMonstre(var id: Int, var nom: String, var especeMonstre: EspeceMonstre, var entraineur: Entraineur?, expInit: Double) {
    var niveau: Int = 1
    var attaque: Int = especeMonstre.baseAttaque + (if (Random.nextInt(0, 2)==1) -2 else 2)
    var defense: Int = especeMonstre.baseDefense + (if (Random.nextInt(0, 2)==1) -2 else 2)
    var vitesse: Int = especeMonstre.baseVitesse + (if (Random.nextInt(0, 2)==1) -2 else 2)
    var attaqueSpe: Int = especeMonstre.baseAttaqueSpe + (if (Random.nextInt(0, 2)==1) -2 else 2)
    var defenseSpe: Int = especeMonstre.baseDefenseSpe + (if (Random.nextInt(0, 2)==1) -2 else 2)
    var pvMax : Int = especeMonstre.basePv + (if (Random.nextInt(0, 2)==1) -5 else 5)
    var potentiel: Double = (Random.nextInt(5, 21) / 10).toDouble()
    var exp: Double = 0.0
        //get() = field
        set(value) {
            field = value
            var estNiveau1: Boolean
            if (this.niveau == 1) {
                estNiveau1 = true
            }
            else {
                estNiveau1 = false
            }

            while (field >= this.palierExp(this.niveau)) {
                this.levelUp()
                if (estNiveau1 == false) {
                    println("Le monster ${this.nom} est maintenant niveau ${this.niveau}")
                }
            }
        }

    /**
     * @property pv Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax]
     */
    var pv: Int = pvMax
        //get() = field
        set(nouveauPv) {
            field = (if (nouveauPv < 0 ) 0 else if (nouveauPv > pvMax) pvMax else nouveauPv)
        }

    /**
     * Calcule l'expérience nécessaire pour atteindre un niveau donné.
     * @param niveau Niveau cible
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau: Int) = 100 * (this.niveau - 1).toDouble().pow(2)

    /**
     * Augmente le niveau du monstre et incrémente ses caractéristiques par de nouvelles valeurs.
     */
    fun levelUp() {
        this.niveau++
        this.attaque += (especeMonstre.modAttaque * potentiel).roundToInt() + if (Random.nextInt(0, 2)==0) -2 else 2
        this.defense += (especeMonstre.modDefense * potentiel).roundToInt() + if (Random.nextInt(0, 2)==0) -2 else 2
        this.vitesse += (especeMonstre.modVitesse * potentiel).roundToInt() + if (Random.nextInt(0, 2)==0) -2 else 2
        this.attaqueSpe += (especeMonstre.modAttaqueSpe * potentiel).roundToInt() + if (Random.nextInt(0, 2)==0) -2 else 2
        this.defenseSpe += (especeMonstre.modDefenseSpe * potentiel).roundToInt() + if (Random.nextInt(0, 2)==0) -2 else 2
        this.pvMax += (especeMonstre.modPv * potentiel).roundToInt() + if (Random.nextInt(0, 2)==0) -5 else 5
        this.pv = this.pvMax
    }
    /**
    * Attaque un autre [IndividuMonstre] et inflige des dégâts. Fait par Alexandre
    *
    * Les dégâts sont calculés de manière très simple pour le moment :
    * `dégâts = attaque - (défense / 2)` (minimum 1 dégât).
    *
    * @param cible Monstre cible de l'attaque.
    */
    fun attaquer(cible: IndividuMonstre) {
        var degatBrut: Int = this.attaque
        var degatTotal = degatBrut - (this.defense / 2)
        if (degatTotal < 1) {
            degatTotal = 1
        }
        var pvAvant = cible.pv
        cible.pv -= degatTotal
        var pvApres = cible.pv
        println("[${this.nom}] inflige ${pvAvant - pvApres} dégâts à [${cible.nom}]")
    }

    /**
     * Demande au joueur de renommer le monstre.
     * Si l'utilisateur entre un texte vide, le nom n'est pas
    modifié.
     */
    fun renommer() {
        println("Renommer [${this.nom}] ?")
        var saisie = readln()
        if (saisie.isNotEmpty()) {
            this.nom = saisie
        }
    }

    /**
     *
     */
    fun afficherDetail() {
        val art: String = this.especeMonstre.afficheArt()
        var artLines: List<String> = art.lines()
        var temp: Map<String, Any> = mapOf("" to "", "nom" to this.nom, "niveau" to this.niveau, "PV" to "${this.pv}/${this.pvMax}", "attaque" to this.attaque, "defense" to this.defense, "vitesse" to this.vitesse, "attaqueSpe" to this.attaqueSpe, "defenseSpe" to this.defenseSpe)
        var details: MutableList<String> = mutableListOf()

        temp.forEach { K, V ->
            details.add(if (K.isNotEmpty()) "$K = $V" else "")
        }

        val maxArtWidth: Int = artLines.toList().map { it.length } . max()
        var maxLines: Int = listOf(artLines.size, details.size).max()

        var artLine: String
        var detailLine: String
        for (i in 0..maxLines-1) {
            if (i < artLines.size) {
                artLine = artLines[i]
            } else {
                artLine = ""
            }
            if (i < details.size) {
                detailLine = details[i]
            } else {
                detailLine = ""
            }
            println(artLine.padEnd(maxArtWidth + 4) + detailLine)
            if (i < maxLines) {
                continue
            }
        }
    }

    init {
        this.exp = expInit
    }
}