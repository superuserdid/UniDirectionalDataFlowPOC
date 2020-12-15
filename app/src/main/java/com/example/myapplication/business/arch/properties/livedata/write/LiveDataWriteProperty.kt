package com.example.myapplication.business.arch.properties.livedata.write

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.foundation.arch.properties.write.WriteProperty

class LiveDataWriteProperty<T>(
    private val liveData: MutableLiveData<T>
): WriteProperty<T> {

    override fun set(value: T) {
        liveData.postValue(value)
    }
}
