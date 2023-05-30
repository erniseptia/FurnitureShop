package com.erni.jetpackcomposeapp.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erni.jetpackcomposeapp.data.ProductRepository
import com.erni.jetpackcomposeapp.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderProducts() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderProduct()
                .collect { orderProduct ->
                    val totalRequiredPoint =
                        orderProduct.sumOf { it.product.requiredPrice * it.count }
                    _uiState.value = UiState.Success(CartState(orderProduct, totalRequiredPoint))
                }
        }
    }

    fun updateOrderProduct(rewardId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderProduct(rewardId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderProducts()
                    }
                }
        }
    }
}