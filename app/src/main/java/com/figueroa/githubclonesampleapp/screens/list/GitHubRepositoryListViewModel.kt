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
    var isLoading: Boolean by mutableStateOf(false)
    var currentPage = 1
    var isLastPage = false

    init {
        loadGitHubRepositories()
    }

    fun loadGitHubRepositories() {
        if (isLoading || isLastPage) return

        viewModelScope.launch {
            isLoading = true
            try {
                when (val response = repository.getGitHubRepositories("Kotlin", 30, currentPage)) {
                    is Resource.Success -> {
                        val newList = response.data!!.filter { it.language == "Kotlin" }
                        list = list + newList
                        currentPage++
                        if (newList.isEmpty()) {
                            isLastPage = true
                        }
                    }

                    is Resource.Error -> {
                        isLoading = false
                    }

                    else -> {
                        isLoading = false
                    }
                }
            } catch (e: Exception) {
                e.message
            } finally {
                isLoading = false
            }
        }
    }
}