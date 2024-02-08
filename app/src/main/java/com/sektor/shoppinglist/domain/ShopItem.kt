package com.sektor.shoppinglist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    var enabled: Boolean,
    val id: Int = UNDEFINED_ID,
) {

    companion object {
        const val UNDEFINED_ID = 0
    }
}
