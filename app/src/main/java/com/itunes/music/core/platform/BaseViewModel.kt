package com.itunes.music.core.platform

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.itunes.music.core.exception.Failure

abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}