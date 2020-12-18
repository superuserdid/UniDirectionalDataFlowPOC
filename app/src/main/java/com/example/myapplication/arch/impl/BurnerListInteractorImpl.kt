package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListContract
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.business.arch.properties.nativeimpl.FlowObservableProperty
import com.example.myapplication.foundation.arch.converter.Converter
import com.example.myapplication.foundation.arch.properties.read.ObservableReadProperty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

/**
 * The interactor is responsible for interacting with the data source and preparing the
 * data model for the view model.
 */
class BurnerListInteractorImpl(
    private val repository: BurnerListContract.Repository,
    private val converter: Converter<ResponseModel, BurnerListDataModelImpl>,
    coroutineContext: CoroutineContext
): BurnerListContract.Interactor {

    // We will find a way to simplify this type of pattern of needing a shadow variable.
    private var _dataModel: BurnerListDataModelImpl? = null
    override val dataModel: Flow<BurnerListContract.DataModel> = flow {
        emit(
            converter.convert(repository.fetch()).apply {
                this@BurnerListInteractorImpl._dataModel = this
            }
        )
    }.flowOn(coroutineContext).catch {
        onError.set(BurnerListContract.Error.InitialFetch)
    }

    override val onError: FlowObservableProperty<BurnerListContract.Error> = FlowObservableProperty()

    override suspend fun onColorChange(value: Int) {
        // Rough example showing usage of different errors
        // and how we can pass error messages from this layer to the view model
        try {
            _dataModel?.setColor(value)
        } catch (e: Exception) {
            onError.set(BurnerListContract.Error.ColorChange(e))
        }
    }
}
