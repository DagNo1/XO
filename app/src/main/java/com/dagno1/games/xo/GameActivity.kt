package com.dagno1.games.xo

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.system.exitProcess

class GameActivity : AppCompatActivity() {
    private val corButton = mutableListOf<MutableList<Button>>()
    private val board = mutableListOf(
        mutableListOf('_','_','_'),
        mutableListOf('_','_','_'),
        mutableListOf('_','_','_')
    )
    private var turn = "X"
    private lateinit var header: TextView
    private lateinit var background: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        loadViews()
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
            board[x][y] = 'X'
            turn = "O"
            corButton[x][y].setTextColor(Color.parseColor("#33A1FD"))
            background.setBackgroundColor(Color.parseColor("#EE4B2B"))
        } else {
            header.text = getString(R.string.xs_turn)
            board[x][y] = 'O'
            turn = "X"
            corButton[x][y].setTextColor(Color.parseColor("#EE4B2B"))
            background.setBackgroundColor(Color.parseColor("#33A1FD"))
        }
        gameStatus()
    }
    private fun loadViews() {
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
    }
    private fun gameStatus() {
        val xo = mutableListOf('X','O')
        var winner = 2
        var wins = 0
        for (i in 0..1) {
            if (
                (board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][2] == xo[i]) ||
                (board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][2] == xo[i]) ||
                (board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][2] == xo[i]) ||
                (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == xo[i]) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] == xo[i]) ||
                (board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[2][0] == xo[i]) ||
                (board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[2][1] == xo[i]) ||
                (board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[2][2] == xo[i])) {
                winner = i
                wins++
            }
        }
        var x = 0
        var o = 0
        var space = 0
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == '_') ++space
                else if (board[i][j] == 'X') ++x
                else if (board[i][j] == 'O') ++o
            }
        }
        if (space == 0 && winner == 2) gamesEnd("Draw.")
        else if (winner == 0) gamesEnd("X Wins!")
        else if (winner == 1) gamesEnd("O Wins!")
    }
    private fun gamesEnd(msg: String) {
        header.text = msg
        when (msg) {
            "Draw." -> {
                header.setTextColor(Color.parseColor("#FFFFFFFF"))
                background.setBackgroundColor(Color.parseColor("#31393c"))
            }
            "X Wins!" -> {
                header.setTextColor(Color.parseColor("#31393c"))
                background.setBackgroundColor(Color.parseColor("#33A1FD"))
            }
            "O Wins!"-> {
                header.setTextColor(Color.parseColor("#31393c"))
                background.setBackgroundColor(Color.parseColor("#F26430"))
            }
        }
        AlertDialog.Builder(this)
            .setTitle(msg)
            .setPositiveButton("Play Again!") { _, _ ->
                finish()
                startActivity(Intent(this,GameActivity::class.java))
            }
            .setNegativeButton("Exit") { _, _ ->
                finishAffinity()
                exitProcess(0)
            }
            .show()
    }
}