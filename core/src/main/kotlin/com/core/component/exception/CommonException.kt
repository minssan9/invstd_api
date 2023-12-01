package com.core.component.exception

import com.google.gson.Gson
import lombok.Getter
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@RequiredArgsConstructor
@Getter
class CommonException : RuntimeException {

    var gson: Gson? = null
    private var type: CommonExceptionType? = null
    val commonExceptionUtils = CommonExceptionUtils()

    constructor(type: CommonExceptionType) : super(type.message) {
        this.type = type
    }

    constructor(type: CommonExceptionType, message: String) : super(type.message + message) {
        this.type = type
    }

    constructor(type: CommonExceptionType, e: Exception) : super(
        type.code + type.message + " / " + e.message + " / " +
                Arrays.stream<StackTraceElement>(e.stackTrace).findFirst().get().className + " " +
                Arrays.stream<StackTraceElement>(e.stackTrace).findFirst().get().lineNumber
    ) {
        this.type = type
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
