package com.idbs.football.utilit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idbs.football.network.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel : ViewModel() {
    internal val _networkState = MutableLiveData<LoadingState?>()
    val networkState: MutableLiveData<LoadingState?> = _networkState

    val job = SupervisorJob()
    internal val viewModelScope = CoroutineScope(job + Dispatchers.Main)


    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun emptyLoadingState(){
        _networkState.value = null
    }
}