package com.idbs.football.di

import com.idbs.football.network.ApiServices
import com.idbs.football.ui.league.LeagueRepo
import com.idbs.football.ui.teams.TeamRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepoModule {
    @Provides
    @ActivityRetainedScoped
    fun provideLeagueListRepo(api:ApiServices):LeagueRepo{
        return LeagueRepo(api)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideTeamListRepo(api:ApiServices):TeamRepo{
        return TeamRepo(api)
    }
}