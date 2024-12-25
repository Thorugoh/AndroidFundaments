package com.dev.thorugoh.androidappfundaments

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

data class DiceUiState(
    @DrawableRes val rolledDice1ImgRes: Int? = null,
    @DrawableRes val rolledDice2ImgRes: Int? = null,
    @DrawableRes val rolledDice3ImgRes: Int? = null,

    val numberOfRolls: Int = 0,
)

class DiceViewModel:  ViewModel() {
    private val _uiState = MutableStateFlow(DiceUiState())
    val uiState: StateFlow<DiceUiState> = _uiState.asStateFlow()

    fun rollDice() {
        _uiState.update { currentState ->
            currentState.copy(
                rolledDice1ImgRes = getDiceImageRes(Random.nextInt(from = 1, until = 7)),
                rolledDice2ImgRes = getDiceImageRes(Random.nextInt(from = 1, until = 7)),
                rolledDice3ImgRes = getDiceImageRes(Random.nextInt(from = 1, until = 7)),
                numberOfRolls = currentState.numberOfRolls + 1
            )
        }
    }

    private fun getDiceImageRes(diceValue: Int): Int {
        return when (diceValue) {
            1 -> R.drawable.ic_dice_one
            2 -> R.drawable.ic_dice_two
            3 -> R.drawable.ic_dice_three
            4 -> R.drawable.ic_dice_four
            5 -> R.drawable.ic_dice_five
            else -> R.drawable.ic_dice_six

        }
    }
}