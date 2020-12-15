package com.example.myapplication.business.arch.properties.rx.read

import android.annotation.SuppressLint
import com.example.myapplication.foundation.arch.properties.read.ObservableReadProperty
import io.reactivex.subjects.PublishSubject

@SuppressLint("CheckResult")
class RxPublishSubjectObservableReadProperty<T>(
    private val subject: PublishSubject<T>
): ObservableReadProperty<T> {

    init {
        subject.subscribe { value ->
            observers.forEach { it.invoke(value) }
        }
    }

    private val observers: ArrayList<(T) -> Unit> = arrayListOf()

    override val value: T
        get() = subject.blockingFirst()

    override fun observe(callback: (T) -> Unit) {
        observers.add(callback)
    }
}
