package com.sektor.shoppinglist.presentation

import android.app.Application
import com.sektor.shoppinglist.di.DaggerApplicationComponent

//Add ShopApp to manifest
class ShopApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}