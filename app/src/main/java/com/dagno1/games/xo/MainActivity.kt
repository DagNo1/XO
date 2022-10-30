package com.dagno1.games.xo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var x: TextView
    lateinit var o: TextView
    lateinit var play_button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        x = findViewById(R.id.x)
        o = findViewById(R.id.o)
        play_button = findViewById(R.id.play)
        play_button.setOnClickListener { starGame() }
    }
    private fun starGame() {
        val game = Intent(this,GameActivity::class.java)
        startActivity(game)
    }
}