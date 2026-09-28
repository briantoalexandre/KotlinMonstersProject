package org.example.item

import org.example.dresseur.Entraineur

/**
 * Représente un badge, qui est un type d'[Item].
 *
 * @param id Identifiant unique du badge.
 * @param nom Nom du badge.
 * @param description Description du badge.
 */
class Badge(id: Int, nom: String, description: String, var champion: Entraineur): Item(id, nom, description) {
}