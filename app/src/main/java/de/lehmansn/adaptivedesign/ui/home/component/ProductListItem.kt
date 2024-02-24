package de.lehmansn.adaptivedesign.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import de.lehmansn.adaptivedesign.model.Product
import de.lehmansn.adaptivedesign.model.ShoppingList
import de.lehmansn.adaptivedesign.ui.home.ProductListItemActions

@Composable
fun ProductListItem(
    product: Product,
    shoppingList: ShoppingList,
    actions: ProductListItemActions
) {
    val alreadyInShoppingList =
        shoppingList.open.contains(product) || shoppingList.closed.contains(product)

    Surface(
        shape = RoundedCornerShape(24.dp),
        onClick = { actions.onClick(product) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Surface(
                modifier = Modifier.size(96.dp),
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(16.dp)
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "%.2f €".format(product.price),
                    style = MaterialTheme.typography.titleSmall
                )
            }

            OutlinedButton(
                onClick = { actions.onAddClick(product) },
                modifier = Modifier.size(48.dp),
                enabled = !alreadyInShoppingList,
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(12.dp),
                colors = if (alreadyInShoppingList) {
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                } else {
                    ButtonDefaults.outlinedButtonColors()
                }
            ) {
                Icon(
                    imageVector = if (alreadyInShoppingList) {
                        Icons.Rounded.Check
                    } else {
                        Icons.Rounded.Add
                    },
                    contentDescription = null
                )
            }
        }
    }
}