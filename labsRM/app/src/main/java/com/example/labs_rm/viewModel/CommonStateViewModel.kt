package com.example.labs_rm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class CommonStateViewModel(
    private val delayMs: Long = 2000L
): ViewModel() {

    private val _state = MutableStateFlow(ScreenStates())

    val state = _state.asStateFlow()

    init {
        switchState()
    }

    fun switchState() {
        _state.update {
            it.copy(
                isLoading = true,
                isLoaded = false,
                isError = false,
                currentInt = 0
            )
        }
        viewModelScope.launch {
            delay(delayMs)
            val randomInt = (0..10).random()
            if (randomInt % 2 == 0) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isLoaded = true,
                        currentInt = randomInt
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isLoaded = false,
                        isError = true
                    )
                }

            }
        }

    }
}