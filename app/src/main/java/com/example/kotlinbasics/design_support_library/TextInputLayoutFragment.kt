package com.example.kotlinbasics.design_support_library

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.FragmentTextInputLayoutBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class TextInputLayoutFragment : Fragment() {

    private lateinit var binding: FragmentTextInputLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTextInputLayoutBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.etCounter.doOnTextChanged { text, _, _, _ ->
            text?.let {
                if (it.length > binding.tiCounter.counterMaxLength)
                    binding.tiCounter.error = "Max character length is " + binding.tiCounter.counterMaxLength;
                else
                    binding.tiCounter.error = null
            }
        }

        binding.fabShowSnackBar.setOnClickListener {
            showSnackbar()
        }
        return view
    }

    private fun showSnackbar() {
        val snackBar = Snackbar.make(binding.root, "Clear text fields", Snackbar.LENGTH_LONG)
        val view = snackBar.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snackBar.setAction("Clear") {
            binding.etCounter.setText("")
            binding.etOutlined.setText("")
            binding.etFilled.setText("")
            binding.etDefault.setText("")
        }
        snackBar.setActionTextColor(resources.getColor(android.R.color.holo_green_light))
        snackBar.setTextColor(resources.getColor(R.color.white))
        snackBar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        snackBar.show()
    }
}