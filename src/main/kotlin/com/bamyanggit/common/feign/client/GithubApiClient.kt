package com.bamyanggit.common.feign.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "GithubApiClient", url = "https://api.github.com")
interface GithubApiClient {

    @GetMapping("/user")
    fun getInfo(
        @RequestHeader("Authorization") token: String,
    ): String
}
