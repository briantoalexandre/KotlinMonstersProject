package org.example.item

import org.example.monstre.IndividuMonstre

/**
 * Interface définissant le comportement d'un objet ou d'une action
 *
 * Les classes qui implémentent cette interface doivent fournir
 * une implémentation de la méthode [utiliser].
 */
interface Utilisable {

    /**
     * Applique l'effet de l'objet ou de l'action sur le monstre cible.
     *
     * @param cible Le [IndividuMonstre] sur lequel l'objet est utilisé.
     * @return `true` si l'item est utilisable,
     *         `false` sinon.
     */
    fun utiliser(cible: IndividuMonstre): Boolean
}