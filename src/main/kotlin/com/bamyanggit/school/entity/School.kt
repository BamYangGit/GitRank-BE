package com.bamyanggit.school.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class School(
    @Id
    val id: Int,

    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    val name: String,
)
