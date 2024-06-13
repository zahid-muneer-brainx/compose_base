package com.brainxtech.todolist.di

import com.brainxtech.todolist.presentation.viewmodels.BasicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val viewModelModule = module {
    viewModel{
        BasicViewModel(
            get(named(CoroutineDispatcherModuleEnums.IO.dispatcherName)),
        )
    }
}