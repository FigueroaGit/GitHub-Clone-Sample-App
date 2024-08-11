package com.figueroa.githubclonesampleapp.repository

import com.figueroa.githubclonesampleapp.data.Resource
import com.figueroa.githubclonesampleapp.model.GitHubRepositoryInformation
import com.figueroa.githubclonesampleapp.network.GitHubRepositoryAPI

class GitHubRepositoryRepository(private val API: GitHubRepositoryAPI) {
    suspend fun getGitHubRepositories(
        language: String,
        perPage: Int,
        page: Int
    ): Resource<List<GitHubRepositoryInformation>> {
        return try {
            Resource.Loading(data = true)
            val itemList =
                API.getGitHubRepositories(query = language, perPage = perPage, page = page).items
            if (itemList.isNotEmpty()) {
                Resource.Loading(data = false)
            }
            Resource.Success(data = itemList)
        } catch (exception: Exception) {
            Resource.Error(message = exception.message.toString())
        }
    }

    suspend fun getGitHubRepositoryInformation(
        owner: String,
        name: String
    ): Resource<GitHubRepositoryInformation> {
        val response = try {
            Resource.Loading(data = true)
            API.getGitHubRepositoryInformation(owner, name)
        } catch (exception: Exception) {
            return Resource.Error(message = "An error occurred ${exception.message}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }
}