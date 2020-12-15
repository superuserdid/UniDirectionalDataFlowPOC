package com.example.myapplication.business.arch.creator

import com.example.myapplication.business.arch.models.write.WritableModel
import com.example.myapplication.foundation.arch.creator.Creator

interface WritableModelCreator<SOURCE, T>: Creator<WritableModelCreator.Input<SOURCE, T>, T> where T: WritableModel {

    class Input<SOURCE, MODEL>(val source: SOURCE, val model: MODEL)
}
