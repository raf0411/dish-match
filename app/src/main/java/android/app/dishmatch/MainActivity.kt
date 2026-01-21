package android.app.dishmatch

import android.app.dishmatch.ui.theme.DishMatchTheme
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Food(
    val name: String,
    val image: Painter? = null
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DishMatchTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Black
                ) { innerPadding ->
                    FoodsScreen(
                        modifier = Modifier
                            .padding(innerPadding),
                        foods = listOf(
                            Food(
                                name = "Fried Rice",
                                image = painterResource(R.drawable.fried_rice)
                            ),
                            Food(
                                name = "Hamburger",
                                image = painterResource(R.drawable.hamburger)
                            ),
                            Food(
                                name = "French Fries",
                                image = painterResource(R.drawable.french_fries)
                            ),
                            Food(
                                name = "Roasted Chicken",
                                image = painterResource(R.drawable.roasted_chicken)
                            ),
                            Food(
                                name = "Jacket Potato",
                                image = painterResource(R.drawable.jacket_potato)
                            )
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun FoodsScreen(
    modifier: Modifier = Modifier,
    foods: List<Food>
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            placeholder = {Text("Get me some food...")},
            value = "",
            onValueChange = {}
        )
        
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(foods) { food ->
                FoodCard(
                    food = food,
                    context = LocalContext.current
                )
            }
        }
    }

}

@Composable
fun FoodCard(
    modifier: Modifier = Modifier,
    food: Food,
    context: Context
) {
    ElevatedCard (
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 32.dp
        ),
        onClick = {
            Toast.makeText(context, "You clicked on ${food.name}!", Toast.LENGTH_SHORT).show()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            food.image?.let { painter ->
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    painter = painter,
                    contentDescription = food.name,
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = food.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true, name = "Foods List")
@Composable
fun FoodsScreenPreview() {
    val foods = listOf(
        Food(
            name = "Fried Rice",
            image = painterResource(R.drawable.fried_rice)
        ),
        Food(
            name = "Hamburger",
            image = painterResource(R.drawable.hamburger)
        ),
        Food(
            name = "French Fries",
            image = painterResource(R.drawable.french_fries)
        ),
        Food(
            name = "Roasted Chicken",
            image = painterResource(R.drawable.roasted_chicken)
        ),
        Food(
            name = "Jacket Potato",
            image = painterResource(R.drawable.jacket_potato)
        )
    )

    DishMatchTheme {
        FoodsScreen(foods = foods)
    }
}

@Preview(showBackground = true, name = "Single Food Card")
@Composable
fun FoodCardPreview() {
    DishMatchTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            FoodCard(
                food = Food(
                    name = "Fried Rice",
                    image = painterResource(R.drawable.fried_rice)
                ),
                context = LocalContext.current
            )
        }
    }
}