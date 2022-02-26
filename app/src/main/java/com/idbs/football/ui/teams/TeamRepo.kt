package com.idbs.football.ui.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.idbs.football.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeamRepo @Inject constructor(val apiServices: ApiServices) {
    private var _teamList = MutableLiveData<TeamsListResponsModel>()
    var teamList: LiveData<TeamsListResponsModel> = _teamList


    suspend fun getTeamList(id:String){
        withContext(Dispatchers.IO) {
            val response  = apiServices.getTeamsList(id).await()
            _teamList.postValue(response)
        }
    }

}