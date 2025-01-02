package com.dev.thorugoh.androidappfundaments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dev.thorugoh.androidappfundaments.databinding.DialogFragmentConfirmBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ConfirmBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private val viewModel: DiceViewModel by activityViewModels()

    private var _binding: DialogFragmentConfirmBinding? = null
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
        _binding = DialogFragmentConfirmBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}