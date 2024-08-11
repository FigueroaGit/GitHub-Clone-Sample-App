package com.figueroa.githubclonesampleapp.di

import com.figueroa.githubclonesampleapp.screens.details.GitHubRepositoryDetailsViewModel
import com.figueroa.githubclonesampleapp.screens.list.GitHubRepositoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {

    val viewModelModule = module {
        viewModel { GitHubRepositoryListViewModel(get()) }
        viewModel { GitHubRepositoryDetailsViewModel(get()) }
    }
}