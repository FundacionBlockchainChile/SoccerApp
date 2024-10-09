package com.example.soccerapp.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.soccerapp.model.Team
import com.example.soccerapp.ui.theme.CardBackground
import com.example.soccerapp.ui.theme.DividerColor
import com.example.soccerapp.ui.theme.Primary
import com.example.soccerapp.ui.theme.SoccerAppTheme
import com.example.soccerapp.ui.theme.SurfaceBackground
import com.example.soccerapp.ui.theme.TextPrimary
import com.example.soccerapp.viewmodel.TeamViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamListScreen(viewModel: TeamViewModel, navController: NavController) {
    val teams = viewModel.teams.collectAsState().value

    SoccerAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Football Teams", style = MaterialTheme.typography.titleMedium) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Primary,
                        titleContentColor = TextPrimary
                    )
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .background(SurfaceBackground)
            ) {
                items(teams) { team ->
                    TeamCard(team = team) {
                        navController.navigate("teamDetail/${team.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun TeamCard(team: Team, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter(team.logo),
                contentDescription = "Team Logo",
                modifier = Modifier
                    .size(60.dp)
                    .background(DividerColor, shape = MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth()
            ) {
                Text(
                    text = team.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    color = Primary
                )
            }
        }
    }
}

