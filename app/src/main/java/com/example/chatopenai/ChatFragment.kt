package com.example.chatopenai

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chatopenai.databinding.FragmentChatBinding
import com.example.chatopenai.domain.model.MessageResponse
import com.example.chatopenai.domain.model.Prompt
import com.example.chatopenai.presentation.adapter.ChatAdapter
import com.example.chatopenai.presentation.model.ChatRecyclerViewItem

class ChatFragment : Fragment() {

    private lateinit var _binding: FragmentChatBinding
    private var chatAdapter = ChatAdapter()

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
        prepareViewListener()

    }

    private fun initialWork() {
        _binding.chatRv.adapter = chatAdapter

        val list = ArrayList<ChatRecyclerViewItem>()
        list.add(ChatRecyclerViewItem.PromptItem(Prompt("Diga que isso é um teste")))
        list.add(ChatRecyclerViewItem.MessageResponseItem(MessageResponse("Isso é um teste")))
        chatAdapter.submitList(list)
    }

    private fun prepareViewListener() {
        _binding.apply {
            sendBtn.setOnClickListener {
                //TODO
            }
        }
    }
}