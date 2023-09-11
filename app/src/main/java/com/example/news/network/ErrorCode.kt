package com.example.news.network

enum class HttpErrorCode(val code: Int, val message: String) {
    BAD_REQUEST(400, "Bad request"),
    FORBIDDEN(403, "Forbidden"),
    USER_NOT_FOUND(404, "User not found"),
    SERVER_ERROR(500, "Server error")
}

fun getErrorMessage(code: Int): String {
    val errorCode = HttpErrorCode.values().find { it.code == code }
    return errorCode?.message ?: "Error: HTTP status code $code"
}