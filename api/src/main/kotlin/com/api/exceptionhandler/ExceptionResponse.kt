package com.api.exceptionhandler

import com.core.component.exception.CommonException
import com.core.component.exception.CommonExceptionType
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@NoArgsConstructor
class ExceptionResponse {
    private var code: String?
    private var message: String?

    constructor(type: CommonExceptionType) {
        this.code = type.code
        this.message = type.message
    }

    constructor(type: CommonExceptionType, e: Exception) {
        this.code = type.code
        this.message = type.message + ", " + e.message
    }

    constructor(e: CommonException) {
        this.code = e.type?.code
        this.message = e.message
    }

    constructor(type: CommonExceptionType, message: String) {
        this.code = type.code
        this.message = type.message + ", " + message
    }
}
