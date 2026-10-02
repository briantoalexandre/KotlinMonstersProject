package org.example.monstre

class PalierEvolution(var id: Int, var niveauRequis: Int, var evolution: EspeceMonstre? = null) {
    fun peutEvoluer(monstre: IndividuMonstre): Boolean = if (monstre.niveau >= this.niveauRequis) true else false
}