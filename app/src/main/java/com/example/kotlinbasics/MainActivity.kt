package com.example.kotlinbasics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinbasics.activity_intent_fragment.activity.MainActivity
import com.example.kotlinbasics.android_permissions.PermissionsActivity
import com.example.kotlinbasics.android_architecture.AndroidArchitectureBaseScreen
import com.example.kotlinbasics.databinding.ActivityMainBinding
import com.example.kotlinbasics.recyclerview_and_adapters.activity.DifferentViewsHomeScreenActivity
import com.example.kotlinbasics.scrollview_and_webview.activity.ScrollViewAndWebViewActivity
import com.example.kotlinbasics.ui_widgets.HomeScreenActivity
import com.example.kotlinbasics.ui_layout.LayoutsActivity
import com.example.kotlinbasics.web_services.activity.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.vraj_android_practicals)

        binding.btnUIWidgets.setOnClickListener {
            intentTo(HomeScreenActivity::class.java)
        }

        binding.btnUILayouts.setOnClickListener {
            intentTo(LayoutsActivity::class.java)
        }

        binding.btnRecyclerViewAndAdapters.setOnClickListener {
            intentTo(DifferentViewsHomeScreenActivity::class.java)
        }

        binding.btnIntAndFrag.setOnClickListener {
            intentTo(MainActivity::class.java)
        }

        binding.btnScrollViewWebView.setOnClickListener {
            intentTo(ScrollViewAndWebViewActivity::class.java)
        }

        binding.btnWebService.setOnClickListener {
            intentTo(LoginActivity::class.java)
        }

        binding.btnPermissions.setOnClickListener {
            intentTo(PermissionsActivity::class.java)
        }

        binding.btnAndroidArchitecture.setOnClickListener {
            intentTo(AndroidArchitectureBaseScreen::class.java)
        }
    }

    private fun intentTo(destinationActivity: Class<*>) {
        startActivity(Intent(this@MainActivity, destinationActivity))
    }

}