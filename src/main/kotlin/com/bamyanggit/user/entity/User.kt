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

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    val email: String,

    targetCommit: Int = 0,
    currentTotalCommit: Int = 0,
    schoolId: Int,
) {
    var targetCommit: Int = targetCommit
        private set

    var currentTotalCommit: Int = currentTotalCommit
        private set

    var schoolId: Int = schoolId
        private set
}
