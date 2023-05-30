package com.erni.jetpackcomposeapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erni.jetpackcomposeapp.data.ProductRepository
import com.erni.jetpackcomposeapp.ui.model.OrderProduct
import com.erni.jetpackcomposeapp.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderProduct>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderProduct>>>
        get() = _uiState

    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllProduct()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}