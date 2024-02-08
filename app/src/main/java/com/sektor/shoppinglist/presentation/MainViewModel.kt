package com.sektor.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sektor.shoppinglist.data.ShopListRepositoryImpl
import com.sektor.shoppinglist.domain.DeleteShopItemUseCase
import com.sektor.shoppinglist.domain.EditShopItemUseCase
import com.sektor.shoppinglist.domain.GetShopListUseCase
import com.sektor.shoppinglist.domain.ShopItem
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    //private val scope = CoroutineScope(Dispatchers.Main)

    var shopListLD = getShopListUseCase.getShopList()

    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }

    fun deleteShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

// Need to cancel scope when create your own
//    override fun onCleared() {
//        super.onCleared()
//        scope.cancel()
//    }
}