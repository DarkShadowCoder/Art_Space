package com.example.devmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devmobile.ui.theme.DevMobileTheme


data class Item(val imageRes: Int, val title: String, val description: String)

class MainActivity : ComponentActivity() {
    private val items = listOf(
        Item(R.drawable.mona_lisa, "Mona Lisa", "Leonard de Vinci, Oeuvre d'art le plus reconue au monde"),
        Item(R.drawable.last_supper, "La Cène", "Leonard de Vinci, 1495-1498"),
        Item(R.drawable.starry_night, "Nuit étoilée", "Van Gogh, 1889, Musée d'art New York")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ArtSpacePreview()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(){

}


@Composable
fun ArtSpaceCard(items: List<Item>,
                 modifier: Modifier = Modifier){

    var currentIndex by remember { mutableStateOf(0) }

    val currentItem = items[currentIndex]

    var scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {

        Card(
            modifier = modifier
                .height(78.dp)
                .padding(5.dp)
                .fillMaxWidth(),
            border = BorderStroke(1.dp, Color.Gray),
            shape = RoundedCornerShape(16.dp),

        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = currentItem.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = currentItem.description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }

        }

        Image(painter = painterResource(id = currentItem.imageRes), contentDescription = currentItem.title, modifier = modifier
            .width(800.dp)
            .height(400.dp)
            .shadow(3.dp, shape = RoundedCornerShape(16.dp)))

        Row(
            modifier = modifier
                .height(76.dp)
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Button(
                modifier = modifier
                    .height(45.dp)
                    .width(125.dp),
                onClick = {currentIndex = if(currentIndex > 0) currentIndex -1 else items.size -1}
            ){
                Text(
                    text = "Previous",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(249, 250, 248, 255)
                )

            }

            Button(
                modifier = modifier
                    .height(45.dp)
                    .width(125.dp),
                onClick = {currentIndex = (currentIndex +1) % items.size}
            ){
                Text(
                    text = "Next",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(249, 252, 248, 255)
                )

            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview(){
    val items = listOf(
        Item(R.drawable.mona_lisa, "Mona Lisa", "Leonard de Vinci, Oeuvre d'art le plus reconue"),
        Item(R.drawable.last_supper, "La Cène", "Leonard de Vinci, 1495-1498"),
        Item(R.drawable.starry_night, "Nuit étoilée", "Van Gogh, 1889, Musée d'art New York")
    )
    DevMobileTheme {
        ArtSpaceCard(items)
    }
}