package com.kefelon.bubbles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.kefelon.bubbles.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
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


        showMessageBubblesSample()
    }


    private fun showMessageBubblesSample() {
        lifecycleScope.launch {
            (1..10).forEach { int ->
                delay(1000)
                arrayList.add(int)
                recyclerViewAdapter.notifyItemInserted(arrayList.size)
            }
        }
    }
}