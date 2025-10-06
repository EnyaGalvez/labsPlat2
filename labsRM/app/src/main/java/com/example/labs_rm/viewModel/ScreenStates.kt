package com.example.labs_rm.viewModel

data class ScreenStates (
    val isLoading: Boolean = true,
    val isLoaded: Boolean = false,
    val isError: Boolean = false,
    val currentInt: Int = 0
)