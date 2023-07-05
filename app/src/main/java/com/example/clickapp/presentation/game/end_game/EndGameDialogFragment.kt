package com.example.clickapp.presentation.game.end_game

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.clickapp.R
import com.example.clickapp.databinding.FragmentEndGameDialogBinding
import com.example.clickapp.presentation.prefs.ResultPrefs.RESULT_KEY
import com.example.clickapp.presentation.prefs.ResultPrefs.RESULT_LIST


class EndGameDialogFragment : DialogFragment(R.layout.fragment_end_game_dialog) {

    private val binding: FragmentEndGameDialogBinding by viewBinding()
    private val args: EndGameDialogFragmentArgs by navArgs()
    private val sharedPreferences: SharedPreferences by lazy {
        requireActivity().getSharedPreferences(RESULT_KEY, Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initViews()
    }

    private fun initViews() {
        with(binding) {
            saveButton.isEnabled = false
            textTimeResult.text = args.resultTime

            editTextName.doOnTextChanged { _, _, _, _ ->
                saveButton.isEnabled = !editTextName.text.isNullOrEmpty()
            }
            saveButton.setOnClickListener {
                val currentList = sharedPreferences.getString(RESULT_LIST, null)?.let {
                        "$it, ${editTextName.text}"
                    } ?: editTextName.text.toString()

                sharedPreferences.edit()
                    .putString(RESULT_LIST, currentList)
                    .apply()

                this@EndGameDialogFragment.dismiss()
            }
        }
    }

}