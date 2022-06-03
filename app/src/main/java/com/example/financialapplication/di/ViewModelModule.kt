package com.example.financialapplication.di

import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.dagger.ViewModelKey
import com.example.financialapplication.ui.items.ItemsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ItemsViewModel::class)
    abstract fun bindItemsViewModel(itemsViewModel: ItemsViewModel): ViewModel

}