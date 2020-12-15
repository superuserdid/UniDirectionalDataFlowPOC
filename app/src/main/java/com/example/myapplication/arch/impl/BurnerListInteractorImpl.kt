package com.example.myapplication.arch.impl

import com.example.myapplication.arch.BurnerListDataModel
import com.example.myapplication.arch.BurnerListInteractor
import com.example.myapplication.arch.BurnerListRepository
import com.example.myapplication.arch.BurnerListWritableModel
import com.example.myapplication.arch.models.Response
import com.example.myapplication.business.arch.creator.ReadableModelCreator
import com.example.myapplication.business.arch.creator.WritableModelCreator
import com.example.myapplication.foundation.arch.converter.Converter

/**
 * The interactor is responsible for interacting with the data source and preparing the
 * data model for the view model.
 */
class BurnerListInteractorImpl(
    private val repository: BurnerListRepository,
    private val converter: Converter<Response, BurnerListDataModelImpl>,
    private val writableCreator: WritableModelCreator<Response, BurnerListWritableModel>,
    private val readableCreator: ReadableModelCreator<BurnerListDataModel>
): BurnerListInteractor {

    private var writable: BurnerListWritableModel? = null

    override fun onColorChange(value: Int) {
        writable?.setColor(value)
    }

    override fun fetchDataModel(callback: (BurnerListDataModel) -> Unit) {
        // Fetch response from data source
        repository.fetch { response ->
            // Convert the response to an immutable data model
            converter.convert(response) { dataModel ->
                // create the writable with the source of truth to allow delegating writes from the interactor
                writable = writableCreator.create(WritableModelCreator.Input(response, dataModel))
                // create/expose only readable attributes of the data model to the view model
                callback(readableCreator.create(dataModel))
            }
        }
    }
}
