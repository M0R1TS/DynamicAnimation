package ru.devsokovix.animation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import ru.devsokovix.animation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val flingX = FlingAnimation(binding.fling, DynamicAnimation.ROTATION).apply {
            friction = 1.1f
        }
        val flingY = FlingAnimation(binding.fling, DynamicAnimation.ROTATION).apply {
            friction = 1.1f
        }

        val gesturesListener = object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                flingX.setStartVelocity(velocityX)
                flingY.setStartVelocity(velocityY)

                flingX.start()
                flingY.start()

                return true
            }
        }

        val gestureDetector = GestureDetector(this, gesturesListener)

        binding.fling.setOnTouchListener { v, event ->
            v.performClick()
            gestureDetector.onTouchEvent(event)
        }

    }

}
