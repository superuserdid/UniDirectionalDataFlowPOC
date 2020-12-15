package com.example.myapplication.arch.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.arch.BurnerListInteractor
import com.example.myapplication.arch.BurnerListViewModel
import com.example.myapplication.business.arch.properties.rx.RxPublishSubjectObservableProperty
import com.example.myapplication.foundation.arch.properties.ObservableProperty
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

// ViewModel manages view things as well as
// rendering view from data provided by data model
// Notice, the ViewModel cannot cast the data model to anything else to write.
// View Model MUST delegate to the interactor according to the Unidirectional Data Flow
class BurnerListViewModelImpl(
    private val interactor: BurnerListInteractor
): ViewModel(), BurnerListViewModel {

    // We can use mutable versions of the same readable LiveData object in the impl
    override val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    override val burnerColor: MutableLiveData<Int> = MutableLiveData()

    override fun onAttached() {
        interactor.fetchDataModel {
            val color = it.color
            color.observe { colorValue ->
                burnerColor.postValue(colorValue)
            }

            // Arbitrary example of how Rx could be used inside of an implementation
            // which is OK to do since it is not exposing the dependency in the arch.
            // Note: While this is OK to do, it should be done wisely, at this point in the
            // data flow, we do not know for certain if the property is an Rx Observable Property.
            // therefore, changing this type upstream could cause issues here.
            if (color is RxPublishSubjectObservableProperty) {
                val observable: Observable<String> =
                    color.subject.zipWith(
                        color.subject,
                        BiFunction { t1, t2 ->
                            "$t1, $t2"
                        }
                    )
                observable.subscribe {}
            }
            isLoading.postValue(false)
        }
    }

    override fun onColorChange(color: Int) {
        isLoading.postValue(true)
        interactor.onColorChange(color)
    }
}
