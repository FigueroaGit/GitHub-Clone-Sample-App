package com.figueroa.githubclonesampleapp

import android.app.Application
import com.figueroa.githubclonesampleapp.di.NetworkModule.networkModule
import com.figueroa.githubclonesampleapp.di.RepositoryModule.repositoryModule
import com.figueroa.githubclonesampleapp.di.ViewModelModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class GitHubCloneSampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GitHubCloneSampleApp)
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}