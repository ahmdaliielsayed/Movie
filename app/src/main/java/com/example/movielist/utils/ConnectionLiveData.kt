package com.example.movielist.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.WorkerThread
import androidx.core.content.getSystemService
import androidx.lifecycle.LiveData

class ConnectionLiveData(context: Context) : LiveData<Boolean>() {
    private val connectivityManager: ConnectivityManager? = context.getSystemService()
    private lateinit var connectivityManagerCallback: NetworkCallback

    override fun setValue(value: Boolean?) {
        if (getValue() == value) {
            return
        }
        super.setValue(value)
    }

    override fun onActive() {
        super.onActive()
        updateConnection()
        connectivityManagerCallback = object : NetworkCallback() {
            @WorkerThread
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities,
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                if (networkCapabilities.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_INTERNET,
                    ) &&
                    networkCapabilities.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED,
                    )
                ) {
                    postValue(true)
                }
            }

            @WorkerThread
            override fun onLost(network: Network) {
                postValue(false)
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager?.registerDefaultNetworkCallback(connectivityManagerCallback)
        } else {
            val networkRequest = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build()
            connectivityManager?.registerNetworkCallback(
                networkRequest,
                connectivityManagerCallback,
            )
        }
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager?.unregisterNetworkCallback(connectivityManagerCallback)
    }

    @Suppress("DEPRECATION")
    private fun updateConnection() {
        val activeNetwork: NetworkInfo? = connectivityManager?.activeNetworkInfo
        value = activeNetwork?.isConnected == true
    }
}