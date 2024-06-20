package com.bamyanggit.user.entity

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>
