package com.example.news.base

import com.example.news.network.ClientService

open class BaseHelper {
    val ApiServiceServer by lazy { ClientService().create(file = false) }
}