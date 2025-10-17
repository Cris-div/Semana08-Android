package com.miempresa.smana08_unocc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miempresa.smana08_unocc.ui.theme.Smana08UnoccTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TecsupCampusApp()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TecsupCampusApp() {
    val navController = rememberNavController()
    var expanded by remember{ mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("TECSUP Campus")},
                actions ={
                    IconButton(onClick = {expanded=true}) {
                        Icon(Icons.Default.MoreVert , contentDescription = "Menú")
                    }
                    DropdownMenu(
                        expanded= expanded,
                        onDismissRequest = {expanded=false }
                    ) {
                        DropdownMenuItem(text = {Text("Inicio")}, onClick ={
                            navController.navigate("home"); expanded =false
                        } )
                        DropdownMenuItem(text = { Text("Cursos") }, onClick = {
                            navController.navigate("courses"); expanded = false
                        })
                        DropdownMenuItem(text = { Text("Instructores") }, onClick = {
                            navController.navigate("instructors"); expanded = false
                        })
                        DropdownMenuItem(text = { Text("Eventos") }, onClick = {
                            navController.navigate("events"); expanded = false
                        })
                        DropdownMenuItem(text = { Text("Acerca de") }, onClick = {
                            navController.navigate("about"); expanded = false
                        })
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(navController=navController, startDestination = "home" , modifier = Modifier.padding(innerPadding)){
        composable("home") {HomeScreen(navController)}
        composable("courses") {CoursesScreen(navController)}
        composable ("instructors"){InstructorsScreen(navController)}
        composable("events") {EventsScreen(navController)}
        composable("about") { AboutScreen(navController) }
    }  }
}

@Composable
fun HomeScreen(navController: NavController) {
    val items = listOf(
        Triple("Cursos",R.drawable.ic_launcher_background,"courses"),
        Triple("Instructores", R.drawable.ic_launcher_background , "instructors"),
        Triple("Eventos", R.drawable.ic_launcher_background , "events")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(items.size){i->
            val item = items[i]
            Card(
                onClick = {navController.navigate(item.third)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id=item.second),
                        contentDescription = item.first,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = item.first,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

@Composable
fun CoursesScreen(navController: NavController) {
    val courses = listOf(
        Triple("Kotlin", "UIs declarativas con Compose", R.drawable.ic_launcher_background),
        Triple("Python", "Análisis de datos", R.drawable.ic_launcher_background),
        Triple("SQL", "Gestión de bases de datos", R.drawable.ic_launcher_background)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(courses.size) { i ->
            val course = courses[i]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = course.third),
                        contentDescription = course.first,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = course.first,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = course.second,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InstructorsScreen(navController: NavController) {
    val instructors = listOf(
        Triple("Elliot", "Kotlin / Android", R.drawable.ic_launcher_background),
        Triple("Silvia", "Gestión de TI", R.drawable.ic_launcher_background),
        Triple("Farfán", "Bases de Datos", R.drawable.ic_launcher_background)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(instructors.size) { i ->
            val inst = instructors[i]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Imagen o avatar del instructor
                    Image(
                        painter = painterResource(id = inst.third),
                        contentDescription = inst.first,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    // Información textual
                    Column {
                        Text(
                            text = inst.first,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = inst.second,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EventsScreen(navController: NavController) {
    val events = listOf(
        Triple("15 Oct", "Feria de Proyectos", "Auditorio A"),
        Triple("20 Oct", "Charla de Innovación", "Sala B"),
        Triple("25 Oct", "Hackathon TECSUP", "Laboratorio 3")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(events.size) { i ->
            val event = events[i]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = " ${event.first}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = " ${event.second}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = " ${event.third}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun AboutScreen(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Aplicación: TECSUP Campus",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Versión: 1.0",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Autor: Curso TECSUP – Jetpack Compose",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}