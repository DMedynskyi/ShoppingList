package com.sektor.shoppinglist.di

import android.app.Application
import com.sektor.shoppinglist.data.AppDatabase
import com.sektor.shoppinglist.data.ShopListDao
import com.sektor.shoppinglist.data.ShopListRepositoryImpl
import com.sektor.shoppinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideShopListDao(
            application: Application
        ): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}