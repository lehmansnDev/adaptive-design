package de.lehmansn.adaptivedesign.data

import de.lehmansn.adaptivedesign.model.Category
import de.lehmansn.adaptivedesign.model.Product
import de.lehmansn.adaptivedesign.model.Rating
import de.lehmansn.adaptivedesign.network.RemoteProduct
import de.lehmansn.adaptivedesign.network.RemoteRating

class ProductMapper {

    operator fun invoke(remote: RemoteProduct): Product = with(remote) {
        Product(
            id = id,
            title = title,
            price = price,
            description = description,
            category = toCategory(category),
            image = image,
            rating = toRating(rating)
        )
    }

    private fun toCategory(remote: String): Category =
        Category.entries.find { it.name == remote } ?: Category.UNKNOWN


    private fun toRating(remote: RemoteRating): Rating = with(remote) {
        Rating(rate, count)
    }
}