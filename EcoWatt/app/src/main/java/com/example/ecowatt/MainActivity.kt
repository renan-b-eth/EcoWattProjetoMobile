package com.example.ecowatt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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
            cadastroUsuario2()
        }
    }

    private fun cadastroUsuario() {
        val email = "adm@adm.com"
        val senha = "adm"

        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, senha).addOnSuccessListener { authResult ->
            val email = authResult.user?.email
            val id = authResult.user?.uid

            Toast.makeText(this, "Logado", Toast.LENGTH_SHORT).show();
        }.addOnFailureListener { exception ->
            val menErro = exception.message
            Toast.makeText(this, menErro, Toast.LENGTH_SHORT).show();
        }




    }

    private fun cadastroUsuario2() {
        var email2 = findViewById<EditText>(R.id.edtEmail)
        var senha2 = findViewById<EditText>(R.id.edtSenha)

        val button = findViewById<Button>(R.id.btnLogin)
        button.setOnClickListener {
            val emailPadrao = "adm2@adm.com"
            val senhaPadrao = "adm"
            val email = email2.text.toString();
            val senha = senha2.text.toString();
            if (email.equals(emailPadrao) && senha.equals(senhaPadrao) || email.equals(emailPadrao) && senha.equals(
                    "adm2"
                )
            ) {
                Log.i("teste", "LOGADO") /*IR PARA A TELA*/
                Toast.makeText(this, "Logado", Toast.LENGTH_SHORT).show();
                val intent = Intent(this, TelaPrincipal::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "EMAIL OU SENHA ERRADA", Toast.LENGTH_SHORT).show()
            }


        }
    }





}