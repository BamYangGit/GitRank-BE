package com.bamyanggit.common.feign.client

import com.bamyanggit.common.feign.client.dto.FeignUserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "GithubApiClient", url = "https://api.github.com")
interface GithubApiClient {

    @GetMapping("/user")
    fun getUserInfo(
        @RequestHeader("Authorization") token: String,
    ): FeignUserInfoResponse
}
