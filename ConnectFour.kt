package connectfour

import kotlin.system.exitProcess

/*
Player wins when they place four discs of the same color in a row
horizontally, vertically, or diagonally.
If the board is full and the win condition isn't fulfilled, it is claimed a draw.
 */

data class Dimension(val row: Int, val col: Int)

class ConnectFour {
    var row = 6 //DEFAULT_ROW_COUNT
    var col = 7 //DEFAULT_COL_COUNT
    var turnP1 = true
    var multiGames = false
    var matrice = MutableList(row) {MutableList(col) { " " } }

    fun run() {
        println("Connect Four")
        println("First player's name:")
        val player1Name = readln()
        println("Second player's name:")
        val player2Name = readln()

        val dimensions = readDimensions()
        println("$player1Name VS $player2Name")
        println("${dimensions.row} X ${dimensions.col} board")
        drawBoard() //empty board display
        beginGame(player1Name, player2Name)
    }

    private fun beginGame(player1Name: String, player2Name: String) {
        turnP1 = true
        matrice = MutableList(row) {MutableList(col) { " " } } //update matrice

        while (true) {
            if (turnP1) println("$player1Name's turn:")
            else println("$player2Name's turn:")

            val playerCol = readColumn(player1Name, player2Name)
            if (playerCol == -99) {
                println("Game over!")
                break
            }

            val lastIndexOfSpace = lastEmptyRowIndex(playerCol)
            if (lastIndexOfSpace == -1) {
                println("Column $playerCol is full")
                continue
            }

            if (turnP1) {
                matrice[lastIndexOfSpace][playerCol - 1] = "o"
                drawBoard(matrice)
                if (checkWin(true)) {
                    println("Player $player1Name won")
                    println("Game Over!")
                    exitProcess(0)
                }
                turnP1 = false
            }
            else {
                matrice[lastIndexOfSpace][playerCol - 1] = "*"
                drawBoard(matrice)
                if (checkWin(false)) {
                    println("Player $player2Name won")
                    println("Game Over!")
                    exitProcess(0)
                }
                turnP1 = true
            }

            if (isBoardFull()) {
                println("It is a draw")
                println("Game Over!")
                exitProcess(0)
            }
        }
    }

    private fun checkWin(turnP1: Boolean): Boolean {
        val pattern = if (turnP1)
            Regex(".*?oooo.*?")
        else
            Regex(".*?\\*\\*\\*\\*.*?")

        //horizontal check
        for (rowM in matrice) {
            if (rowM.joinToString("").matches(pattern))
                return true else continue
        }

        //vertical check
        for (i in 0 until matrice[0].size) { //number of columns
            val tempList = mutableListOf<String>()
            for (j in matrice.indices) { //number of rows
                tempList.add(matrice[j][i])
            }
            if (tempList.joinToString("").matches(pattern))
                return true else continue
        }

        //Diagonal check, left to right
        val temp = matrice.size + matrice[0].size - 2
        for (i in 0..temp) {
            val tempList = mutableListOf<String>()
            for (j in 0..i) {
                val k = i - j
                if (k < matrice.size && j < matrice[0].size)
                    tempList.add(matrice[k][j])
            }
            if (tempList.joinToString("").matches(pattern))
                return true else continue
        }

        //Diagonal check, left to right
        for (i in 0..temp) {
            val tempList = mutableListOf<String>()
            var k = i
            for (j in matrice[0].size - 1 downTo 0){
                if (k < matrice.size && j < matrice[0].size && k >= 0 )
                    tempList.add(matrice[k][j])
                k--
            }
            if (tempList.joinToString("").matches(pattern))
                return true else continue
        }


        return false
    }

    private fun isBoardFull(): Boolean {
        for (i in matrice.indices) {
            for (j in matrice[0].indices) {
                if (matrice[i][j] == " ")
                    return false
            }
        }
        return true
    }


    private fun lastEmptyRowIndex(playerCol: Int): Int {
        for (i in matrice.size-1  downTo  0) {
            if (matrice[i][playerCol - 1] == " ") {
                return i
            }
        }
        return -1
    }


    private fun readColumn(player1Name: String, player2Name: String): Int {
        val columnRegex = Regex("\\s*\\d+\\s*")
        while (true) {
            val colInput = readln()
            if (colInput == "end")
                return -99
            if (!colInput.matches(columnRegex)) {
                println("Incorrect column number")
                if (turnP1)
                    println("$player1Name's turn:")
                else
                    println("$player2Name's turn:")
                continue
            }

            val playerCol = colInput.toInt()
            if (playerCol !in 1..col) {
                println("The column number is out of range (1 - ${col})")
                if (turnP1)
                    println("$player1Name's turn:")
                else
                    println("$player2Name's turn:")
                continue
            }

            return playerCol
        }
    }

    private fun readDimensions(): Dimension {
        val boardRegex = Regex("\\s*\\d+\\s*[xX]\\s*\\d+\\s*")

        while (true) {
            println("Set the board dimensions (Rows x Columns)")
            println("Press Enter for default (6 x 7)")
            val dimensionInput = readln()

            if (!dimensionInput.matches(boardRegex) && dimensionInput.isNotBlank()) {
                println("Invalid input")
                continue
            }

            if (dimensionInput.isBlank()) {
                break
            } else {
                var isRowSet = false
                for (ch in dimensionInput) {
                    if (ch.isDigit() && !isRowSet) {
                        row = ch.digitToInt()
                        isRowSet = true
                    }
                    if (ch.isDigit() && isRowSet)
                        col = ch.digitToInt()
                }
            }

            if (!checkBoardRowRange() || !checkBoardColumnRange())
                continue else break
        }

        return Dimension(row, col)
    }

    private fun checkBoardRowRange(): Boolean {
        return if (row !in 5..9) {
            println("Board rows should be from 5 to 9")
            false
        } else
            true
    }

    private fun checkBoardColumnRange(): Boolean {
        return if (col !in 5..9) {
            println("Board columns should be from 5 to 9")
            false
        } else
            true
    }

    private fun drawBoard() {
        // 1 2 3 4 5...
        repeat(col) {
            print(" ${it+1}")
        }
        println()
        //║ ║ ║ ║ ║...
        repeat(row) {
            repeat(col + 1) {
                print("║ ")
            }
            println()
        }
        //╚═╩═╩═╩═╩═╩═╩═╝
        println("╚" + "═╩".repeat(col - 1) + "═╝")
    }

    private fun drawBoard(matrice: MutableList<MutableList<String>>) {
        // 1 2 3 4 5...
        repeat(col) {
            print(" ${it+1}")
        }
        println()
        //║ ║ ║ ║ ║...
        for (i in 0 until row) {
            for (j in 0 until col) {
                print("║${matrice[i][j]}")
            }
            println("║")
        }
        //╚═╩═╩═...╩═╩═╝
        println("╚" + "═╩".repeat(col - 1) + "═╝")
    }
//end of class
}


fun main() {
    val game = ConnectFour()
    game.run()
}


