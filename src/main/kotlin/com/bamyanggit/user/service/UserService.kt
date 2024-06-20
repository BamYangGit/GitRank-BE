package com.bamyanggit.user.service

import com.bamyanggit.common.feign.client.GithubApiClient
import com.bamyanggit.common.feign.client.GithubOAuthClient
import com.bamyanggit.common.feign.client.dto.TokenResponse
import com.bamyanggit.user.entity.User
import com.bamyanggit.user.entity.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
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
    private val snakeCaseObjectMapper: ObjectMapper = jacksonObjectMapper().apply {
        propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
    }

    fun signUp(code: String): TokenResponse {
        val jsonString = githubOAuthClient.getAccessToken(clientId, clientSecret, code)
        val accessToken = jsonToTokenResponse(jsonString).accessToken

        println(githubApiClient.getInfo("token $accessToken"))

        userRepository.save(
            User(
                email =
            )
        )

        return TokenResponse(accessToken)
    }

    private fun jsonToTokenResponse(jsonString: String): TokenResponse {
        return snakeCaseObjectMapper.readValue(jsonString)
    }
}
