package org.example.item

/**
 * Représente un élément identifié par un identifiant, un nom et une description.
 *
 * @property id Identifiant unique de l'élément.
 * @property nom Nom de l'élément.
 * @property description Description détaillée de l'élément.
 */
open class Item(var id: Int, var nom: String, var description: String) {
}