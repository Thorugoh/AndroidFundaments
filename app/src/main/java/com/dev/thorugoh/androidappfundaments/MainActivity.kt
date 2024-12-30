package com.dev.thorugoh.androidappfundaments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.dev.thorugoh.androidappfundaments.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DiceViewModel by viewModels();

    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvMainContainer) as? NavHostFragment
        navHostFragment?.navController
    }

    override fun onResume() {
        super.onResume()

        viewModel.uiStateLiveData.observe(this@MainActivity) { uiState ->
            uiState.rolledDice1ImgRes?.let { it1 -> binding.ivRolledDice1.setImageResource(it1) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        lifecycleScope.launch {
//            viewModel.uiState.collect {
//                // Update ui elements
//                it.rolledDice1ImgRes?.let { it1 -> binding.ivRolledDice1.setImageResource(it1) }
//            }
//        }


        binding.btnRollDice.setOnClickListener {
            viewModel.rollDice()
        }

        binding.btnGoToNextScreen.setOnClickListener {
            navController?.currentDestination?.id.let {
                when(it) {
                    R.id.firstFragment -> {
                        navController?.navigate(
                            R.id.action_firstFragment_to_secondFragment,
                            bundleOf("first_arg" to arrayOf("1", "2", "3") )
                        )
                        binding.btnGoToNextScreen.text = getString(R.string.see_next_die)
                    }
                    R.id.secondFragment -> {
                        navController?.navigate(R.id.action_secondFragment_to_thirdFragment)
                        binding.btnGoToNextScreen.text = getString(R.string.see_previous_die)
                    }
                    R.id.thirdFragment -> {
                        navController?.navigate(R.id.action_thirdFragment_to_firstFragment)
                        binding.btnGoToNextScreen.text = getString(R.string.see_next_die)
                    }
                }
            }
        }

    }
}