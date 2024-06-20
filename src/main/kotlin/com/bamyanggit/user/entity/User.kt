package com.bamyanggit.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    val accountId: String,

    imageUrl: String,
    followerCount: Int = 0,
    followingCount: Int = 0,
    targetCommit: Int = 0,
    currentTotalCommit: Int = 0,
    schoolId: Int,
) {
    @Column(columnDefinition = "VARCHAR(1000)", nullable = false)
    var imageUrl: String = imageUrl
        private set

    var followerCount: Int = followerCount
        private set

    var followingCount: Int = followingCount
        private set

    var targetCommit: Int = targetCommit
        private set

    var currentTotalCommit: Int = currentTotalCommit
        private set

    var schoolId: Int = schoolId
        private set
}
