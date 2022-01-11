package com.matrix.githubbrowser.domain.utils

import androidx.lifecycle.MutableLiveData

class Extensions {

    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

}