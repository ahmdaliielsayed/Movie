package com.example.movielist.listofmovies.domain.utils

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    EMPTY_RESPONSE,
    API_ERROR,
    UNKNOWN,
}