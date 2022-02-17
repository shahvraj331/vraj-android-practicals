package com.example.kotlinbasics.activity_intent_fragment.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinbasics.R
import com.example.kotlinbasics.activity_intent_fragment.FragmentViewModel
import com.example.kotlinbasics.activity_intent_fragment.fragment.FirstUserFragment
import com.example.kotlinbasics.activity_intent_fragment.fragment.GalleryFragment
import com.example.kotlinbasics.activity_intent_fragment.fragment.SecondUserFragment
import com.example.kotlinbasics.databinding.ActivityMainIntentFragmentBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainIntentFragmentBinding
    private val viewModel: FragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainIntentFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.intent_and_fragments)
        val viewType = intent.getBooleanExtra(getString(R.string.jetpack_navigation_component), false)
        viewModel.setIsJetPackEnabled(viewType)

        binding.swJetpackNavigation.isChecked = viewType
        if (viewType) {
            startActivityWithNavigationComponent()
        } else {
            startActivityWithoutNavigationComponent()
        }

        binding.swJetpackNavigation.setOnCheckedChangeListener { _, isChecked ->
            val intent = Intent(this@MainActivity, MainActivity::class.java)
            intent.putExtra(getString(R.string.jetpack_navigation_component), isChecked)
            startActivity(intent)
            finish()
        }
    }

    private fun setUpFragment(newFragment: Fragment, fragmentTag: String) {
        supportFragmentManager.commit {
            replace(R.id.currentFragment, newFragment, fragmentTag)
        }
    }

    private fun startActivityWithoutNavigationComponent() {
        setUpFragment(GalleryFragment(), getString(R.string.gallery))
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.galleryFragment -> {
                    setUpFragment(GalleryFragment(), getString(R.string.gallery))
                }
                R.id.firstUserFragment -> {
                    setUpFragment(FirstUserFragment(), getString(R.string.first_user))
                }
                R.id.secondUserFragment -> {
                    setUpFragment(SecondUserFragment(), getString(R.string.second_user))
                }
            }
            true
        }
    }

    private fun startActivityWithNavigationComponent() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.currentFragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }
}