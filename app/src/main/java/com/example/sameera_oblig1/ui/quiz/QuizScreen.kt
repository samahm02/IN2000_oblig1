package com.example.sameera_oblig1

import Question
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sameera_oblig1.ui.quiz.QuizUiState


@Composable
fun QuizScreen (modifier: Modifier,onNavigateToQuiz: () -> Unit) {
    var indeks by remember{mutableStateOf(0)}
    var antallRiktige by remember{mutableStateOf(0)}
    var test by remember{mutableStateOf("")}
    var enabledState by remember {
        mutableStateOf(true)
    }

    val styrer = QuizUiState(
        Question("Er Ronaldo the GOAT?", false),
        Question("Vant Frankrike fotball vm 2022?", false),
        Question("Er Messi the GOAT?", true)
    )
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
        var Sporsmal = styrer.sporsmalListe[indeks].sporsmal
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "$Sporsmal",fontSize = 24.sp)
    }


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()) {
            Button(onClick = {
                if (indeks < 2) {
                    if (styrer.sporsmalListe[indeks].SannUsann) {
                        antallRiktige += 1
                        test = "Poeng: $antallRiktige"
                    }
                    indeks++
                } else {
                    if (styrer.sporsmalListe[indeks].SannUsann) {
                        antallRiktige += 1
                    }
                    test = "Poeng: $antallRiktige/3"
                    enabledState=false
                }
                if(!styrer.sporsmalListe[indeks].SannUsann){
                    if (indeks==2) {
                        "Poeng: $antallRiktige/3"
                    }
                    else{
                        test = "Poeng: $antallRiktige"
                    }
                }
            }, colors = ButtonDefaults.buttonColors(Color.Green),enabled = enabledState,
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp,),shape = RectangleShape) {
                Text(text = "Fakta")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                if (indeks < 2) {
                    if (!styrer.sporsmalListe[indeks].SannUsann) {
                        antallRiktige += 1
                        test = "Poeng: $antallRiktige"
                    }
                    indeks++
                } else {
                    if (!styrer.sporsmalListe[indeks].SannUsann) {
                        antallRiktige += 1
                    }
                    test = "Poeng: $antallRiktige/3"
                    enabledState=false
                }
                if(styrer.sporsmalListe[indeks].SannUsann){
                    if (indeks==2) {
                        "Poeng: $antallRiktige/3"
                    }
                    else{
                        test = "Poeng: $antallRiktige"
                    }
                }
            }, colors = ButtonDefaults.buttonColors(Color.Red), enabled = enabledState,
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp,),shape = RectangleShape) {
                Text(text = "Fleip")
            }


        }

    }
    Column(  modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.height(400.dp))
        Text(text = test,fontSize = 24.sp)
    }

    QuizResetKapp(modifier, onNavigateToQuiz)


}




@Composable
fun QuizResetKapp(modifier: Modifier, onNavigateToQuiz: () -> Unit){
    Column(
        modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(onClick = { onNavigateToQuiz()}, modifier = Modifier.fillMaxWidth(),shape = RectangleShape) {
            Text(text = "Restart Quiz")

        }
    }

}