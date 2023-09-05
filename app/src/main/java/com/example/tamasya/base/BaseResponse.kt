package com.example.tamasya.base

data class BaseResponse<T>(
    val error: Boolean,
    val message: String,
    val data: T?
)