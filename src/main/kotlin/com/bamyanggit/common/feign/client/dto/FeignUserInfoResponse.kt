package com.bamyanggit.common.feign.client.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = SnakeCaseStrategy::class)
data class FeignUserInfoResponse(
    val login: String?,
    val avatarUrl: String?,
    val name: String?,
    val followers: String?,
    val following: String?,
)
