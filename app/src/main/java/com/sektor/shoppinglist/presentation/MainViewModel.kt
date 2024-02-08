package com.sektor.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sektor.shoppinglist.domain.DeleteShopItemUseCase
import com.sektor.shoppinglist.domain.EditShopItemUseCase
import com.sektor.shoppinglist.domain.GetShopListUseCase
import com.sektor.shoppinglist.domain.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase
) : ViewModel() {
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