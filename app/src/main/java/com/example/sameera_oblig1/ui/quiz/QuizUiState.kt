package com.example.sameera_oblig1.ui.quiz

import Question
data class QuizUiState(val question1: Question,val question2: Question,val question3: Question){
    var sporsmalListe:List<Question> =listOf(question1,question2,question3)
    var poeng=0
}

fun rettSvar(poeng: Int){
}

fun hentSposmal(){

}