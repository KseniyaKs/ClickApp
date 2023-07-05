package com.example.clickapp.presentation.game

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.clickapp.R
import com.example.clickapp.databinding.FragmentGameBallBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val MAX_TIME = Long.MAX_VALUE
const val INTERVAL_STEP = 1000L
const val MIN_TIME = 0L

class GameBallFragment : Fragment(R.layout.fragment_game_ball) {

    private val binding: FragmentGameBallBinding by viewBinding()

    private var animationDrawable: AnimationDrawable? = null
    private var timer: CountDownTimer? = null
    private var clickCounter = 0
    private var ballCounter = 0
    private var startMs: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override fun onPause() {
        super.onPause()
        timer?.cancel()
    }

    private fun initViews() {
        with(binding) {
            timer = object : CountDownTimer(MAX_TIME, INTERVAL_STEP) {
                override fun onTick(millisUntilFinished: Long) {
                    val currentSec = SystemClock.elapsedRealtime() - startMs
                    val formatedTime = SimpleDateFormat(
                        "mm:ss",
                        Locale.getDefault(Locale.Category.FORMAT)
                    ).format(Date(currentSec))
                    textGameTime.text = formatedTime
                }

                override fun onFinish() {}
            }

            image.setOnClickListener {
                if (startMs == MIN_TIME) {
                    startMs = SystemClock.elapsedRealtime()
                    timer?.start()
                }
                clickCounter++
                if (clickCounter % 3 == 0) {
                    ballCounter++
                    val ball = "ball_$ballCounter"
                    val imageResource =
                        resources.getIdentifier(ball, "drawable", requireContext().packageName)

                    image.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            imageResource,
                            requireContext().theme
                        )
                    )
                    if (ballCounter == 30) {
                        startAnimation()
                    }
                }
            }

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun startAnimation() {
        with(binding) {
            image.setImageDrawable(null)
            image.setOnClickListener(null)
            image.setBackgroundResource(R.drawable.ball_animation)
            animationDrawable = binding.image.background as AnimationDrawable
            animationDrawable?.start()
            timer?.cancel()

            //у animationDrawable нет колбека конца, поэтому конец считаем сами
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(
                    GameBallFragmentDirections.actionGameToEndGame(
                        textGameTime.text.toString()
                    )
                )
            }, 500)
        }
    }
}