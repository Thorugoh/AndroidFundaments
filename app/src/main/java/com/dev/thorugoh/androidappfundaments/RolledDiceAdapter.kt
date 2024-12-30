package com.dev.thorugoh.androidappfundaments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.thorugoh.androidappfundaments.databinding.ItemRolledDiceBinding

data class RolledDice(
    val firstDie: Int,
    val secondDie: Int,
    val thirdDie: Int
)

class RolledDiceAdapter(private val rolledDice: List<RolledDice>): RecyclerView.Adapter<RolledDiceAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemRolledDiceBinding): RecyclerView.ViewHolder(binding.root) {

        fun binding(rolledDice: RolledDice) {
            with(binding) {
                imgFirstDie.setImageResource(getDiceImageRes(rolledDice.firstDie))
                imgSecondDie.setImageResource(getDiceImageRes(rolledDice.secondDie))
                imgThirdDie.setImageResource(getDiceImageRes(rolledDice.thirdDie))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRolledDiceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return rolledDice.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(rolledDice[position])
    }
}