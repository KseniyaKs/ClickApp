package com.example.clickapp.presentation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.clickapp.R
import com.example.clickapp.databinding.FragmentMenuBinding


class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val binding: FragmentMenuBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            buttonStart.setOnClickListener {
                findNavController().navigate(MenuFragmentDirections.actionMenuToStartGame())
            }
            buttonResult.setOnClickListener {
                findNavController().navigate(MenuFragmentDirections.actionMenuToResult())
            }
            buttonPolitic.setOnClickListener {
                findNavController().navigate(MenuFragmentDirections.actionMenuToPolitics())
            }
            buttonQuit.setOnClickListener {
                requireActivity().finish()
            }
        }
    }
}