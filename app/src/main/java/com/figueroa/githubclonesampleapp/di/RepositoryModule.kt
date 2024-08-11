package com.figueroa.githubclonesampleapp.di

import com.figueroa.githubclonesampleapp.repository.GitHubRepositoryRepository
import org.koin.dsl.module

object RepositoryModule {

    val repositoryModule = module {
        factory { GitHubRepositoryRepository(get()) }
    }
}