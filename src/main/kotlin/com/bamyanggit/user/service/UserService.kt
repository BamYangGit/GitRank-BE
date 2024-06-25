package com.bamyanggit.user.service

import com.bamyanggit.common.feign.client.GithubApiClient
import com.bamyanggit.common.feign.client.GithubOAuthClient
import com.bamyanggit.common.jsoup.GithubContributionsCrawler
import com.bamyanggit.user.entity.User
import com.bamyanggit.user.entity.UserRepository
import com.bamyanggit.user.presentation.dto.response.MyInfoResponse
import com.bamyanggit.user.presentation.dto.response.TokenResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    @Value("\${spring.security.oauth2.client.registration.github.client-id}")
    private val clientId: String,
    @Value("\${spring.security.oauth2.client.registration.github.client-secret}")
    private val clientSecret: String,
    private val githubOAuthClient: GithubOAuthClient,
    private val githubApiClient: GithubApiClient,
    private val githubContributionsCrawler: GithubContributionsCrawler,
) {

    fun signUp(code: String): TokenResponse {
        val feignTokenResponse = githubOAuthClient.getAccessToken(clientId, clientSecret, code)
        val feignUserInfo = githubApiClient.getUserInfo("token ${feignTokenResponse.accessToken}")

        val contributions = githubContributionsCrawler.getContributionCount(feignUserInfo.login!!)

        userRepository.save(
            User(
                accountId = feignUserInfo.login,
                imageUrl = feignUserInfo.avatarUrl ?: "",
                followerCount = feignUserInfo.followers?.toIntOrNull() ?: 0,
                followingCount = feignUserInfo.following?.toIntOrNull() ?: 0,
                currentTotalCommit = contributions.totalCommit,
                todayCommit = contributions.todayCommit,
                yesterdayCommit = contributions.yesterdayCommit,
            )
        )

        return TokenResponse(feignTokenResponse.accessToken!!)
    }

    fun getMyInfo(): MyInfoResponse {
        val user = userRepository.findByIdOrNull(1)
            ?: throw RuntimeException()

        return MyInfoResponse(
            id = user.id,
            accountId = user.accountId,
            currentTotalCommit = user.currentTotalCommit,
            followerCount = user.followerCount,
            followingCount = user.followingCount,
            imageUrl = user.imageUrl,
            targetCommit = user.targetCommit,
            todayCommit = user.todayCommit,
            yesterdayCommit = user.yesterdayCommit,
        )
    }
}
