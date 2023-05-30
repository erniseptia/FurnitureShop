package com.erni.jetpackcomposeapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.erni.jetpackcomposeapp.ViewModelFactory
import com.erni.jetpackcomposeapp.di.Injection
import com.erni.jetpackcomposeapp.ui.components.ProductItem
import com.erni.jetpackcomposeapp.ui.model.OrderProduct
import com.erni.jetpackcomposeapp.ui.state.UiState


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllRewards()
            }
            is UiState.Success -> {
                HomeContent(
                    orderProduct = uiState.data,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderProduct: List<OrderProduct>,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        items(orderProduct) { data ->
            ProductItem(
                image = data.product.image,
                title = data.product.title,
                requiredPoint = data.product.requiredPrice,
                modifier = Modifier.clickable {
                    navigateToDetail(data.product.id)
                }
            )
        }
    }
}