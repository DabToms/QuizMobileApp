package com.example.zadanie1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class PromptActivity : AppCompatActivity()
{
    companion object{
        var KEY_EXTRA_ANSWER_SHOWN: String = "com.example.zadanie1.MainActivity.answerShown"
    }
    var correctAnswer: Boolean = false
    private lateinit var showCorrectAnswerButton: Button
    private lateinit var answerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prompt)

        correctAnswer = intent.getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true)

        showCorrectAnswerButton = findViewById(R.id.correct_answer_button)
        answerTextView = findViewById(R.id.help_text_view)

        showCorrectAnswerButton.setOnClickListener {
            var answer: Int = if (correctAnswer) R.string.button_true else R.string.button_false
            answerTextView.setText(answer)
            setAnswerShownResult(true)
        }


    }

    private fun setAnswerShownResult(answerWasShown: Boolean) {
        var resultIntent: Intent = Intent()
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown)
        setResult(RESULT_OK, resultIntent)
    }
}