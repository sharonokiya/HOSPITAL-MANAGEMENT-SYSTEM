package com.example.hospitalmanagementsystem.ui.theme.screens.register

import android.R.attr.phoneNumber
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.data.AuthViewModel

@Composable
fun RegisterScreen(navbarController: NavHostController) {
    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current

    RegisterContent(
        onSignup = { username, email, password, confirmPassword ->
            authViewModel.signup(
                username = username,
                email = email,
                password = password,
                confirmpassword = confirmPassword,
                navController = navbarController,
                context = context
            )
        }
    )
}

@Composable
fun RegisterContent(
    onSignup: (String, String, String, String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    Column(

                                    modifier = Modifier
                                    .fillMaxSize()
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            Color(0xFF0F0C29),
                                            Color(0xFF302B63),
                                            Color(0xFF24243E),
                                            Color(0xFF7B2FF7),
                                            Color(0xFFFD5E53),
                                            Color.White
                                        ),
                                        center = Offset(400f, 400f),
                                        radius = 700f
                                    )
                                )
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.aga),
                                contentDescription = "Logo",
                                modifier = Modifier
                                    .size(140.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.White, CircleShape)
                                    .shadow(4.dp, CircleShape)
                            )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Enter Username") },
            placeholder = {Text(text = "Please enter username")},
            leadingIcon = { Icon(Icons.Default.Person,contentDescription = null) }
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Enter Email") },
            placeholder = {Text(text = "Please enter email")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon"
                )
            }

        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text(text = "Enter phoneNumber") },
            placeholder = {Text(text = "Please enter phoneNumber")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null
                )
            }


        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Enter Password") },
            placeholder = {Text(text = "please Password")},
            leadingIcon = {Icon(Icons.Default.Lock,contentDescription = null)}

        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = "Confirm Password") },
            placeholder = {Text(text ="confirm password")},
            leadingIcon = {Icon(Icons.Default.Check,contentDescription = null)}
        )




        Button(
            onClick = {
                onSignup(username, email, password, confirmPassword)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Create Account", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }


    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview(){
    RegisterContent(onSignup = { _, _, _, _ -> })
}