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

        //create 2d array of buttons to look for rows
        val buttonRows = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        //setup all buttons
        setupOnClick(buttonRows)
    }

    private fun setupNewGameButton(newGameButton: Button?) {
        newGameButton?.setOnClickListener {
            recreate()
        }
    }

    private fun setupOnClick(buttonRows : Array<Array<Button>>) {
        //find player turn indicator
        val playerTurn = findViewById<TextView>(R.id.textView)

        //setup player strings
        val playerOneMarker = getString(R.string.player_one_marker)
        val playerOne = getString(R.string.playerTurn, playerOneMarker)
        val playerTwoMarker = getString(R.string.player_two_marker)
        val playerTwo = getString(R.string.playerTurn, playerTwoMarker)

        //create win combinations from 2d array
        val buttonColumns = createButtonColumns(buttonRows)
        val buttonDiagonals = createButtonDiagonals(buttonRows)

        //set to first player's turn
        playerTurn.text = playerOne

        //setup button turn logic
        for (arrays in buttonRows) {
            for (button in arrays) {
                button.setOnClickListener {
                    if (playerTurn.text == playerOne) {
                        button.text = playerOneMarker

                        //switch turns
                        playerTurn.text = playerTwo
                    }
                    else {
                        button.text = playerTwoMarker

                        //switch turns
                        playerTurn.text = playerOne
                    }

                    //disable button after use
                    button.isClickable = false

                    //check for player1 wins
                    checkWinOrTie(buttonRows, playerOneMarker)
                    checkWinOrTie(buttonColumns, playerOneMarker)
                    checkWinOrTie(buttonDiagonals, playerOneMarker)
                    //check for player 2 wins
                    checkWinOrTie(buttonRows, playerTwoMarker)
                    checkWinOrTie(buttonColumns, playerTwoMarker)
                    checkWinOrTie(buttonDiagonals, playerTwoMarker)

                    //TODO: make it so you can't flip the screen
                }
            }
        }
    }

    private fun checkWinOrTie(buttons : Array<Array<Button>>, player : String) {
        //find turn display
        val playerTurn = findViewById<TextView>(R.id.textView)
        //track used buttons
        var usedButtons = 0

        for (arrays in buttons) {
            //reset on new row
            var matchCount = 0
            for (button in arrays) {
                if (button.text.isNotEmpty()) {
                    usedButtons++
                }
                //tally marked buttons in row
                if (button.text == player) {
                    matchCount++
                    if (matchCount == 3) {
                        playerTurn.text = getString(R.string.win_game, player)
                        disableButtons(buttons)
                    }
                }
            }
            //check for tie game (all buttons used)
            if (usedButtons == buttons.size * buttons[0].size) {
                playerTurn.text = getString(R.string.tie_game)
            }
        }
    }

    private fun createButtonColumns(buttonRows : Array<Array<Button>>) : Array<Array<Button>> {
       return arrayOf(
            arrayOf(buttonRows[0][0], buttonRows[1][0], buttonRows[2][0]),
            arrayOf(buttonRows[0][1], buttonRows[1][1], buttonRows[2][1]),
            arrayOf(buttonRows[0][2], buttonRows[1][2], buttonRows[2][2])
        )
    }


    private fun createButtonDiagonals(buttonRows: Array<Array<Button>>): Array<Array<Button>> {
        return arrayOf(
            arrayOf(buttonRows[0][0], buttonRows[1][1], buttonRows[2][2]),
            arrayOf(buttonRows[0][2], buttonRows[1][1], buttonRows[2][0]),
        )
    }

    private fun disableButtons(buttons : Array<Array<Button>>) {
        for (arrays in buttons) {
            for (button in arrays) {
                if (button.isClickable) {
                    button.isClickable = false
                }
            }
        }
    }
}