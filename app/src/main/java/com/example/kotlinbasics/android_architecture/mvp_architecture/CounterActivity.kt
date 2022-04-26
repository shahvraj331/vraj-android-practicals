package com.example.kotlinbasics.android_architecture.mvp_architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.kotlinbasics.R
import com.example.kotlinbasics.commonFolder.utils.Constants
import com.example.kotlinbasics.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity(), View {

    private lateinit var binding: ActivityCounterBinding
    private var presenter: CounterActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.mvp_architecture)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = CounterActivityPresenter(this)
    }

    override fun initializeView() {
        binding.tvCurrentCounterValue.text = Constants.ZERO.toString()
        binding.btnIncreaseCounter.setOnClickListener {
            presenter?.incrementCounter()
        }
        binding.btnDecreaseCounter.setOnClickListener {
            presenter?.decrementCounter()
        }
    }

    override fun updateView(valueIncreased: Boolean) {
        binding.tvCurrentCounterValue.text = presenter?.getCounter()
        val currentStatusText = if (valueIncreased) getString(R.string.value_increased) else getString(R.string.value_decreased)
        binding.tvLastCounterStatus.text = currentStatusText
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}