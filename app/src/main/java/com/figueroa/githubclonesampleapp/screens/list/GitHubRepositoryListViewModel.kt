package com.figueroa.githubclonesampleapp.screens.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.figueroa.githubclonesampleapp.data.Resource
import com.figueroa.githubclonesampleapp.model.GitHubRepositoryInformation
import com.figueroa.githubclonesampleapp.repository.GitHubRepositoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GitHubRepositoryListViewModel(private val repository: GitHubRepositoryRepository) :
    ViewModel() {

    suspend fun getGitHubRepositories(page: Int): Resource<List<GitHubRepositoryInformation>> {
        return repository.getGitHubRepositories("Kotlin", 30, page)
    }
}