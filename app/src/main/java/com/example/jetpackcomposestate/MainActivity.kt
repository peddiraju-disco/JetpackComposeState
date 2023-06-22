package com.example.jetpackcomposestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposestate.ui.theme.JetpackComposeStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeStateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    //HelloContent()
                    HelloContentScreen()
                    //HelloContentViewModel()
                }
            }
        }
    }
}

class HeroViewModel: ViewModel() {

    private val _name: MutableLiveData<String> = MutableLiveData("")
    val name: LiveData<String> = _name

    fun onNameChange(newName:String){
        _name.value=newName
    }
}

/*@Composable
fun HelloContentViewModel(heroViewModel: HeroViewModel = viewModel()){
    var name by rememberSaveable { mutableStateOf("") }
    HelloContentRememberSavable(name=name, onNameChange = { name = it })
}*/


@Composable
fun HelloContentScreen(){
    var name by rememberSaveable { mutableStateOf("") }
    HelloContentRememberSavable(name=name, onNameChange = { name = it })
}

//rememberSaveable === state management
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloContentRememberSavable(name:String, onNameChange:(String) -> Unit) {
    Column() {
        if(name.isNotEmpty()) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        OutlinedTextField(
            value = name,
            modifier = Modifier.padding(10.dp),
            onValueChange = onNameChange,
            label = { Text("Enter text here") }
        )
    }
}

//remember === state management
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloContentRemember() {
    Column() {
        var name by remember { mutableStateOf("") }
        if(name.isNotEmpty()) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        OutlinedTextField(
            value = name,
            modifier = Modifier.padding(10.dp),
            onValueChange = { name = it},
            label = { Text("Enter text here") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeStateTheme {
        //Greeting("Android")
    }
}