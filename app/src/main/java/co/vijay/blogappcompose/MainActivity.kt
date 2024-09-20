package co.vijay.blogappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
    
    @Preview
    @Composable
    fun App() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "login") {
            composable("registration") {
                RegistrationScreen(navController) { email ->
                    navController.navigate("main/${email}")
                }
            }

            composable("login") {
                LoginScreen(navController)
            }

            composable("main/{email}", arguments = listOf(
                navArgument("email"){
                    type = NavType.StringType
                }
            )) {
                val email = it.arguments?.getString("email")
                MainScreen(navController, email)
            }
        }
    }


    @Composable
    fun RegistrationScreen(navController: NavHostController, onClick: (email : String) -> Unit) {
        val email = "vijaydhiayno.1@gmail.com"
        Column {
            Text(text = "Registration Screen", style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.clickable {
                    navController.navigate("main/${email}")
                }
            )
            Text(text = "Registration Screen 2nd trick", style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.clickable {
                    onClick(email)
                }
            )
        }

    }

    @Composable
    fun LoginScreen(navController: NavHostController) {
        Text(text = "Login Screen", style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.clickable {
                navController.navigate("registration")
            })
    }


    @Composable
    fun MainScreen(navController: NavHostController, email: String?) {
        Text(text = "Main Screen -> $email", style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.clickable {
                navController.navigate("login")
            })
    }

}