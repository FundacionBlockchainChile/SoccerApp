import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.soccerapp.viewmodel.TeamViewModel
import com.example.soccerapp.ui.theme.Primary
import com.example.soccerapp.ui.theme.SurfaceBackground
import com.example.soccerapp.ui.theme.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailScreen(teamId: Int, viewModel: TeamViewModel, navController: NavController) {
    val context = LocalContext.current // Obtén el contexto
    LaunchedEffect(teamId) {
        viewModel.loadTeamDetails(teamId)
    }

    val teamDetails = viewModel.teamDetails.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(teamDetails?.nombre ?: "Loading...", style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Go back")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Primary,
                    titleContentColor = TextPrimary
                )
            )
        }
    ) { padding ->
        teamDetails?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(SurfaceBackground),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = rememberImagePainter(it.logo),
                    contentDescription = "Team Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text("Nombre: ${it.nombre}", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 16.dp))
                Text("Estadio: ${it.estadio}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 16.dp))
                Text("Ciudad: ${it.ciudad}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 16.dp))
                Text("Títulos Nacionales: ${it.titulosNacionales}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 16.dp))
                Text("Fundación: ${it.fundacion}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 16.dp))
                Text("Títulos Internacionales: ${it.titulosInternacionales}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 16.dp))
                Text("Director Técnico: ${it.directorTecnico}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 16.dp))
                Text("Colores: ${it.colores.joinToString()}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 16.dp))
                Text("Entradas Disponibles: ${if (it.entradas) "Sí" else "No"}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(horizontal = 16.dp))

                // Botón para enviar correo
                Button(
                    onClick = { sendEmail(context, it.nombre) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(text = "Solicitar información de entradas")
                }
            }
        } ?: Text(
            text = "Cargando detalles del equipo...",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

fun sendEmail(context: android.content.Context, teamName: String) {
    val emailIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_EMAIL, arrayOf("infofutbol@gmail.com")) // Destinatario
        putExtra(Intent.EXTRA_SUBJECT, "Formulario de Contacto info $teamName") // Asunto
        putExtra(Intent.EXTRA_TEXT, "Información de disponibilidad de entradas para el próximo partido: $teamName") // Cuerpo del mensaje
    }

    try {
        // Verificamos si hay una aplicación de correo que pueda manejar el Intent
        context.startActivity(Intent.createChooser(emailIntent, "Enviar correo"))
    } catch (e: Exception) {
        Log.e("EmailError", "Error al enviar correo: ${e.message}")
    }
}
