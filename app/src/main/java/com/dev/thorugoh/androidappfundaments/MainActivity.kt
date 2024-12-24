package com.dev.thorugoh.androidappfundaments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.dev.thorugoh.androidappfundaments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvMainContainer) as? NavHostFragment
        navHostFragment?.navController
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

        binding.btnGoToNextScreen.setOnClickListener {
            navController?.currentDestination?.id.let {
                when(it) {
                    R.id.firstFragment -> {
                        navController?.navigate(
                            R.id.action_firstFragment_to_secondFragment,
                            bundleOf("first_arg" to arrayOf("1", "2", "3") )
                        )
                        binding.btnGoToNextScreen.text = getString(R.string.go_back)
                    }
                    R.id.secondFragment -> {
                        navController?.popBackStack()
                        binding.btnGoToNextScreen.text = getString(R.string.go_to_next_screen)
                    }
                }
            }
        }

    }
}