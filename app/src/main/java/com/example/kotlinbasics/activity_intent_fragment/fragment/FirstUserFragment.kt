package com.example.kotlinbasics.activity_intent_fragment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.kotlinbasics.R
import com.example.kotlinbasics.activity_intent_fragment.FragmentViewModel
import com.example.kotlinbasics.databinding.FragmentFirstUserBinding

class FirstUserFragment : Fragment() {

    private val viewModel: FragmentViewModel by activityViewModels()
    private lateinit var binding: FragmentFirstUserBinding
    private var messagesList: MutableList<String> = mutableListOf()
    private var isJetPackEnabled = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFirstUserBinding.inflate(inflater, container, false)
        val view = binding.root
        isJetPackEnabled = viewModel.getIsJetPackEnabled()
        updateConversation()

        binding.btnSend.setOnClickListener {
            getUpdatedMessage()?.let { message ->
                val action = FirstUserFragmentDirections.goToSecondUserFragment(message)
                viewModel.updateConversation(message)
                updateConversation()
                binding.etNewMessage.onEditorAction(EditorInfo.IME_ACTION_DONE)
                binding.etNewMessage.clearFocus()
                if (isJetPackEnabled) {
                    it.findNavController().navigate(action)
                }
            }
        }

        return view
    }

    private fun getUpdatedMessage(): String? {
        val currentMessage = binding.etNewMessage.text.toString().trim()
        return if (currentMessage.isEmpty()) {
            Toast.makeText(context?.applicationContext, getString(R.string.enter_message), Toast.LENGTH_SHORT).show()
            null
        } else {
            getString(R.string.prefix_first) + currentMessage
        }
    }

    private fun updateConversation() {
        binding.etNewMessage.setText(getString(R.string.empty_text))
        binding.tvConversation.text = getString(R.string.empty_text)
        messagesList = viewModel.retrieveConversation()
        var allConversation = getString(R.string.empty_text)
        for (message in messagesList) {
            allConversation += message + getString(R.string.next_line)
        }
        binding.tvConversation.text = allConversation
    }
}