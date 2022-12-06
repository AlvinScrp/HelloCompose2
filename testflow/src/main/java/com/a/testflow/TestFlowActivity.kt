package com.a.testflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TestFlowActivity : AppCompatActivity() {

    private val viewModel: TestFlowViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_flow)
        lifecycleScope.launch {
            Log.d("alvin","${Thread.currentThread()}")
            try {
                viewModel.state.collect {
                    Log.d("alvin","state : $it")
                    if (it == 3){
                        throw NullPointerException("终止第一个StateFlow的数据收集")
                    }
                }
            } catch (e: Exception) {
                Log.d("alvin","e : $e")
            }
            viewModel.name.collect {
                Log.d("alvin","name : $it")
            }
        }
        viewModel.download()
    }
}

class TestFlowViewModel : ViewModel() {
    private val _state: MutableStateFlow<Int> = MutableStateFlow(0)
    val state: StateFlow<Int> get() = _state

    private val _name: MutableStateFlow<String> = MutableStateFlow("第二个StateFlow")
    val name: StateFlow<String> get() = _name
    fun download() {
        for (state in 0..5) {
            viewModelScope.launch(Dispatchers.IO) {
                delay(200L * state)
                _state.value = state
            }
        }
    }
}