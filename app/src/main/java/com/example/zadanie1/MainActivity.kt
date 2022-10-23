package com.example.zadanie1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var KEY_CURRENT_INDEX: String = "currentIndex"
    private val QUIZ_TAG: String = "Tag quizu"
    private val REQUEST_CODE_PROMPT: Int = 0
    val KEY_EXTRA_ANSWER: String = "com.example.zadanie1.correctAnswer"
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var promptButton: Button
    private lateinit var questionTextView: TextView
    val questions = listOf(
        Question(R.string.q_amogus, true),
        Question(R.string.q_capital, false),
        Question(R.string.q_java, true),
        Question(R.string.q_murki, false),
        Question(R.string.q_vodka, false),
    )

    private var currentIndex: Int = 0
    private var answerWasShown: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onCreate.")
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX)
        }
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        promptButton = findViewById(R.id.prompt_button)
        questionTextView = findViewById(R.id.question_text)

        trueButton.setOnClickListener { checkAnswerCorrectness(true) }

        falseButton.setOnClickListener { checkAnswerCorrectness(false) }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questions.size
            answerWasShown = false
            setNextQuestion()
        }

        promptButton.setOnClickListener {
            var intent: Intent = Intent(
                this, PromptActivity::class.java
            )
            var correctAnswer: Boolean = questions[currentIndex].IsTrueAnswer()

            intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer)
            startActivity(intent)
            startActivityForResult(intent, REQUEST_CODE_PROMPT)
        }

        setNextQuestion()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) return
        if (resultCode == REQUEST_CODE_PROMPT) {
            if (data == null) return
            answerWasShown = data.getBooleanExtra(PromptActivity().KEY_EXTRA_ANSWER_SHOWN, false)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(QUIZ_TAG, "Wywołano onSaveInstanceState.")
        outState.putInt(KEY_CURRENT_INDEX, currentIndex)
    }

    override fun onStart() {
        super.onStart()
        Log.d("start", "start")
    }

    override fun onPause() {
        super.onPause()
        Log.d("pauza", "pauza")
    }

    override fun onResume() {
        super.onResume()
        Log.d("resume", "resume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("stop", "stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("destroy", "destroy")
    }

    private fun setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId())
    }

    private fun checkAnswerCorrectness(userAnswer: Boolean) {
        val correctAnswer: Boolean = questions[currentIndex].IsTrueAnswer()
        val resultMessageId: Int = if (answerWasShown) {
            R.string.answer_was_shown
        } else {
            if (userAnswer == correctAnswer) {
                R.string.correct_answer
            } else {
                R.string.incorrect_answer
            }
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show()
    }
}