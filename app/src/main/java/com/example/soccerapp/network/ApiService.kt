package com.example.soccerapp.network

import com.example.soccerapp.model.Team
import com.example.soccerapp.model.TeamDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("equipos")
    suspend fun getTeams(): List<Team>

    @GET("equipos/{id}")
    suspend fun getTeamDetails(@Path("id") teamId: Int): TeamDetails
}
