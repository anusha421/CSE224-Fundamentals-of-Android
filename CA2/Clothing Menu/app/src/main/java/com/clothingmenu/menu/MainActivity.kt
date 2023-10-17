package com.clothingmenu.menu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.MenuCompat
import com.littlelemon.menu.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainActivity : ComponentActivity() {
    private val productsList = mutableListOf(
        ProductItem("Cargo Pant", 33.00, "Pant", R.drawable.cargo_pant),
        ProductItem("Grey Trouser", 43.00, "Pant", R.drawable.grey_trouser),
        ProductItem("Khaki Pant", 90.00, "Pant", R.drawable.khaki_pant),
        ProductItem("Scarf", 81.00, "Accessories", R.drawable.scarf),
        ProductItem("Shirt", 34.00, "Shirt", R.drawable.shirt),
        ProductItem("T Shirt", 10.00, "Shirt", R.drawable.t_shirt),
    )

    private val productsState: MutableStateFlow<Products> =
        MutableStateFlow(Products(productsList))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { InitUI() }
    }

    @Composable
    fun InitUI() {
        val products by productsState.collectAsState()
        ProductsGrid(products = products)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.products_menu, menu)
        MenuCompat.setGroupDividerEnabled(menu, true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.groupId == R.id.filter) {
            val type = when (item.itemId) {
                R.id.filter_all -> FilterType.All
                R.id.filter_pant -> FilterType.Pant
                R.id.filter_shirt -> FilterType.Shirt
                R.id.filter_accessories -> FilterType.Accessories
                else -> FilterType.All
            }
            productsState.update {
                Products(
                    FilterHelper().filterProducts(
                        type,
                        productsList
                    )
                )
            }
        }
        return true
    }
}