package com.example.myapplication.business.arch.properties.rx.write

import com.example.myapplication.foundation.arch.properties.write.WriteProperty
import io.reactivex.subjects.PublishSubject

class RxPublishSubjectWriteProperty<T>(
    private val subject: PublishSubject<T>,
    initialValue: T
): WriteProperty<T> {

    init {
        subject.onNext(initialValue)
    }

    override fun set(value: T) {
        subject.onNext(value)
    }
}
