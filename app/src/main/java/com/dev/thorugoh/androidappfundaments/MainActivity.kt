package com.dev.thorugoh.androidappfundaments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                Toast.makeText(this@MainActivity, "Profile", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.help -> {
                Toast.makeText(this@MainActivity, "Help", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbarSettingsMenu)
        supportActionBar?.setDisplayShowTitleEnabled(false)

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
//            AlertDialog.Builder(this@MainActivity)
//                .setTitle("Run dice")
//                .setMessage("Do you want to roll the dice?")
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .setPositiveButton("Yes") { _, _ ->
//                    viewModel.rollDice()
//                }
//                .setPositiveButtonIcon(AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_dice_unknown))
//                .setNegativeButton("No") { _, _ ->}
//                .setCancelable(false)
//                .create()
//                .show()
//            ConfirmDialogFragment().show(supportFragmentManager, "Confirm")
            ConfirmBottomSheetDialogFragment().show(supportFragmentManager, "Confirm")

//            viewModel.rollDice()
        }

        binding.btnGoToNextScreen.setOnClickListener {
//            val slideInLeft = AnimationUtils.loadAnimation(this@MainActivity, android.R.anim.slide_in_left)
            val customAnim = AnimationUtils.loadAnimation(this@MainActivity, R.anim.custom_anim)
            binding.btnGoToNextScreen.startAnimation(customAnim)

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