package com.bamyanggit.user.presentation

import com.bamyanggit.common.feign.client.dto.TokenResponse
import com.bamyanggit.user.presentation.dto.request.CodeRequest
import com.bamyanggit.user.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody request: CodeRequest): TokenResponse {
        return userService.signUp(request.code)
    }
}
