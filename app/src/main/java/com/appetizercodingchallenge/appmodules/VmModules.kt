package com.appetizercodingchallenge.appmodules

import com.appetizercodingchallenge.ui.items.ItemsViewModel
import com.appetizercodingchallenge.ui.saveditems.SavedItemsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel { SavedItemsViewModel(get(), get(), get()) }
    viewModel { ItemsViewModel(get(), get(), get()) }
}
