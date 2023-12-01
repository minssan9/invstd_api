package com.core.component.exception

import lombok.AllArgsConstructor
import lombok.Getter

@Getter
@AllArgsConstructor
enum class CommonExceptionType(s: String, s1: String) {
    INTERNAL_SERVER_ERROR("1000", "Internal server error"),

    // validation
    INVALID_PARAMS("2000", "Invalid params"),
    INVALID_REQUEST("2000", "Bad Request, Please Check Again. 잘못된 요청입니다. 다시 확인해주세요. "),
    UNSUPPORTED_SORT_TYPE("2010", "UNSUPPORTED_SORT_TYPE, 지원하지 않는 정렬"),

    // user
    CANNOT_FOUND_USER("3000", "Cannot found user"),
    EMAIL_EXIST_EXCEPTION_MSG("3000", "이미 계정이 존재합니다."),
    NICKNAME_EXIST_EXCEPTION_MSG("3000", "이미 닉네임이 존재합니다."),
    NOTEXIST_ACCOUNT_EXCEPTION_MSG("3000", "계정이 없습니다."),
    SIGNIN_EXCEPTION_MSG("3000", "로그인정보가 일치하지 않습니다."),
    SUCCESS_MESSAGE("3000", "SUCCESS"),
    NOT_FOUND_MESSAGE("3000", "NOT FOUND"),
    FAILED_MESSAGE("3000", "서버에서 오류가 발생하였습니다."),
    INVALID_ACCESS_TOKEN("3000", "Invalid access token."),
    INVALID_REFRESH_TOKEN("3000", "Invalid refresh token."),
    NOT_EXPIRED_TOKEN_YET("3000", "Not expired token yet."),

    // database
    NONEXIST_DATA_EXCEPTION_MSG("8000", "데이터를 찾을 수 없습니다. "),

    // business
    NOTEXIST_ITEM("4100", "Cannot found Item"),
    NOTEXIST_ITEM_TEMPLATE("4100", "Cannot found Item template"),

    // HOMS
    REGEX_ERROR("9999", "REGEX_ERROR");

    val code: String? = null
    val message: String? = null
}
