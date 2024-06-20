package com.bamyanggit.user.service

import com.bamyanggit.common.feign.client.GithubApiClient
import com.bamyanggit.common.feign.client.GithubOAuthClient
import com.bamyanggit.user.entity.User
import com.bamyanggit.user.entity.UserRepository
import com.bamyanggit.user.presentation.dto.response.TokenResponse
import org.springframework.beans.factory.annotation.Value
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
) {

    fun signUp(code: String): TokenResponse {
        val feignTokenResponse = githubOAuthClient.getAccessToken(clientId, clientSecret, code)

        val feignUserInfo = githubApiClient.getUserInfo("token ${feignTokenResponse.accessToken}")

        // signup api 다시 짜기
        userRepository.save(
            User(
                accountId = feignUserInfo.login!!,
                imageUrl = feignUserInfo.avatarUrl ?: "",
                followerCount = feignUserInfo.followers,
                followingCount = feignUserInfo.following,
            )
        )

        return TokenResponse(feignTokenResponse.accessToken!!)
    }
}
