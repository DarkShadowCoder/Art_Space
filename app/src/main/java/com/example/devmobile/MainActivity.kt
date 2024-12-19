package com.example.devmobile


import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.*



// Ecran de l'application mobile
@Composable
fun AppScreen(viewModel: MainViewModel = viewModel()) {
    val searchResults by remember { mutableStateOf(viewModel.searchResults) }
    val favorites by remember { mutableStateOf(viewModel.favorites) }

    Column(
        modifier = Modifier
            .padding(0.dp)
        ,
        verticalArrangement = Arrangement.SpaceBetween,
    )
    {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color(52, 152, 235))
                .padding(0.dp),
            shadowElevation = 4.dp,
            ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color(52, 152, 235))
            ) {
                Text(text = "Flight Search",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Surface(
                modifier = Modifier
                    .padding(horizontal = 2.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                shadowElevation = 4.dp,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 5.dp)
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                    BasicTextField(
                        value = viewModel.query,
                        onValueChange = {
                            viewModel.query = it
                            viewModel.searchAirports()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(/*onSearch = { viewModel.searchAirports() }*/ onSearch = { Log.d("Clic d'users", "Bonjour Monde")}),
                        decorationBox = { innerTextField ->
                            Box(
                                Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            ) {
                                if (viewModel.query.isEmpty()) {
                                    Text("Rechercher un Vol",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = Color.Gray,
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(
                visible = viewModel.query.isNotEmpty(),
                enter = fadeIn(),       // Animation fade in lors de l'apparition
                exit = fadeOut()        // Animation fade out lors de la disparition
            ) {
                Text("Vols disponibles :", style = MaterialTheme.typography.titleMedium)
                searchResults.forEach { airport ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { },
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(airport.name, style = MaterialTheme.typography.titleSmall)
                                Text(
                                    airport.iata_code,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }
                            // Lorsque l'on appuie sur le boutton le vol  est ajoutée aux favoris
                            Button(onClick = {
                                viewModel.addFavorite("departure_code", airport.iata_code)
                            }) {
                                Icon(
                                    Icons.Default.Favorite,
                                    contentDescription = "Add to Favorites"
                                )
                            }
                        }
                    }
                }
            }

            // Animation lors de la saisie dans la barre de recherche
            AnimatedVisibility(
                visible = viewModel.query.isEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column {
                    Text("Favoris :", style = MaterialTheme.typography.titleMedium)
                    favorites.forEach { favorite ->
                        Text(
                            "Comming from \n ${favorite.departure_code} \n to \n ${favorite.destination_code}",
                            modifier = Modifier.padding(vertical = 4.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.saveQueryState()
        }
    }
}

class MainViewModelFactory(private val dao: AirportDao, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(dao, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun MyApp(db: AppDatabase, context: Context) {
    val dao = db.airportDao()
    val factory = MainViewModelFactory(dao, context)
    val viewModel: MainViewModel = viewModel(factory = factory)

    AppScreen(viewModel = viewModel)
}

@Preview(showBackground = true)
@Composable
fun PreviewAppScreen() {
    val context = LocalContext.current
    MyApp(Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "flights"
    ).createFromAsset("flight_search.db")
    .build(), context)
}
