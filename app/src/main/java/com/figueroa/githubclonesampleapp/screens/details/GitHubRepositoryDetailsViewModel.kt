package com.figueroa.githubclonesampleapp.screens.details

import androidx.lifecycle.ViewModel
import com.figueroa.githubclonesampleapp.data.Resource
import com.figueroa.githubclonesampleapp.model.GitHubRepositoryInformation
import com.figueroa.githubclonesampleapp.repository.GitHubRepositoryRepository

class GitHubRepositoryDetailsViewModel(private val repository: GitHubRepositoryRepository) :
    ViewModel() {

    suspend fun getGitHubRepositoryInformation(
        owner: String,
        name: String
    ): Resource<GitHubRepositoryInformation> {
        return repository.getGitHubRepositoryInformation(owner, name)
    }

}