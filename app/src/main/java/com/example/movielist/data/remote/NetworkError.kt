package com.example.movielist.data.remote

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