package com.example.focusfinder


import android.graphics.Color
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import android.os.CountDownTimer
import android.util.Log
import android.widget.EditText
import nl.dionsegijn.konfetti.Confetti
import nl.dionsegijn.konfetti.models.Shape.Companion.CIRCLE
import nl.dionsegijn.konfetti.models.Shape.Companion.RECT
import nl.dionsegijn.konfetti.models.Size


class Timer : Fragment() {

    lateinit var time_text : TextView
    lateinit var stop_button : Button
    lateinit var start_button : Button
    lateinit var home_button : Button
    lateinit var user_input : EditText

    lateinit var countdown_timer : CountDownTimer
    lateinit var confetti: nl.dionsegijn.konfetti.KonfettiView


    var START_MILLI_SECONDS = 6000L
    var isRunning : Boolean = false
    var time_in_milli_seconds = 0L






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        time_text = view.findViewById(R.id.textView)
        home_button = view.findViewById(R.id.timer_home_button)
        stop_button = view.findViewById(R.id.timer_stop_button)
        start_button = view.findViewById(R.id.time_start_button)
        user_input = view.findViewById(R.id.enter_time_amount)
        confetti = view.findViewById(R.id.viewKonfetti)


        // nav button to dashboard
       home_button .setOnClickListener {
            findNavController().navigate(R.id.action_global_dashboard)
        }

        var currentTime = time_text.text

        start_button.setOnClickListener {
            if (isRunning) {
                pauseTimer()
                currentTime = time_text.text.toString()
                Log.d("BBBBB", currentTime as String)
            } else {
                val time = user_input.text.toString() // need to save current time so when user resumes it doesnt start from the top again
                time_in_milli_seconds = time.toLong() * 60000L
                startTimer(time_in_milli_seconds)
            }

        }

        stop_button.setOnClickListener{
            resetTimer()
        }
    }


    fun pauseTimer() {
        start_button.text = "Start"
        countdown_timer.cancel()
        isRunning = false
        stop_button.visibility = View.VISIBLE
    }

    fun startTimer(time_in_seconds : Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                loadConfeti()
            }

            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUI()
            }
        }

        countdown_timer.start()

        isRunning = true
        start_button.text = "Pause"
        stop_button.visibility = View.INVISIBLE
    }

    fun resetTimer() {
        time_in_milli_seconds = START_MILLI_SECONDS
        updateTextUI()
        stop_button.visibility = View.INVISIBLE
    }

    fun updateTextUI() {
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60

        time_text.text = "$minute:$seconds"
    }

    fun loadConfeti() {
        confetti.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(CIRCLE, RECT)
            .addSizes(Size(12))
            .setPosition(-50f, confetti.width + 50f, -50f, -50f)
            .streamFor(300, 5000L)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }
}