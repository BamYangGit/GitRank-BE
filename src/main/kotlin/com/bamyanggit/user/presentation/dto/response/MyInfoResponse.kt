package com.bamyanggit.user.presentation.dto.response

data class MyInfoResponse(
    val id: Long,
    val accountId: String,
    val currentTotalCommit: Int,
    val followerCount: Int,
    val followingCount: Int,
    val imageUrl: String,
    val targetCommit: Int,
    val todayCommit: Int,
    val yesterdayCommit: Int,
)
