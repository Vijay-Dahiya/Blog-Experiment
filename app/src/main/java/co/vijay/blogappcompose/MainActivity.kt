package co.vijay.blogappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
    
    @Preview
    @Composable
    fun App() {
        Derived()
    }

    @Composable
    fun Derived() {
        val table = remember { mutableIntStateOf(5) }
        val idx = produceState(initialValue = 1) {
            repeat(9) {
                delay(1000)
                value += 1
            }
        }

        val message2 = remember {
            mutableStateOf("${table.intValue} * ${idx.value} = ${table.value * idx.value}")
        }
        val message = remember {
            derivedStateOf {
                "${table.intValue} * ${idx.value} = ${table.value * idx.value}"
            }
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Text(text = message.value, fontSize = 24.sp)
            Text(text = message2.value, fontSize = 26.sp)
            Text(text = "${table.intValue} * ${idx.value} = ${table.value * idx.value}", fontSize = 24.sp)
        }
    }
}