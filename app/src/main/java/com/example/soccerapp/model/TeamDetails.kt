// TeamDetails.kt
package com.example.soccerapp.model

data class TeamDetails(
    val id: Int,
    val nombre: String,
    val logo: String,
    val estadio: String,
    val ciudad: String,
    val titulosNacionales: Int,
    val fundacion: Int,
    val titulosInternacionales: Int,
    val directorTecnico: String,
    val colores: List<String>,
    val entradas: Boolean
) {
}