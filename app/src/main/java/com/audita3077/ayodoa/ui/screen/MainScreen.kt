package com.audita3077.ayodoa.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.audita3077.ayodoa.navigation.Screen
import com.audita3077.ayodoa.util.ViewModelFactory
import com.audita3077.ayodoa.model.Doa
import com.audita3077.ayodoa.ui.theme.AyoDoaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val viewModel: MainViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
    val listDoa by viewModel.allDoa.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Ayo Doa") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.Form.route)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Tambah")
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ) {
            items(listDoa.size) { index ->
                DoaItem(doa = listDoa[index], onClick = {
                    navController.navigate(Screen.Detail.withId(listDoa[index].id))
                })
            }
        }
    }
}

@Composable
fun DoaItem(doa: Doa, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = doa.judul, style = MaterialTheme.typography.titleMedium)
            Text(text = doa.latin, style = MaterialTheme.typography.bodySmall, maxLines = 1)
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    AyoDoaTheme {
        MainScreen(rememberNavController())
    }
}
