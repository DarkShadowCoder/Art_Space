package oldPackage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devmobile.Item
import com.example.devmobile.R
import com.example.devmobile.ui.theme.DevMobileTheme


@Composable
fun ArtSpaceCard(items: List<Item>,
                 modifier: Modifier = Modifier){
    // Varible de gestion des état
    var currentIndex by remember { mutableStateOf(0) }

    val currentItem = items[currentIndex]

    // Variable permettant de controller l'etat du scroll
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {
        Box (
            modifier = modifier
                .height(78.dp)
                .shadow(2.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,

            ) {
            Text(text = "Art Space (${currentIndex+1}/4)",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold)
        }

        Column(modifier = modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Image(
                painter = painterResource(id = currentItem.imageRes),
                contentDescription = currentItem.title,
                modifier = modifier
                    .width(800.dp)
                    .height(400.dp)
                    .padding(5.dp)
                    .shadow(3.dp, shape = RoundedCornerShape(16.dp)),
                alignment = Alignment.Center,
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = modifier.height(45.dp).fillMaxWidth())
            Card(
                modifier = modifier
                    .height(98.dp)
                    .padding(5.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = currentItem.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier.padding(vertical = 5.dp)
                    )
                    Text(
                        text = currentItem.description,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }

        Row(
            modifier = modifier
                .height(76.dp)
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                modifier = modifier
                    .height(45.dp)
                    .width(145.dp),
                onClick = {
                    currentIndex = if (currentIndex > 0) currentIndex - 1 else items.size - 1
                }
            ) {
                Text(
                    text = "Previous",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(255, 255, 245, 255)
                )

            }

            Button(
                modifier = modifier
                    .height(45.dp)
                    .width(145.dp),
                onClick = { currentIndex = (currentIndex + 1) % items.size }
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(255, 255, 255, 255)
                )

            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview(){
    // Liste des elements à afficher à chaque changement d'états
    val items = listOf(
        Item(R.drawable.reunification, "Reunification", "Celebre monument situé à Yaoundé"),
        Item(R.drawable.yaounde_cameroon, "Monument du Cameroun", "Sous le nom de ' J'aime mon pays' "),
        Item(R.drawable.pagode, "Pagoda", "Monument celebre de Thailand"),
        Item(R.drawable.chute_melong, "Chute de Melong", "Celebre chute de la ville de Melong Cameroun")
    )

    DevMobileTheme {
        ArtSpaceCard(items)
    }
}