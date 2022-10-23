package com.example.zadanie1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class PromptActivity : AppCompatActivity()
{
    var KEY_EXTRA_ANSWER_SHOWN: String = "com.example.zadanie1.answerShown"
    var correctAnswer: Boolean = false
    private lateinit var showCorrectAnswerButton: Button
    private lateinit var answerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prompt)
        showCorrectAnswerButton = findViewById(R.id.correct_answer_button)
        answerTextView = findViewById(R.id.help_text_view)

        showCorrectAnswerButton.setOnClickListener(View.OnClickListener() {

            fun onClick(v: View) {
                var answer: Int = if (correctAnswer) R.string.button_true else R.string.button_false
                answerTextView.setText(answer)
                setAnswerShownResult(true)
            }
        })

        correctAnswer = getIntent().getBooleanExtra(MainActivity().KEY_EXTRA_ANSWER, true)
    }

    private fun setAnswerShownResult(answerWasShown: Boolean) {
        var resultIntent: Intent = Intent()
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown)
        setResult(RESULT_OK, resultIntent)
    }
}