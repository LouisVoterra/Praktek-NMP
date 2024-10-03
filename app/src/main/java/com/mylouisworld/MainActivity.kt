package com.mylouisworld
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mylouisworld.databinding.ActivityMainBinding


/*
class MainActivity extends AppCompatActivity() which it has some features inside
onCreate for Define an UI
enableEdgeToEdge() to display  status & navigation bar
with viewBinding activated, findViewById is useless.
*/
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n")


    var currentQuestion = 0
    var score = 0
    var wrongAnswerCount = 0


    private fun displayQuestions() {
        binding.txtQuestion.text = QuestionData.questions[currentQuestion].question
        QuestionData.questions.shuffle()
    }

    private fun nextQuestion() {
        currentQuestion++
        displayQuestions()
    }

    fun increaseScore(){
        score++
        Toast.makeText(this, "Score: $score", Toast.LENGTH_SHORT).show()
    }

    private fun checkForWrongAnswers() {
        if (wrongAnswerCount >= 3) {
            Toast.makeText(this, "You answered incorrectly 3 times!", Toast.LENGTH_SHORT).show()
            wrongAnswerCount = 0 // Reset counter after showing the message
        }
    }



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) { //this code make sure logic from AppCompatActivity run correctly
        super.onCreate(savedInstanceState) //savedInstanceState to record last position
        //setContentView(R.layout.activity_main) //to display XML based UI
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playerName = intent.getStringExtra(IntroActivity.PLAYER_NAME)
        binding.txtWelcome.text = playerName

        displayQuestions()

        binding.btnTrue.setOnClickListener {
            if (QuestionData.questions[currentQuestion].answer) {
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                increaseScore()  // Increase score for a correct answer
                wrongAnswerCount = 0 // Reset wrong answer count
            } else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
                wrongAnswerCount++ // Increment wrong answer count
                checkForWrongAnswers() // Check if wrong answers reached 3
            }
            nextQuestion()
        }

        binding.btnFalse.setOnClickListener {
            if (!QuestionData.questions[currentQuestion].answer) {
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                increaseScore()  // Increase score for a correct answer
                wrongAnswerCount = 0 // Reset wrong answer count
            } else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
                wrongAnswerCount++ // Increment wrong answer count
                checkForWrongAnswers() // Check if wrong answers reached 3
            }
            nextQuestion()
        }



    }



        /*
            binding.btnTrue.setOnClickListener {
                 binding.txtQuestion.text = "SpongeBob Quiz"
        }
        val txtQuestion =  findViewById<TextView>(R.id.txtQuestion)
        val btn = findViewById<Button>(R.id.btnTrue)
        variable txtQuestion used for search ID textView, then store to txtQuestion
        then di txtQuestion.text for change value hello world to Spongebob Quiz
        */
}


