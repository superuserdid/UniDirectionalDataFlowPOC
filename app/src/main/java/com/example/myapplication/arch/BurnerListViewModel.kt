package com.example.myapplication.arch

import androidx.lifecycle.LiveData

// Only readable/immutable values
interface BurnerListViewModel {

    val isLoading: LiveData<Boolean>

    val burnerColor: LiveData<Int>

    fun onAttached()

    fun onColorChange(color: Int)
}
