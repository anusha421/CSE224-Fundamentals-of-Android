package com.littlelemon.menu

sealed class FilterType {
    object All : FilterType()
    object Pant : FilterType()
    object Shirt : FilterType()
    object Accessories : FilterType()
}
