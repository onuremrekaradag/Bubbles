package com.kefelon.bubbles

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // Private MutableSharedFlow
    private val _counterSharedFLow = MutableSharedFlow<Int>(
        replay = 3,
        extraBufferCapacity = 5,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    // Public read-only SharedFlow
    val counterSharedFLow: SharedFlow<Int> = _counterSharedFLow

    fun startToCount() {
        // CoroutineScope (viewModelScope.launch) only required for delay(1000) function
        viewModelScope.launch {
            (1..10).forEach { value ->
                delay(1000)
                Log.e("SharedFlow", "$value emited in SharedFlow")
                _counterSharedFLow.emit(value)
            }
        }
    }

}