package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListContract
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.foundation.arch.converter.Converter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * The interactor is responsible for interacting with the data source and preparing the
 * data model for the view model.
 */
class BurnerListInteractorImpl(
    private val repository: BurnerListContract.Repository,
    private val converter: Converter<ResponseModel, BurnerListDataModelImpl>,
    private val dispatcher: CoroutineContext
): BurnerListContract.Interactor {

    private var dataModel: BurnerListDataModelImpl? = null

    override fun onColorChange(value: Int) {
        dataModel?.setColor(value)
    }

    override suspend fun fetchDataModel(): BurnerListContract.DataModel {
        return withContext(dispatcher) {
            converter.convert(repository.fetch()).apply {
                this@BurnerListInteractorImpl.dataModel = this
            }
        }
    }
}
