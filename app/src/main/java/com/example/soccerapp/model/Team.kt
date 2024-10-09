// Team.kt
package com.example.soccerapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class Team(
    @PrimaryKey val id: Int,
    val nombre: String,
    val logo: String,
) {

}