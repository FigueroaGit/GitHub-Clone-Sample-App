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

    var list: List<GitHubRepositoryInformation> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)

    init {
        loadGitHubRepositories()
    }

    private fun loadGitHubRepositories() {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                when (val response = repository.getGitHubRepositories("Kotlin", 10, 1)) {
                    is Resource.Success -> {
                        list = response.data!!.filter { it.language == "Kotlin" }
                        if(list.isNotEmpty()) isLoading = false
                    }

                    is Resource.Error -> {
                        isLoading = false
                    }

                    else -> {
                        isLoading = false
                    }
                }
            } catch (e: Exception) {
                isLoading = false
            }
        }
    }
}
