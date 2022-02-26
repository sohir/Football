package com.idbs.football.ui.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.idbs.football.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LeagueRepo @Inject constructor(val apiServices: ApiServices) {
    private var _leagueList = MutableLiveData<LeagueResponsModel>()
    var leagueList: LiveData<LeagueResponsModel> = _leagueList


    suspend fun getList(){
        withContext(Dispatchers.IO) {
            val response  = apiServices.getLeagueList().await()
            _leagueList.postValue(response)
        }
    }

}