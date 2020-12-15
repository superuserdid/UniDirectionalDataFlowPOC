package com.example.myapplication.arch

interface BurnerListInteractor {

    fun onColorChange(value: Int)

    fun fetchDataModel(callback: (BurnerListDataModel) -> Unit)
}