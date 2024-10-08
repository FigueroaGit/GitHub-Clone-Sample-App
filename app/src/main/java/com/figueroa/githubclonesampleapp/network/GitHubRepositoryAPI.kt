package com.figueroa.githubclonesampleapp.network

import com.figueroa.githubclonesampleapp.model.GitHubRepository
import com.figueroa.githubclonesampleapp.model.GitHubRepositoryInformation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubRepositoryAPI {

    @GET("/search/repositories")
    suspend fun getGitHubRepositories(
        @Query("q") query: String,
        @Query("perpage") perPage: Int,
        @Query("page") page: Int,
    ): GitHubRepository

    @GET("/repos/{owner}/{repo}")
    suspend fun getGitHubRepositoryInformation(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): GitHubRepositoryInformation


}