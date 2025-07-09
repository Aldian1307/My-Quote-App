package com.example.aplikasiquote

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditQuoteScreen(navController: NavHostController) {
    val quote = QuoteState.selectedQuote
    if (quote == null) {
        Text("No quote selected.")
        return
    }

    var editQuote by remember { mutableStateOf(quote.quote) }
    var book by remember { mutableStateOf(quote.book) }
    var author by remember { mutableStateOf(quote.author) }
    var page by remember { mutableStateOf(quote.page) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ubah Kutipan") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .padding(horizontal = 14.dp, vertical = 12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = editQuote,
                onValueChange = { editQuote = it },
                label = { Text("Edit Kutipan") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = book,
                onValueChange = { book = it },
                label = { Text("Masukan Nama Buku") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Masukan Nama Penulis") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = page,
                onValueChange = { page = it },
                label = { Text("Masukan Nomor Halaman") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    FirebaseRepository.updateQuote(
                        Quote(quote.id, editQuote, book, author, page)
                    )
                    navController.popBackStack()
                    Toast.makeText(context, "Kutipan Di Perbarui", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Perbarui")
            }
        }
    }
}
