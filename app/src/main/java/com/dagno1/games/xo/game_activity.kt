package com.dagno1.games.xo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class GameActivity : AppCompatActivity() {
    private val corButton = mutableListOf<MutableList<Button>>()
    private var turn = "X"
    private lateinit var header: TextView
    private lateinit var background: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        background = findViewById(R.id.background)
        header = findViewById(R.id.header)
        corButton.add(mutableListOf(
                findViewById(R.id.cor00),
                findViewById(R.id.cor01),
                findViewById(R.id.cor02)
            )
        )
        corButton.add(mutableListOf(
                findViewById(R.id.cor10),
                findViewById(R.id.cor11),
                findViewById(R.id.cor12)
            )
        )
        corButton.add(
            mutableListOf(
                findViewById(R.id.cor20),
                findViewById(R.id.cor21),
                findViewById(R.id.cor22)
            )
        )
        corButton[0][0].setOnClickListener { mark(0,0) }
        corButton[0][1].setOnClickListener { mark(0,1) }
        corButton[0][2].setOnClickListener { mark(0,2) }
        corButton[1][0].setOnClickListener { mark(1,0) }
        corButton[1][1].setOnClickListener { mark(1,1) }
        corButton[1][2].setOnClickListener { mark(1,2) }
        corButton[2][0].setOnClickListener { mark(2,0) }
        corButton[2][1].setOnClickListener { mark(2,1) }
        corButton[2][2].setOnClickListener { mark(2,2) }
    }
    private fun mark(x: Int, y: Int) {
        if (corButton[x][y].text != "") {
            Toast.makeText(this, "SPOT TAKEN!", Toast.LENGTH_SHORT).show()
            return
        }
        corButton[x][y].text = turn
        if (turn == "X") {
            header.text = getString(R.string.os_turn)
            turn = "O"
            corButton[x][y].setTextColor(Color.parseColor("#33A1FD"))
            background.setBackgroundColor(Color.parseColor("#EE4B2B"))
        } else {
            header.text = getString(R.string.xs_turn)
            turn = "X"
            corButton[x][y].setTextColor(Color.parseColor("#EE4B2B"))
            background.setBackgroundColor(Color.parseColor("#33A1FD"))
        }
    }
}