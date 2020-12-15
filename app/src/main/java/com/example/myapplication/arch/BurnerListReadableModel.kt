package com.example.myapplication.arch

import com.example.myapplication.business.arch.models.read.ReadableModel
import com.example.myapplication.foundation.arch.properties.read.ObservableReadProperty

/**
 * This model defines the readable aspects of the data model.
 */
interface BurnerListReadableModel: ReadableModel {

    val color: ObservableReadProperty<Int>
}
