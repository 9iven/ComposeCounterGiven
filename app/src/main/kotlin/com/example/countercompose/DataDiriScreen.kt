package com.example.countercompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countercompose.ui.theme.CounterComposeTheme

@Composable
fun DataDiriScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DataDiriViewModel = viewModel()
) {
    val nama by viewModel.nama
    val umur by viewModel.umur

    DataDiriContent(
        nama = nama,
        umur = umur,
        onNamaChange = { viewModel.onNamaChange(it) },
        onUmurChange = { viewModel.onUmurChange(it) },
        onBack = onBack,
        modifier = modifier
    )
}

@Composable
fun DataDiriContent(
    nama: String,
    umur: String,
    onNamaChange: (String) -> Unit,
    onUmurChange: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Baris Header dengan Tombol Kembali dan Judul
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = "Kembali ke Counter",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Data Diri",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Form Input Nama
        OutlinedTextField(
            value = nama,
            onValueChange = onNamaChange,
            label = { Text("Nama Lengkap") },
            placeholder = { Text("Masukkan nama...") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Form Input Umur
        OutlinedTextField(
            value = umur,
            onValueChange = onUmurChange,
            label = { Text("Umur") },
            placeholder = { Text("Masukkan umur...") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Card Ringkasan Hasil Inputan
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF5F5F5)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Ringkasan:",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Nama saya \"$nama\" dan umur saya \"$umur\", Salam kenal!",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF212121),
                    lineHeight = 24.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Tombol Kembali di Bagian Bawah
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF673AB7)
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.width(ButtonDefaults.IconSpacing))
            Text("KEMBALI KE COUNTER")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DataDiriScreenPreview() {
    CounterComposeTheme {
        DataDiriContent(
            nama = "Given Gerald Chandra",
            umur = "20",
            onNamaChange = {},
            onUmurChange = {},
            onBack = {}
        )
    }
}
