package com.dev.thorugoh.androidappfundaments

import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

data class DiceUiState(
    @DrawableRes val rolledDice1ImgRes: Int? = null,
    @DrawableRes val rolledDice2ImgRes: Int? = null,
    @DrawableRes val rolledDice3ImgRes: Int? = null,
    val rolledDice: List<RolledDice> = emptyList(),
    val numberOfRolls: Int = 0,
)

class DiceViewModel:  ViewModel() {
    private val _uiState = MutableStateFlow(DiceUiState())
    val uiState: StateFlow<DiceUiState> = _uiState.asStateFlow()

    private val _uiStateLiveData = MutableLiveData(DiceUiState())
    val uiStateLiveData: MutableLiveData<DiceUiState> = _uiStateLiveData



    fun rollDice() {
        val firstDie = Random.nextInt(from = 1, until = 7)
        val secondDie = Random.nextInt(from = 1, until = 7)
        val thirdDie = Random.nextInt(from = 1, until = 7)

        val rolledDice = RolledDice(firstDie, secondDie, thirdDie)

        _uiState.update { currentState ->
            val currentRolledDice = currentState.rolledDice.toMutableList()
            currentRolledDice.add(rolledDice)

            currentState.copy(
                rolledDice1ImgRes = getDiceImageRes(firstDie),
                rolledDice2ImgRes = getDiceImageRes(secondDie),
                rolledDice3ImgRes = getDiceImageRes(thirdDie),
                numberOfRolls = currentState.numberOfRolls + 1,
                rolledDice = currentRolledDice.toList()
            )
        }

        CoroutineScope(Dispatchers.IO).launch {
            val currentRolledDice = _uiState.value.rolledDice.toMutableList().toMutableList()
            currentRolledDice.add(rolledDice)

            _uiStateLiveData.postValue(
                DiceUiState(
                    rolledDice1ImgRes = getDiceImageRes(firstDie),
                    rolledDice2ImgRes = getDiceImageRes(secondDie),
                    rolledDice3ImgRes = getDiceImageRes(thirdDie),
                    numberOfRolls = _uiStateLiveData.value?.numberOfRolls ?: 0,
                    rolledDice = currentRolledDice.toList()
                )
            )
        }

//        _uiStateLiveData.value = DiceUiState(
//            rolledDice1ImgRes = getDiceImageRes(Random.nextInt(from = 1, until = 7)),
//            rolledDice2ImgRes = getDiceImageRes(Random.nextInt(from = 1, until = 7)),
//            rolledDice3ImgRes = getDiceImageRes(Random.nextInt(from = 1, until = 7)),
//            numberOfRolls = (_uiStateLiveData.value?.numberOfRolls ?: 0) + 1,
//            rolledDice = rolledDice
//        )
    }


}