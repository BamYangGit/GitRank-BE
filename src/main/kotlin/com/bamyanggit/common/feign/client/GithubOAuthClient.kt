package com.bamyanggit.common.feign.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "GithubOAuthClient", url = "https://github.com")
interface GithubOAuthClient {

    @GetMapping("/login/oauth/access_token", produces = ["application/json"])
    fun getAccessToken(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("code") code: String
    ): String
}
