package denis.rinfret.exemplesentrees

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import denis.rinfret.exemplesentrees.ui.theme.ExemplesEntreesTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExemplesEntreesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TextLengthCounter(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var length by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Entrez du texte") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { length = text.length }
        ) {
            Text("Calculer la longueur")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Longueur du texte : $length caractères")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitSelector(modifier: Modifier = Modifier) {
    val fruits = listOf("Pomme", "Banane", "Orange", "Fraise", "Kiwi")
    var expanded by remember { mutableStateOf(false) }
    var selectedFruit by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedFruit,
                onValueChange = {},
                readOnly = true,
                label = { Text("Choisissez un fruit") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                fruits.forEach { fruit ->
                    DropdownMenuItem(
                        text = { Text(fruit) },
                        onClick = {
                            selectedFruit = fruit
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedFruit.isNotEmpty()) {
            Text("Fruit sélectionné : $selectedFruit")
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TextLengthCounter()
        Spacer(modifier = Modifier.height(100.dp))
        FruitSelector()
    }
}


@Preview(showBackground = true)
@Composable
fun TextLengthCounterPreview() {
    ExemplesEntreesTheme {
        TextLengthCounter()
    }
}

@Preview(showBackground = true)
@Composable
fun FruitSelectorPreview() {
    ExemplesEntreesTheme {
        FruitSelector()
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ExemplesEntreesTheme {
        App()
    }
}