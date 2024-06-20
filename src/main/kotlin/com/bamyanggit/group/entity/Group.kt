package com.bamyanggit.group.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Group(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
    val currentParticipant: Int = 0,
    val maximumParticipant: Int = 5,
    val groupLeaderId: Long,
)
