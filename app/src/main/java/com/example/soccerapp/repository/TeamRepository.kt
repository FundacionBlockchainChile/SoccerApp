package com.example.soccerapp.repository

import android.util.Log
import com.example.soccerapp.model.Team
import com.example.soccerapp.model.TeamDetails
import com.example.soccerapp.network.ApiService
import com.example.soccerapp.database.TeamDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TeamRepository @Inject constructor(
    private val apiService: ApiService,
    private val teamDao: TeamDao
) {
    suspend fun getTeams(): Flow<List<Team>> = flow {
        val teams = apiService.getTeams()
        Log.d("Teams", "Teams fetched: ${teams}")
        teamDao.insertAll(teams)
        emit(teams)
    }

    // Adjust the function to emit TeamDetails instead of Team
    fun getTeamDetails(teamId: Int): Flow<TeamDetails> = flow {
        val teamDetails = apiService.getTeamDetails(teamId)
        emit(teamDetails)
    }

    suspend fun insertAll(teams: List<Team>) {
        try {
            teamDao.insertAll(teams)
        } catch (e: Exception) {
            Log.e("DatabaseError", "Error inserting teams: ${e.message}")
        }
    }
}
