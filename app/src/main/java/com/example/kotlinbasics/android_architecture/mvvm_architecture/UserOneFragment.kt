package com.example.kotlinbasics.android_architecture.mvvm_architecture

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.FragmentUserOneBinding

class UserOneFragment : Fragment() {

    private lateinit var binding: FragmentUserOneBinding
    private val viewModel: ChatViewModel by activityViewModels()

    override fun onResume() {
        binding.viewModel = viewModel
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_one, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val view = binding.root

        binding.etMessageToSecond.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {  }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                var message = ""
                text?.let {
                    message = it.toString()
                }
                viewModel.setLatestMessageFromFirst(message)
                binding.viewModel = viewModel
            }

            override fun afterTextChanged(s: Editable?) {  }
        })

        return view
    }
}