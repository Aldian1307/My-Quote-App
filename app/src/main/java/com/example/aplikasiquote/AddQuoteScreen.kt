package com.example.aplikasiquote

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aplikasiquote.FirebaseRepository
import com.example.aplikasiquote.Quote


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddQuoteScreen(navController: NavHostController) {
    var addQuote by remember { mutableStateOf("") }
    var book by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var page by remember { mutableStateOf("") }
    var context = LocalContext.current

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Tambah Kutipan") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )
        }
    ){ padding ->
        Column(modifier = Modifier.fillMaxSize()
            .background(Color.White).padding(padding)
            .padding(horizontal = 14.dp, vertical = 12.dp)
            .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = addQuote,
                onValueChange = {addQuote = it},
                label = { Text("Masukan Kutipan Favorit")},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = book,
                onValueChange = {book = it},
                label = { Text("Masukan Nama Buku")},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = author,
                onValueChange = {author = it},
                label = { Text("Masukan Nama Penulis")},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(12.dp))


            OutlinedTextField(
                value = page,
                onValueChange = { page = it},
                label = { Text("Masukan Nomor Halaman")},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                FirebaseRepository.addQuote(Quote("", addQuote, book, author, page))
                navController.popBackStack()
                Toast.makeText(context, "Kutipan Tersimpan", Toast.LENGTH_SHORT).show()
            }, modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan")
            }
        }
    }
}