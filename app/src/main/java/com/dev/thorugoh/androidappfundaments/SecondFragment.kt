package com.dev.thorugoh.androidappfundaments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dev.thorugoh.androidappfundaments.databinding.FragmentSecondBinding
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {
    private val viewModel: DiceViewModel by activityViewModels()
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstFragment = arguments?.getStringArray("first_arg") ?: arrayOf()

        lifecycleScope.launch {
            viewModel.uiState.collect {
                it.rolledDice3ImgRes?.let { it1 -> binding.ivRolledDice3.setImageResource(it1) }
            }
        }
        Log.d("SecondFragment", "Argument: ${firstFragment.joinToString()}")
    }
}