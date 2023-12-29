package com.core.component.exception

import java.text.MessageFormat

class CommonExceptionUtils {
    /**
     * Build a message for the given base message and root cause.
     *
     * @return the full exception message
     */
    fun buildMessage(type: CommonExceptionType, msgParams: Array<Any>): String {

        //message property 파일 사용 X처리 -> 공통 에러콜백함수로 키값 및 파라미터 전달
        val pMsgKey = type.code
        val pMsgParams = msgParams as Array<String>

        //JSON 데이터를 위한 StringBuffer 데이터 생성
        val sb = StringBuffer()
        sb.append("{'msgKey': '")
        sb.append(pMsgKey)
        sb.append("', 'msgParams':[")
        for (i in pMsgParams.indices) {
            if (i > 0) sb.append(",")
            sb.append("'" + pMsgParams[i] + "'")
        }
        sb.append("]}")

        //StringBuffer -> JSON 변경
//        val jsonObject: JSONObject = JSONObject.fromObject(JSONSerializer.toJSON(sb.toString()))
        return sb.toString()
    }

    fun buildMessage(msgKey: String?, msgParams: Array<Any?>?): String? {
        var userMessage = msgKey
        if (msgParams != null) {
            userMessage = MessageFormat.format(msgKey, *msgParams)
        }
        return userMessage
    }
}
