package com.example.chatopenai.presentation.fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chatopenai.R
import com.example.chatopenai.common.*
import com.example.chatopenai.databinding.FragmentChatBinding
import com.example.chatopenai.presentation.activity.adapter.ChatAdapter
import com.example.chatopenai.presentation.enuns.RequestType
import com.example.chatopenai.presentation.state.ChatViewState
import com.example.chatopenai.presentation.viewmodel.ChatViewModel
import com.example.chatopenai.presentation.viewmodel.MainViewModel

class ChatFragment : Fragment() {

    private lateinit var _binding: FragmentChatBinding
    private var chatAdapter = ChatAdapter()
    private val mainViewModel: MainViewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }
    private val viewModel: ChatViewModel by lazy { ViewModelProvider(requireActivity())[ChatViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialWork()
        observer()
        prepareViewListener()
    }

    private fun handleChatViewState(state: ChatViewState) {
        chatAdapter.submitList(state.chatMessageList)
        state.lastRequestIsCompleted?.apply {
            _binding.loadingAnim.setupLoadingView(!this)
            if(this) _binding.sendBtn.toVisible() else _binding.sendBtn.toInvisible()
        }
    }

    private fun observer() {
        viewModel.chatViewState.observe(viewLifecycleOwner) {
            handleChatViewState(it)
        }
    }

    private fun initialWork() {
        _binding.chatRv.adapter = chatAdapter
        _binding.promptEdt.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES or InputType.TYPE_CLASS_TEXT
    }

    private fun prepareViewListener() {
        _binding.apply {
            sendBtn.setOnClickListener {
                val text = _binding.promptEdt.text.toString().trim()
                if(text.isNotEmpty()) {
                    _binding.promptEdt.setText("")
                    it.hideKeyboard()
                    viewModel.makeRequest(text)
                }
            }

            chipGroup.setOnCheckedStateChangeListener { group, _ ->
                when(group.checkedChipId) {
                    R.id.generate_image -> viewModel.requestType = RequestType.GENERATE_IMAGE
                    R.id.generate_text -> viewModel.requestType = RequestType.GENERATE_TEXT
                }
            }
        }
    }
}