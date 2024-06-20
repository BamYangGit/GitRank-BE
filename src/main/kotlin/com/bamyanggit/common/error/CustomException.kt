package com.bamyanggit.common.error

abstract class CustomException(
    val status: Int,
    override val message: String
) : RuntimeException() {
    override fun fillInStackTrace(): Throwable = this
}
