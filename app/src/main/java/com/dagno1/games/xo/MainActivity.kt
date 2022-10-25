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
    private val handler = Handler(Looper.getMainLooper())
    lateinit var x: TextView
    lateinit var o: TextView
    lateinit var play_button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        handler.post(updateColor)
        x = findViewById(R.id.x)
        o = findViewById(R.id.o)
        play_button = findViewById(R.id.play)

        play_button.setOnClickListener { starGame() }
    }
    private val updateColor: Runnable = object : Runnable {
        override fun run() {
            val rand = (0..1).random()
            val colorX = if (rand == 0) Color.RED else Color.BLUE
            val colorO = if (rand == 0) Color.BLUE else Color.RED
            x.setTextColor(colorX)
            o.setTextColor(colorO)
            handler.postDelayed(this, 500)
        }
    }
    private fun starGame() {
        handler.removeCallbacks(updateColor)
        val game = Intent(this,GameActivity::class.java)
        startActivity(game)
    }
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateColor)
    }
}