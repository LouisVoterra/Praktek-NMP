package com.mylouisworld

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mylouisworld.databinding.ActivityIntroBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class IntroActivity : AppCompatActivity() {
    private lateinit var binding:ActivityIntroBinding

    companion object{

        val PLAYER_NAME = "player_name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener{
            val playerName = binding.txtPlayerName.text.toString()
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra(PLAYER_NAME,playerName)
            startActivity(intent)
            finish()
        }

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, QuestionListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showDatePickerDialog() {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,{
                view: DatePicker?, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                val displayFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val displayDate = displayFormat.format(selectedDate.time)
                binding.txtBod.setText(displayDate)

                val storageFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val storedDate = storageFormat.format(selectedDate.time)
                binding.txtBod.tag = storedDate
            },year,
              month,
              day	)
        datePickerDialog.show()

        binding.txtBod.setOnClickListener { showDatePickerDialog() }


    }

}