package com.dev.thorugoh.androidappfundaments

fun getDiceImageRes(diceValue: Int): Int {
    return when (diceValue) {
        1 -> R.drawable.ic_dice_one
        2 -> R.drawable.ic_dice_two
        3 -> R.drawable.ic_dice_three
        4 -> R.drawable.ic_dice_four
        5 -> R.drawable.ic_dice_five
        else -> R.drawable.ic_dice_six

    }
}