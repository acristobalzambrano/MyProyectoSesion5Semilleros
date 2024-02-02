package com.uncp.myproyectosesion5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.uncp.myproyectosesion5.ui.theme.CustomTheme

class MainActivity : ComponentActivity() {
    private val myName = "Antony"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme {
                MainScreen(myName)
            }
        }
    }
}

@Composable
fun MainScreen(myName: String) {
    Column {
        Greeting(myName)
        Operation()
    }
}

@Composable
fun Greeting(name: String) {
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
    var respuesta by remember {
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
            respuesta = calcularDosNumeros(number1, number2)
        }) {
            Text(text = "Calcular")
        }
        Text(text = "Respuesta: $respuesta")
    }
}

fun calcularDosNumeros(number1: String, number2: String): Int {
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
    val myName = "Antony"
    CustomTheme {
        Greeting(myName)
    }
}