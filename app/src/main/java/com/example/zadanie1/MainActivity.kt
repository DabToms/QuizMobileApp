package com.example.zadanie1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit  var trueButton: Button
    private lateinit  var falseButton: Button
    private lateinit  var nextButton: Button
    private lateinit  var questionTextView: TextView
    private val questions = listOf(
        Question(R.string.q_amogus, true),
        Question(R.string.q_capital, false),
        Question(R.string.q_java, true),
        Question(R.string.q_murki, false),
        Question(R.string.q_vodka, false),
    )

    private var currentIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text)

        trueButton.setOnClickListener{checkAnswerCorrectness(true) }

        falseButton.setOnClickListener { checkAnswerCorrectness(false) }

        nextButton.setOnClickListener{
                currentIndex = (currentIndex + 1) % questions.size
                setNextQuestion()
            }
        
        setNextQuestion()
    }

    private fun setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId())
    }

    private fun checkAnswerCorrectness(userAnswer: Boolean) {
        val correctAnswer: Boolean = questions[currentIndex].IsTrueAnswer()
        val resultMessageId: Int = if (userAnswer == correctAnswer) {
            R.string.correct_answer
        } else {
            R.string.incorrect_answer
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show()
    }
}