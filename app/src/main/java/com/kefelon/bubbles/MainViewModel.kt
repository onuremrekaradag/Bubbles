package com.kefelon.bubbles

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // Private MutableStateFlow
    private val _counterStateFLow = MutableStateFlow(0)

    // Public read-only StateFlow
    val counterStateFLow: StateFlow<Int> = _counterStateFLow

    fun startToCount() {
        // CoroutineScope (viewModelScope.launch) only required for delay(1000) function
        viewModelScope.launch {
            (1..10).forEach { value ->
                delay(1000)
                Log.e("StateFlow", "$value emited in StateFlow")
                _counterStateFLow.value = value
            }
        }
    }

}