package com.kefelon.bubbles

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _counterChannel = Channel<Int>(Channel.BUFFERED)

    val counterFlow = _counterChannel.receiveAsFlow()

    fun startToCount() {
        viewModelScope.launch {
            (1..10).forEach { value ->
                delay(1000)
                Log.e("Channel", "$value emited in Channel")
                _counterChannel.send(value)
            }
        }
    }

}