package com.example.kotlinbasics.android_architecture.mvvm_architecture

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.kotlinbasics.R
import com.example.kotlinbasics.databinding.FragmentUserTwoBinding

class UserTwoFragment : Fragment() {

    private lateinit var binding: FragmentUserTwoBinding
    private val viewModel: ChatViewModel by activityViewModels()

    override fun onResume() {
        binding.viewModel = viewModel
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_two, container, false)
        viewModel.emailVerifyImageUrl.value = "https://www.kindpng.com/picc/m/285-2852276_email-id-verification-reminder-plugin-verify-email-illustration.png"
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val view = binding.root

        binding.etEmailOfUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {  }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                var email = ""
                text?.let {
                    email = it.toString()
                }
                viewModel.setEmailOfUser(email)
                viewModel.isEmailValid.observe(viewLifecycleOwner) {
                    binding.tvIsEmailValid.text = if (it) {
                        getString(R.string.valid_email_text)
                    } else {
                        getString(R.string.invalid_email_text)
                    }
                }
                binding.viewModel = viewModel
            }

            override fun afterTextChanged(s: Editable?) {  }
        })

        return view
    }
}