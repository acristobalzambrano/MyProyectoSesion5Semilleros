package com.uncp.myproyectosesion5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uncp.myproyectosesion5.ui.theme.CustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column {
        Greeting()
        Operation()
    }
}

@Composable
fun Greeting() {
    var text by remember {
        mutableStateOf("")
    }
    var isVisible by remember {
        mutableStateOf(false)
    }
    Column {
        Text(
            text = "Ingrese su nombre:"
        )
        OutlinedTextField(value = text, onValueChange = {
            text = it
            isVisible = false
        })
        Button(onClick = {
            isVisible = true
        }) {
            Text(text = "Guardar")
        }
        if (isVisible && text.isNotEmpty()) {
            Text(
                text = "Hola $text!"
            )
        }
    }
}

@Composable
fun Operation() {
    var number1 by remember {
        mutableStateOf("")
    }
    var number2 by remember {
        mutableStateOf("")
    }
    var response by remember {
        mutableIntStateOf(0)
    }
    Column {
        Row {
            Text(
                text = "Ingrese su primer numero:"
            )
            OutlinedTextField(
                value = number1,
                onValueChange = {
                    number1 = it
                },
                label = {
                    Text(text = "Numero 1")
                }
            )
        }
        Row {
            Text(
                text = "Ingrese su segundo numero:"
            )
            OutlinedTextField(
                value = number2,
                onValueChange = {
                    number2 = it
                },
                label = {
                    Text(text = "Numero 2")
                }
            )
        }
        Button(onClick = {
            response = calculateTwoNumbers(number1, number2)
        }) {
            Text(text = "Calcular")
        }
        Text(text = "Respuesta: $response")
    }
}

fun calculateTwoNumbers(number1: String, number2: String): Int {
    return try {
        (number1.toInt() + number2.toInt()) * (number1.toInt() - number2.toInt())
    } catch (e: Exception) {
        e.printStackTrace()
        0
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CustomTheme {
        Operation()
    }
}