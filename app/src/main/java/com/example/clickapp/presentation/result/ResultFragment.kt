package com.example.clickapp.presentation.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.clickapp.R
import com.example.clickapp.databinding.FragmentResultBinding
import com.example.clickapp.presentation.App
import com.example.clickapp.presentation.database.AppDatabase

class ResultFragment : Fragment(R.layout.fragment_result) {

    private val binding: FragmentResultBinding by viewBinding()
    private val db: AppDatabase? by lazy { App.instance?.database }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val resultList = db?.winerDao()?.getAll()
        val sortedList = resultList?.let { list -> list.sortedBy { it.resultTime } }

        with(binding) {
            toolbar.titleText.text = getString(R.string.result_title)
            toolbar.toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            sortedList?.let {
                recyclerViewResult.adapter = ResultAdapter(it)
                recyclerViewResult.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}