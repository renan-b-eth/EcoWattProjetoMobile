package com.example.ecowatt

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecowatt.databinding.ActivityHistoricoConsumoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HistoricoConsumo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historico_consumo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        val button = findViewById<Button>(R.id.btnInserir)
        val button2 = findViewById<Button>(R.id.btnListar)
        val editText2 = findViewById<EditText>(R.id.edtInserir)

        button.setOnClickListener{
            Toast.makeText(this, "Consumo Inserido com Sucesso", Toast.LENGTH_SHORT).show();
            salvarConsumo()
        }

        button2.setOnClickListener{
            val texto = editText2.text.toString()
            val db = FirebaseFirestore.getInstance()
            val documentReference = db.collection("consumos").document("21IOHzeIOXIoOUMJNVi6")
            val dataToUpdate = mutableMapOf<String, Any>()
            dataToUpdate["consumo"] = texto


        }
    }
    fun showCustomDialog(context: Context, title: String, hint: String, onTextEntered: (String) -> Unit) {
        val dialog = Dialog(context)
        val view = LayoutInflater.from(context).inflate(androidx.core.R.layout.custom_dialog, null)
        val editText = view.findViewById<EditText>(R.id.edtInserir)


        dialog.setContentView(view)
        dialog.show()
    }

    private val binding by lazy {
        ActivityHistoricoConsumoBinding.inflate(layoutInflater)
    }

    private val autenticacao by lazy{
        FirebaseAuth.getInstance()
    }

    private val bancoDados by lazy{
        FirebaseFirestore.getInstance()
    }

    private fun salvarConsumo(){
        val dados = mapOf(
            "consumo" to binding.edtInserir.text.toString()
        )
        val referenceConsumo = bancoDados.collection("consumos")
        referenceConsumo.add(dados)
    }

        suspend fun fetchDataCoroutine() {
        val db = Firebase.firestore

        val documentSnapshot = db.collection("consumos").document("1").get().await()
        if (documentSnapshot.exists()) {
            val name = documentSnapshot.getString("consumo")
            Toast.makeText(this, "Consumo Encontrado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Consumo Inexistente", Toast.LENGTH_SHORT).show();
        }
    }

}