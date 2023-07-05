package com.example.clickapp.presentation.politic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.clickapp.R
import com.example.clickapp.databinding.FragmentPoliticBinding

class PoliticFragment : Fragment(R.layout.fragment_politic) {

    private val binding: FragmentPoliticBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            toolbar.titleText.text = getString(R.string.politic_title)
            toolbar.toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}