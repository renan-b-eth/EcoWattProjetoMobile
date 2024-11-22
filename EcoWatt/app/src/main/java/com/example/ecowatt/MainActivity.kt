package com.example.ecowatt

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecowatt.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogin.setOnClickListener{
            cadastroUsuario();
        }
    }

    private fun cadastroUsuario(){
        val email = "adm@adm.com"
        val senha = "adm"

        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email,senha).addOnSuccessListener {authResult ->
            val email = authResult.user?.email
            val id = authResult.user?.uid

            Toast.makeText(this, "Logado", Toast.LENGTH_SHORT).show();
        }.addOnFailureListener{exception->
            val menErro = exception.message
            Toast.makeText(this, menErro, Toast.LENGTH_SHORT).show();
        }

    }

}