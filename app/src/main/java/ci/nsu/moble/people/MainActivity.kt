package ci.nsu.moble.people

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ci.nsu.moble.people.ui.theme.PeopleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PeopleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ColorChangerScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ColorChangerScreen(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var buttonColor by remember { mutableStateOf(Color.Blue) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                buttonColor = getColorFromText(newText)
            },
            decorationBox = { innerTextField ->
                androidx.compose.material3.TextField(
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                        buttonColor = getColorFromText(newText)
                    },
                    label = { Text("Введите цвет (green, red, blue...)") },
                    modifier = Modifier.padding(8.dp)
                )
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                buttonColor = getColorFromText(text)
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text("Нажми меня!")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Попробуйте ввести: green, red, blue, yellow, black, white",
            color = Color.Gray
        )
    }
}

fun getColorFromText(text: String): Color {
    return when (text.lowercase().trim()) {
        "green" -> Color.Green
        "red" -> Color.Red
        "blue" -> Color.Blue
        "yellow" -> Color.Yellow
        "black" -> Color.Black
        "white" -> Color.White
        "cyan" -> Color.Cyan
        "magenta" -> Color.Magenta
        "gray" -> Color.Gray
        else -> Color.Blue
    }
}

@Preview(showBackground = true)
@Composable
fun ColorChangerPreview() {
    PeopleTheme {
        ColorChangerScreen(Modifier.padding(16.dp))
    }
}