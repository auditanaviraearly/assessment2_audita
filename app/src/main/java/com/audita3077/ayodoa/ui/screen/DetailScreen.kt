package com.audita3077.ayodoa.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.audita3077.ayodoa.model.Doa
import com.audita3077.ayodoa.ui.theme.AyoDoaTheme
import com.audita3077.ayodoa.util.ViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    id: Int
) {
    val viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))

    // Memanggil loadDoaById saat layar ditampilkan
    LaunchedEffect(id) {
        viewModel.loadDoaById(id)
    }

    // Menggunakan collectAsState untuk mengamati perubahan pada StateFlow
    val doa by viewModel.doa.collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Doa") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Navigating to the edit screen, passing the ID
                    navController.navigate("edit_screen/${doa?.id}")
                }
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Doa")
            }
        }
    ) { padding ->
        if (doa == null) {
            // Loading indicator if doa is null
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(text = "Judul: ${doa?.judul}", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Doa (Arab): ${doa?.arab}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Doa (Latin): ${doa?.latin}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Terjemahan: ${doa?.terjemahan}", style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(24.dp))

                // Button for deleting Doa
                Button(
                    onClick = {
                        doa?.let {
                            viewModel.deleteDoa(it)
                            navController.popBackStack()
//                            Toast.makeText(LocalContext.current, "Doa deleted", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
                ) {
                    Text(text = "Delete Doa")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    AyoDoaTheme {
        val sampleDoa = Doa(
            id = 1,
            judul = "Doa Minta Rezeki",
            arab = "اللّهُمّ رَبِّنَا عَطِّنَا",
            latin = "Allahumma rabbana atina",
            terjemahan = "Ya Allah, berikan kami rezeki."
        )
        DetailScreen(navController = rememberNavController(), id = sampleDoa.id)
    }
}

