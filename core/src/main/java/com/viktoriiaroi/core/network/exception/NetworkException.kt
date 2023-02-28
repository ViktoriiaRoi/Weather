package com.viktoriiaroi.core.network.exception

sealed class NetworkException(message: String) : Exception(message) {
    object EmptyData : NetworkException("No data for your city")
    object NoInternet : NetworkException("No internet connection")
}