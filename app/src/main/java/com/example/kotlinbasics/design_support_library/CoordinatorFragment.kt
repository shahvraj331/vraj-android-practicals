package com.example.kotlinbasics.design_support_library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.FragmentCoordinatorBinding

class CoordinatorFragment : Fragment() {

    private lateinit var binding: FragmentCoordinatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoordinatorBinding.inflate(layoutInflater, container, false)
        val view = binding.root
//        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return view
    }
}