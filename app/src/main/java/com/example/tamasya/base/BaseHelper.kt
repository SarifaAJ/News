package com.example.tamasya.base

import com.example.tamasya.network.ClientService

open class BaseHelper {
    val ApiServiceServer by lazy { ClientService().create(file = false) }
}