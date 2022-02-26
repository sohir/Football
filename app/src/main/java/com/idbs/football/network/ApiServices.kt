package com.idbs.football.network

import com.idbs.football.ui.league.LeagueResponsModel
import com.idbs.football.ui.teams.TeamsListResponsModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiServices {

    @GET("competitions")
    fun getLeagueList(): Deferred<LeagueResponsModel>

    @GET("competitions/{leagueId}/teams")
    fun getTeamsList(
      @Path("leagueId") id: String
    ): Deferred<TeamsListResponsModel>
}