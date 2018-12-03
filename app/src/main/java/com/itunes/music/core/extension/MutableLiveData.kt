package com.itunes.music.core.extension

import android.arch.lifecycle.MutableLiveData

fun <T> MutableLiveData<MutableList<T>>.addValues(values: List<T>) {
    val value = this.value ?: arrayListOf()
    value.addAll(values)
    this.value = value
}