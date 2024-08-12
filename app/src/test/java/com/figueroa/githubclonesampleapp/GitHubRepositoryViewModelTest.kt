package com.figueroa.githubclonesampleapp

import com.figueroa.githubclonesampleapp.data.Resource
import com.figueroa.githubclonesampleapp.model.GitHubRepositoryInformation
import com.figueroa.githubclonesampleapp.model.GitHubRepositoryLicense
import com.figueroa.githubclonesampleapp.model.GitHubRepositoryOwner
import com.figueroa.githubclonesampleapp.repository.GitHubRepositoryRepository
import com.figueroa.githubclonesampleapp.screens.details.GitHubRepositoryDetailsViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GitHubRepositoryDetailsViewModelTest {

    private lateinit var repository: GitHubRepositoryRepository
    private lateinit var viewModel: GitHubRepositoryDetailsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = Mockito.mock(GitHubRepositoryRepository::class.java)
        viewModel = GitHubRepositoryDetailsViewModel(repository)
    }

    @Test
    fun `test getGitHubRepositoryInformation success`() = runBlocking {
        // Mock data
        val mockData = GitHubRepositoryInformation(
            allow_forking = true,
            archive_url = "https://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}",
            archived = false,
            assignees_url = "https://api.github.com/repos/octocat/Hello-World/assignees{/user}",
            blobs_url = "https://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}",
            branches_url = "https://api.github.com/repos/octocat/Hello-World/branches{/branch}",
            clone_url = "https://github.com/octocat/Hello-World.git",
            collaborators_url = "https://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}",
            comments_url = "https://api.github.com/repos/octocat/Hello-World/comments{/number}",
            commits_url = "https://api.github.com/repos/octocat/Hello-World/commits{/sha}",
            compare_url = "https://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}",
            contents_url = "https://api.github.com/repos/octocat/Hello-World/contents/{+path}",
            contributors_url = "https://api.github.com/repos/octocat/Hello-World/contributors",
            created_at = "2023-01-01T00:00:00Z",
            default_branch = "main",
            deployments_url = "https://api.github.com/repos/octocat/Hello-World/deployments",
            description = "This is a sample repository",
            disabled = false,
            downloads_url = "https://api.github.com/repos/octocat/Hello-World/downloads",
            events_url = "https://api.github.com/repos/octocat/Hello-World/events",
            fork = false,
            forks = 42,
            forks_count = 42,
            forks_url = "https://api.github.com/repos/octocat/Hello-World/forks",
            full_name = "octocat/Hello-World",
            git_commits_url = "https://api.github.com/repos/octocat/Hello-World/git/commits{/sha}",
            git_refs_url = "https://api.github.com/repos/octocat/Hello-World/git/refs{/sha}",
            git_tags_url = "https://api.github.com/repos/octocat/Hello-World/git/tags{/sha}",
            git_url = "git://github.com/octocat/Hello-World.git",
            has_discussions = true,
            has_downloads = true,
            has_issues = true,
            has_pages = false,
            has_projects = true,
            has_wiki = true,
            homepage = "https://github.com",
            hooks_url = "https://api.github.com/repos/octocat/Hello-World/hooks",
            html_url = "https://github.com/octocat/Hello-World",
            id = 1296269,
            is_template = false,
            issue_comment_url = "https://api.github.com/repos/octocat/Hello-World/issues/comments{/number}",
            issue_events_url = "https://api.github.com/repos/octocat/Hello-World/issues/events{/number}",
            issues_url = "https://api.github.com/repos/octocat/Hello-World/issues{/number}",
            keys_url = "https://api.github.com/repos/octocat/Hello-World/keys{/key_id}",
            labels_url = "https://api.github.com/repos/octocat/Hello-World/labels{/name}",
            language = "Kotlin",
            languages_url = "https://api.github.com/repos/octocat/Hello-World/languages",
            license = GitHubRepositoryLicense(
                key = "mit",
                name = "MIT License",
                spdx_id = "MIT",
                url = "https://api.github.com/licenses/mit",
                node_id = "MDc6TGljZW5zZW1pdA=="
            ),
            merges_url = "https://api.github.com/repos/octocat/Hello-World/merges",
            milestones_url = "https://api.github.com/repos/octocat/Hello-World/milestones{/number}",
            mirror_url = "",
            name = "Hello-World",
            node_id = "MDEwOlJlcG9zaXRvcnkxMjk2MjY5",
            notifications_url = "https://api.github.com/repos/octocat/Hello-World/notifications{?since,all,participating}",
            open_issues = 1,
            open_issues_count = 1,
            owner = GitHubRepositoryOwner(
                login = "octocat",
                id = 1,
                node_id = "MDQ6VXNlcjE=",
                avatar_url = "https://github.com/images/error/octocat_happy.gif",
                gravatar_id = "",
                url = "https://api.github.com/users/octocat",
                html_url = "https://github.com/octocat",
                followers_url = "https://api.github.com/users/octocat/followers",
                following_url = "https://api.github.com/users/octocat/following{/other_user}",
                gists_url = "https://api.github.com/users/octocat/gists{/gist_id}",
                starred_url = "https://api.github.com/users/octocat/starred{/owner}{/repo}",
                subscriptions_url = "https://api.github.com/users/octocat/subscriptions",
                organizations_url = "https://api.github.com/users/octocat/orgs",
                repos_url = "https://api.github.com/users/octocat/repos",
                events_url = "https://api.github.com/users/octocat/events{/privacy}",
                received_events_url = "https://api.github.com/users/octocat/received_events",
                type = "User",
                site_admin = false
            ),
            `private` = false,
            pulls_url = "https://api.github.com/repos/octocat/Hello-World/pulls{/number}",
            pushed_at = "2023-01-01T00:00:00Z",
            releases_url = "https://api.github.com/repos/octocat/Hello-World/releases{/id}",
            score = 1.0,
            size = 108,
            ssh_url = "git@github.com:octocat/Hello-World.git",
            stargazers_count = 80,
            stargazers_url = "https://api.github.com/repos/octocat/Hello-World/stargazers",
            statuses_url = "https://api.github.com/repos/octocat/Hello-World/statuses/{sha}",
            subscribers_url = "https://api.github.com/repos/octocat/Hello-World/subscribers",
            subscription_url = "https://api.github.com/repos/octocat/Hello-World/subscription",
            svn_url = "https://svn.github.com/octocat/Hello-World",
            tags_url = "https://api.github.com/repos/octocat/Hello-World/tags",
            teams_url = "https://api.github.com/repos/octocat/Hello-World/teams",
            topics = listOf("octocat", "repository", "github"),
            trees_url = "https://api.github.com/repos/octocat/Hello-World/git/trees{/sha}",
            updated_at = "2023-01-01T00:00:00Z",
            url = "https://api.github.com/repos/octocat/Hello-World",
            visibility = "public",
            watchers = 80,
            watchers_count = 80,
            web_commit_signoff_required = false
        )
        val mockResource = Resource.Success(data = mockData)

        // Mock the repository response
        Mockito.`when`(repository.getGitHubRepositoryInformation("octocat", "Hello-World"))
            .thenReturn(mockResource)

        // Call the view model method
        val resource = viewModel.getGitHubRepositoryInformation("octocat", "Hello-World")

        // Verify the response
        assertTrue(resource is Resource.Success)
        assertEquals("Hello-World", (resource as Resource.Success).data?.name ?: "")
        assertEquals("This is a sample repository", resource.data?.description ?: "")
    }

    @Test
    fun `test getGitHubRepositoryInformation error`() = runBlocking {
        // Mock error
        val mockResource = Resource.Error<GitHubRepositoryInformation>(message = "An error occurred")

        // Mock the repository response
        Mockito.`when`(repository.getGitHubRepositoryInformation("octocat", "Hello-World"))
            .thenReturn(mockResource)

        // Call the view model method
        val resource = viewModel.getGitHubRepositoryInformation("octocat", "Hello-World")

        // Verify the response
        assertTrue(resource is Resource.Error)
        assertEquals("An error occurred", (resource as Resource.Error).message)
    }
}