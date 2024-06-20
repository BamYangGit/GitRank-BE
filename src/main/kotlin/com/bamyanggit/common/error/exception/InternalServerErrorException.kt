package com.bamyanggit.common.error.exception

import com.bamyanggit.common.error.CustomException

object InternalServerErrorException : CustomException(
    status = 500,
    message = "Internal Server Error",
) {
    private fun readResolve(): Any = InternalServerErrorException
}
