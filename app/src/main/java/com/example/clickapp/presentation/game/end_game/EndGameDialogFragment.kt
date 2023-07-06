package com.example.clickapp.presentation.game.end_game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.clickapp.R
import com.example.clickapp.databinding.FragmentEndGameDialogBinding
import com.example.clickapp.presentation.App
import com.example.clickapp.presentation.database.AppDatabase
import com.example.clickapp.presentation.database.WinerEntity


class EndGameDialogFragment : DialogFragment(R.layout.fragment_end_game_dialog) {

    private val binding: FragmentEndGameDialogBinding by viewBinding()
    private val args: EndGameDialogFragmentArgs by navArgs()
    private val db: AppDatabase? by lazy { App.instance?.database }

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
                    db?.winerDao()?.insert(
                        WinerEntity(
                            name = editTextName.text.toString(),
                            resultTime = args.resultTime.filter { it.isDigit() }.toInt()
                        )
                    )
                this@EndGameDialogFragment.dismiss()
            }
        }
    }

}