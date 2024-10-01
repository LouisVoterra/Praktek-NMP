package com.mylouisworld

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mylouisworld.databinding.ActivityIntroBinding

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
        }
    }
}