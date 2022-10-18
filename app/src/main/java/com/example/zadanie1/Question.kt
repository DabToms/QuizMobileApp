package com.example.zadanie1

class Question(questionId: Int, trueAnswer: Boolean) {
    private val getQuestionId = questionId
    private val trueAnswer = trueAnswer

    public fun IsTrueAnswer(): Boolean {
        return trueAnswer
    }

    public fun getQuestionId(): Int {
        return getQuestionId
    }
}