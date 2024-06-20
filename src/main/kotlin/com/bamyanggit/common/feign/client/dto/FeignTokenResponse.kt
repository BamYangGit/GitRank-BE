package com.bamyanggit.common.feign.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class FeignTokenResponse(
    @JsonProperty("access_token")
    val accessToken: String?,
)
