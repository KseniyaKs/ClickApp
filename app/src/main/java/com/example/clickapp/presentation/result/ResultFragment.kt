package com.example.clickapp.presentation.result

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.clickapp.R
import com.example.clickapp.databinding.FragmentResultBinding
import com.example.clickapp.presentation.prefs.ResultPrefs.RESULT_KEY
import com.example.clickapp.presentation.prefs.ResultPrefs.RESULT_LIST

class ResultFragment : Fragment(R.layout.fragment_result) {

    private val binding: FragmentResultBinding by viewBinding()

    private val sharedPreferences: SharedPreferences by lazy {
        requireActivity().getSharedPreferences(RESULT_KEY, Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val resultList = sharedPreferences.getString(RESULT_LIST, null)?.split(", ")

        with(binding) {
            toolbar.titleText.text = getString(R.string.result_title)
            toolbar.toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            resultList?.let {
                recyclerViewResult.adapter = ResultAdapter(it)
                recyclerViewResult.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}