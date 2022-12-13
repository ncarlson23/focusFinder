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
import android.os.SystemClock
import android.util.Log
import android.widget.EditText
import nl.dionsegijn.konfetti.Confetti
import nl.dionsegijn.konfetti.models.Shape.Companion.CIRCLE
import nl.dionsegijn.konfetti.models.Shape.Companion.RECT
import nl.dionsegijn.konfetti.models.Size


class Timer : Fragment() {

    lateinit var home_button : Button
    lateinit var timer : TextView
    lateinit var start_button : Button
    lateinit var reset_button : Button
    lateinit var time_text : EditText

    lateinit var countdown_timer : CountDownTimer
    lateinit var confetti: nl.dionsegijn.konfetti.KonfettiView


    var START_MILLI_SECONDS = 0L  // reset back to 0:0
    var isRunning : Boolean = false
    var time_in_milli_seconds = 0L
    var pausedTime : Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        home_button = view.findViewById(R.id.timer_home_button)
        timer = view.findViewById(R.id.timer)
        start_button = view.findViewById(R.id.button)
        reset_button = view.findViewById(R.id.reset)
        time_text = view.findViewById(R.id.time_edit_text)
        confetti = view.findViewById(R.id.viewKonfetti)



        // nav button to dashboard
        home_button.setOnClickListener {
            findNavController().navigate(R.id.action_global_dashboard)
        }

        //var currentTime = time_text.text

        start_button.setOnClickListener {
            if (isRunning) {
                pauseTimer()
                // currentTime
                //Log.d("BBBBB", currentTime.toString())
            } else {
                // need to save current time so when user resumes it doesn't start from the top again
                // FIX THIS BUG
//                pausedTime = SystemClock.elapsedRealtime()
//                val elapsedTime = SystemClock.elapsedRealtime() - pausedTime
                val time = time_text.text.toString()
                Log.d("BBBBB", timer.text.toString())
                time_in_milli_seconds = time.toLong() * 60000L
                startTimer(time_in_milli_seconds)
            }

        }

        reset_button.setOnClickListener {
            resetTimer()
        }
    }

    fun pauseTimer() {
        start_button.text = "Start"
        countdown_timer.cancel()
        isRunning = false
        reset_button.visibility = View.VISIBLE
    }

    fun startTimer(time_in_seconds : Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                timer.text = "done!"
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
        reset_button.visibility = View.INVISIBLE
    }

    // fixed
    fun resetTimer() {
        time_in_milli_seconds = START_MILLI_SECONDS
        updateTextUI()
        reset_button.visibility = View.INVISIBLE
    }

    fun updateTextUI() {
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60

        timer.text = "$minute:$seconds"
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