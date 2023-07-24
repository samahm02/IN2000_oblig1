package com.example.sameera_oblig1

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.text.DecimalFormat



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UnitConverterScreen (modifier: Modifier, onNavigateToQuiz: () -> Unit) {
    var brukerInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0.0) }
    var output by remember { mutableStateOf("") }
    val focusManager= LocalFocusManager.current
    val tominput=stringResource(id = R.string.tomInput)
    val EnhetsArray: Array<String> = stringArrayResource(id = R.array.EnhetsArray)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }, modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            TextField(
                value = brukerInput,
                onValueChange = { brukerInput = it },
                label = {
                    Text(
                        text = stringResource(id = R.string.UnitTxtFelt),
                        modifier = Modifier
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(80.dp))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    value = selectedOptionText,
                    onValueChange = { selectedOptionText = it },
                    label = { Text("Velg Enhet") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                )
                val filteringOptions =
                    EnhetsArray.filter { it.contains(selectedOptionText, ignoreCase = true) }
                if (filteringOptions.isNotEmpty()) {
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        filteringOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    selectedOptionText = selectionOption
                                    expanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
            }
        }
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                focusManager.clearFocus()
                var inputDouble = brukerInput.toDoubleOrNull()



                if (inputDouble != null) {
                    if (selectedOptionText=="Fluid ounce"){
                        result=(inputDouble * 0.02957)
                    }else if (selectedOptionText=="Cup"){
                        result=(inputDouble * 0.23659)
                    }else if (selectedOptionText=="Gallon"){
                        result=(inputDouble * 3.78541)
                    }else if (selectedOptionText=="Hogshead"){
                        result=(inputDouble * 238.481)
                    } else{scope.launch {
                        snackbarHostState.showSnackbar(tominput)
                    }}
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar(tominput)
                    }
                }
                if (result!=0.0){
                    result = Math.round(result * 100.0) / 100.00
                    output = "$result L"
                }

            }) {
                Text(text = stringResource(id = R.string.svarKnapp))
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "$output", fontSize = 24.sp)


        }

        GaaTilQuizKapp(modifier, onNavigateToQuiz)
    }

    }




@Composable
fun GaaTilQuizKapp(modifier: Modifier, onNavigateToQuiz: () -> Unit){
    Column(
        modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(text = "Unitconverter")
        Button(onClick = { onNavigateToQuiz()}, modifier = Modifier.fillMaxWidth(),shape = RectangleShape) {
            Text(text = "Til Quiz")

        }
    }

}