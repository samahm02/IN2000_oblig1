package com.example.sameera_oblig1

import android.graphics.drawable.shapes.Shape
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat

@Composable
fun PalindromeScreen (modifier: Modifier, onNavigateToUnitConverter: () -> Unit) {
    var brukerInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val svarJa = stringResource(id = R.string.erPalindrome)
    val svarNei = stringResource(id = R.string.erIkkePalindrome)
    val empty = stringResource(id = R.string.tomInput)
    val focusManager= LocalFocusManager.current

    Column(
        modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        TextField(
            value = brukerInput,
            onValueChange = { brukerInput=it },
            label= { Text(text = stringResource(id = R.string.palindromeTxtFelt), modifier = Modifier)},

            singleLine = true
        )
        Spacer(modifier = Modifier.height(80.dp))
        Button(onClick = {
            focusManager.clearFocus()
            result = if (brukerInput == "" || brukerInput == " ") {
                "$empty"
            } else if (brukerInput.lowercase().reversed() == brukerInput.lowercase()) {
                "$brukerInput $svarJa"
            } else {
                "$brukerInput $svarNei"
            }
            brukerInput = ""
        }) {
            Text(text = stringResource(id = R.string.svarKnapp))
        }
    }
    Column(modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "$result", fontSize = 24.sp)
    }
    GaaTilUnitKapp(modifier, onNavigateToUnitConverter)

}
        




@Composable
fun GaaTilUnitKapp(modifier: Modifier, onNavigateToUnitConverter: () -> Unit){
    Column(
        modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(text = "Palindrome")
        Button(onClick = {onNavigateToUnitConverter()}, modifier = Modifier.fillMaxWidth(),shape = RectangleShape) {
            Text(text = "Til Unitconverter")

        }
    }

}






