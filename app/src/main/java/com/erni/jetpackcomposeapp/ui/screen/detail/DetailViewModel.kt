package com.erni.jetpackcomposeapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erni.jetpackcomposeapp.data.ProductRepository
import com.erni.jetpackcomposeapp.ui.model.OrderProduct
import com.erni.jetpackcomposeapp.ui.model.Product
import com.erni.jetpackcomposeapp.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderProduct>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderProduct>>
        get() = _uiState

    fun getProductById(productId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderProductById(productId))
        }
    }

    fun addToCart(product: Product, count: Int) {
        viewModelScope.launch {
            repository.updateOrderProduct(product.id, count)
        }
    }
}