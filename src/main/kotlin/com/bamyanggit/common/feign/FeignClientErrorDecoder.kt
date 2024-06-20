package com.bamyanggit.common.feign

import com.bamyanggit.common.error.exception.InternalServerErrorException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder

class FeignClientErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception {
        if (response.status() >= 400) {
            throw InternalServerErrorException
        }

        return FeignException.errorStatus(methodKey, response)
    }
}
