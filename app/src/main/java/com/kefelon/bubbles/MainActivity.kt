package com.kefelon.bubbles

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kefelon.bubbles.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private val arrayList = arrayListOf<Int>()
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        recyclerViewAdapter = RecyclerViewAdapter(arrayList)
        binding.recyclerView.adapter = recyclerViewAdapter

        mainViewModel.startToCount()
        collectStateFlow()
    }


    private fun collectStateFlow() {
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.counterStateFLow.buffer(2).collect { value ->
                    arrayList.add(value)
                    recyclerViewAdapter.notifyItemInserted(arrayList.size)
                    Log.e("StateFlow", "$value inserted to RecyclerView")
                }
            }
        }
    }
}