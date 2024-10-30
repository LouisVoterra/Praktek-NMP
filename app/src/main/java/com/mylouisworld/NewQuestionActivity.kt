package com.mylouisworld

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mylouisworld.databinding.ActivityNewQuestionBinding
import com.squareup.picasso.Picasso

class NewQuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioTemplate.setOnClickListener{
            with(binding){
                binding.labelChooseImage.visibility = View.VISIBLE
                spinnerImage.visibility = View.VISIBLE
                binding.labelImageURL.visibility = View.GONE
                binding.txtURL.visibility = View.GONE
            }
        }

        binding.radioExternal.setOnClickListener{
            with(binding){
                binding.labelChooseImage.visibility = View.GONE
                spinnerImage.visibility = View.GONE
                binding.labelImageURL.visibility = View.VISIBLE
                binding.txtURL.visibility = View.VISIBLE
            }
        }

        val items = arrayOf("karen","mermaid","mrkrab","sponge","squid")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,items)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerImage.adapter = adapter

        val imgId = this.resources.getIdentifier("karen","drawable",this.packageName)
        binding.imgPreview.setImageResource(imgId)

        binding.spinnerImage.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val imgId = applicationContext.resources.getIdentifier(items[position],
                    "drawable",applicationContext.packageName)

                binding.imgPreview.setImageResource(imgId)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.btnSave.setOnClickListener{
            val radioAnswer = findViewById<RadioButton>(binding.radioAnswer.checkedRadioButtonId)
            val selectedImageName = items[binding.spinnerImage.selectedItemPosition]
            val imgid = this.resources.getIdentifier(selectedImageName,"drawable",
                this.packageName)
            val newQuestion = QuestionBank(binding.txtNewQuestion.text.toString(),
                radioAnswer.text.toString().lowercase().toBoolean(),imgid,
                binding.txtURL.text.toString(),binding.checkAvailable.isChecked)

            val questionList = QuestionData.questions.toMutableList()
            questionList.add(newQuestion)
            QuestionData.questions = questionList.toTypedArray()

            Toast.makeText(this, "Question Added Successfully", Toast.LENGTH_SHORT).show()

            finish()
        }

        binding.txtURL.setOnKeyListener { v,keyCode,event ->
            if(keyCode == KeyEvent.KEYCODE_ENTER) {
                val url = binding.txtURL.text.toString()
                val builder = Picasso.Builder(this)
                builder.listener { picasso, uri, exception ->
                    exception.printStackTrace()
                }
                builder.build().load(url).into(binding.imgPreview)
            }
            true
        }


    }
}