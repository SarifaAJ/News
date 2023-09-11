package com.example.news.helper

import android.os.Environment

object Config {

    const val BASE_URL = "https://tamasya.technice.id/"
    const val BASE_API = BASE_URL  + "api/"


    var DIRECTORY_IMAGE: String = Environment.getExternalStorageDirectory().toString() + "/Tamasya/"

}