package com.example.movielist.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <T: Any?> Fragment.collect(flow: StateFlow<T>, body: (T?) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        flow.collect { value ->
            body(value)
        }
    }
}

fun <T: Any?> Fragment.observe(liveData: LiveData<T>, body: (T?) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer(body))
}