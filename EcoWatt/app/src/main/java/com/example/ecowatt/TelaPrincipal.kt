package com.example.ecowatt

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TelaPrincipal : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textView = findViewById<TextView>(R.id.txtView1)
        generateRandomNumber(textView)

        val textView2 = findViewById<TextView>(R.id.txtView2)
        generateRandomNumber2(textView2)
    }

    fun generateRandomNumber(textView: TextView) {
        val handler = Handler(Looper.getMainLooper())
        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            while (true) {
                val randomNum = (0..500).random()
                delay(2000) // Aguarda 2 segundos

                withContext(Dispatchers.Main) {
                    textView.text = randomNum.toString()
                }
            }
        }
    }

    fun generateRandomNumber2(textView: TextView) {
        val handler = Handler(Looper.getMainLooper())
        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            while (true) {
                val randomNum = (0..3000).random()
                delay(800)

                withContext(Dispatchers.Main) {
                    textView.text = randomNum.toString()
                }
            }
        }
    }


}