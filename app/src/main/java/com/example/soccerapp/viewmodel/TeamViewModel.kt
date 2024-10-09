package com.example.soccerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soccerapp.model.Team
import com.example.soccerapp.model.TeamDetails
import com.example.soccerapp.repository.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val repository: TeamRepository
) : ViewModel() {
    private val _teams = MutableStateFlow<List<Team>>(emptyList())
    val teams: StateFlow<List<Team>> = _teams.asStateFlow()

    init {
        loadTeams()
    }

    private fun loadTeams() {
        viewModelScope.launch {
            repository.getTeams().collect { teamsList ->
                _teams.value = teamsList
            }
        }
    }

    private val _teamDetails = MutableStateFlow<TeamDetails?>(null)
    val teamDetails = _teamDetails.asStateFlow()

    fun loadTeamDetails(teamId: Int) {
        viewModelScope.launch {
            val details = repository.getTeamDetails(teamId)
            details.collect {
                _teamDetails.value = it
            }
        }
    }
}
