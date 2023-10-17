package com.clothingmenu.menu

class FilterHelper {

    fun filterProducts(type: FilterType, productsList: List<ProductItem>): List<ProductItem> {
        return when (type) {
            FilterType.All -> productsList
            FilterType.Pant -> productsList.filter { it.category=="Pant" }
            FilterType.Shirt -> productsList.filter { it.category=="Shirt" }
            FilterType.Accessories -> productsList.filter { it.category=="Accessories" }
        }
    }

}