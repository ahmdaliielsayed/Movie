package com.example.movielist.utils

object Constants {

    lateinit var BASE_URL: String
    lateinit var IMAGE_URL: String
    lateinit var API_KEY: String

    const val ONE = 1

    const val HTTP_REQUEST_TIMEOUT = 408
    const val HTTP_TOO_MANY_REQUESTS = 429
    val HTTP_SERVER_ERROR_RANGE = 500..599

    const val YEAR_2024 = 2024

    const val SPLASH_TIME_OUT: Long = 3000

    const val EMPTY = ""
    const val SPACE = " "
    const val LOCALE_EN_US = "en-US"
}