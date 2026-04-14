
package com.example.hospitalmanagementsystem.ui.theme.screens.patient

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.hospitalmanagementsystem.data.PatientViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPatientScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var illness by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    val PatientViewModel: PatientViewModel = viewModel()
    val context = LocalContext.current


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Add Patient")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1565C0),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
        ) {

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                if(imageUri != null){
                    Image(painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }else{
                    Icon(Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp))
                }

            }
            Button(onClick = {launcher.launch("image/*")},
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Select Image")
            }
            OutlinedTextField(
                value = name,
                onValueChange = {name = it},
                label = {Text(text = "Enter Name")},
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = age,
                onValueChange = {age = it},
                label = {Text(text = "Enter Age")},
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = phone,
                onValueChange = {phone = it},
                label = {Text(text = "Enter Phone")},
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = illness,
                onValueChange = {illness= it},
                label = {Text(text = "Enter Illness")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    PatientViewModel.uploadPatient(imageUri,name,age,phone,illness,context,navController)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Patient")
            }
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddPatientScreenPreview() {
    AddPatientScreen(rememberNavController())

}