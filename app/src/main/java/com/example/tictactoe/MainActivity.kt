package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup new game
        val  newGameButton = findViewById<Button>(R.id.new_game)
        setupNewGameButton(newGameButton)

        //setup all buttons
        //find buttons
        val  button1 = findViewById<Button>(R.id.button1)
        val  button2 = findViewById<Button>(R.id.button2)
        val  button3 = findViewById<Button>(R.id.button3)
        val  button4 = findViewById<Button>(R.id.button4)
        val  button5 = findViewById<Button>(R.id.button5)
        val  button6 = findViewById<Button>(R.id.button6)
        val  button7 = findViewById<Button>(R.id.button7)
        val  button8 = findViewById<Button>(R.id.button8)
        val  button9 = findViewById<Button>(R.id.button9)

        //setup necessary on click functions
        setupOnClick(button1)
        setupOnClick(button2)
        setupOnClick(button3)
        setupOnClick(button4)
        setupOnClick(button5)
        setupOnClick(button6)
        setupOnClick(button7)
        setupOnClick(button8)
        setupOnClick(button9)

    }

    private fun setupNewGameButton(newGameButton: Button?) {
        newGameButton?.setOnClickListener {
            recreate()
        }
    }

    private fun setupOnClick(button: Button?) {
        val playerTurn = findViewById<TextView>(R.id.textView)

        button?.setOnClickListener {
            if (playerTurn.text == getString(R.string.playerX)) {
                button.text = "X"

                playerTurn.text = getString(R.string.playerO)
            } else {
                button.text = "O"
                playerTurn.text = getString(R.string.playerX)
            }

            button.isClickable = false
        }
    }
}