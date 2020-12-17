package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListContract
import com.example.myapplication.arch.models.ResponseModel
import com.example.myapplication.foundation.arch.converter.Converter

/**
 * The interactor is responsible for interacting with the data source and preparing the
 * data model for the view model.
 */
class BurnerListInteractorImpl(
    private val repository: BurnerListContract.Repository,
    private val converter: Converter<ResponseModel, BurnerListDataModelImpl>
): BurnerListContract.Interactor {

    private var dataModel: BurnerListDataModelImpl? = null

    override fun onColorChange(value: Int) {
        dataModel?.setColor(value)
    }

    override fun fetchDataModel(callback: (BurnerListContract.DataModel) -> Unit) {
        repository.fetch { response ->
            converter.convert(response) { dataModel ->
                this.dataModel = dataModel
                callback(dataModel)
            }
        }
    }
}
