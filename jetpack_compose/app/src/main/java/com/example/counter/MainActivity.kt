package com.example.counter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.counter.ui.theme.CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var count by remember { mutableStateOf(0) }
    val increment = { count = count + 1 }
    Column() {
        Text("$count")
        Button(onClick = increment) {
            Text(text = "+")
        }
        Child(text = "toChild", feedback = { Log.d("debug", it) } )
    }
}

@Composable
fun Child(text: String, feedback: (v: String) -> Unit) {
    Column() {
        Text("$text")
        Button(onClick = { feedback("toParent") }) {
            Text(text = "+")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CounterTheme {
        MyApp()
    }
}