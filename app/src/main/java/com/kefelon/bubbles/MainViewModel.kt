package com.kefelon.bubbles

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainViewModel : ViewModel() {

    val counterFlow: Flow<Int> = flow {
        (1..10).forEach { value ->
            delay(1000)
            Log.e("Flow", "$value emited in Flow")
            emit(value)
        }
    }

}