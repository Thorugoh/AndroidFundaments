package com.dev.thorugoh.androidappfundaments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dev.thorugoh.androidappfundaments.databinding.FragmentThirdBinding
import kotlinx.coroutines.launch


class ThirdFragment : Fragment() {
    private val viewModel: DiceViewModel by activityViewModels()

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                binding.rvRolledDice.adapter = RolledDiceAdapter(uiState.rolledDice)
            }
        }
    }


}