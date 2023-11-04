package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "welcome") {
                composable("welcome") {
                    WelcomeScreen(navController = navController)
                }
                composable("list") {
                    val cities = listOf(
                        CityInfo("Yerevan", "Yerevan is the capital and largest city of Armenia. It is known for its rich history, beautiful architecture, and vibrant culture. Explore the ancient history of Yerevan and enjoy its lively atmosphere.", R.drawable.yerevan_image),
                        CityInfo("Paris", "Paris, the capital of France, is famous for its iconic landmarks, world-class art, and delicious cuisine. Experience the romance of Paris as you visit the Eiffel Tower, Louvre Museum, and indulge in French pastries.", R.drawable.paris_image),
                        CityInfo("Barcelona", "Barcelona is a vibrant city in Spain, famous for its unique architecture, stunning beaches, and lively nightlife. Discover the works of Antoni Gaudí, relax on the beautiful Mediterranean coast, and savor authentic tapas.", R.drawable.barcelona_image),
                        CityInfo("Amsterdam", "Amsterdam, the capital of the Netherlands, is known for its picturesque canals, historic houses, and cultural heritage. Explore the Van Gogh Museum, take a canal cruise, and experience the charm of Dutch culture.", R.drawable.amsterdam_image),
                        CityInfo("Tokyo", "Tokyo, the capital of Japan, is a bustling metropolis known for its modern skyscrapers, historic temples, and delicious street food. Experience the unique blend of traditional and contemporary culture in Tokyo.", R.drawable.tokyo_image),
                        CityInfo("Vienna", "Vienna, the capital of Austria, is famous for its classical music, historic architecture, and coffeehouse culture. Visit the Vienna State Opera, explore Schönbrunn Palace, and enjoy a slice of Sachertorte.", R.drawable.vienna_image),
                        CityInfo("Zurich", "Zurich is the largest city in Switzerland, known for its financial institutions, historic old town, and beautiful lake. Explore the charming streets of Zurich, go shopping on Bahnhofstrasse, and relax by Lake Zurich.", R.drawable.zurich_image),
                        CityInfo("Athens", "Athens is the capital of Greece and one of the world's oldest cities. Explore the historic ruins of the Acropolis, discover ancient Greek history, and enjoy Greek cuisine and hospitality.", R.drawable.athens_image)
                    )

                    CityListScreen(cities = cities, navController = navController)
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .scale(1.3f)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello!",
                fontSize = 36.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Welcome to the City Explorer",
                fontSize = 26.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(72.dp))
            Button(
                onClick = {
                    navController.navigate("list")
                },
                modifier = Modifier
                    .height(56.dp)
            ) {
                Text(
                    text = "Let's go!",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
}

data class CityInfo(val name: String, val description: String, val imageResId: Int)

@Composable
fun CityListScreen(cities: List<CityInfo>, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .height(40.dp)
        ) {
            Text(
                text = "Go Back",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp
            )
        }
        LazyColumn {
            items(cities) { city ->
                var isExpanded by remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            isExpanded = !isExpanded
                        }
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = city.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        if (isExpanded) {
                            Image(
                                painter = painterResource(id = city.imageResId),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )

                            Text(
                                text = city.description,
                                fontSize = 14.sp,
                                color = Color.Gray
                            )

                            Text(
                                text = "See Less",
                                color = Color.Blue,
                                modifier = Modifier.clickable {
                                    isExpanded = !isExpanded
                                }
                            )
                        } else {
                            Text(
                                text = "See More",
                                color = Color.Blue,
                                modifier = Modifier.clickable {
                                    isExpanded = !isExpanded
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}