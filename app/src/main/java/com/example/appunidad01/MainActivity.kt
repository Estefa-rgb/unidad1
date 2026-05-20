package com.example.appunidad01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var txtNombre: EditText
    private lateinit var btnPulsar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        txtNombre = findViewById(R.id.txtNombre)
        btnPulsar = findViewById(R.id.btnPulsar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)

        btnPulsar.setOnClickListener {
            val nombre = txtNombre.text.toString()

            if (nombre.isEmpty()) {
                Toast.makeText(this, "Escribe tu nombre", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Hola $nombre", Toast.LENGTH_SHORT).show()
            }
        }

        btnLimpiar.setOnClickListener {
            txtNombre.text.clear()
        }

        btnCerrar.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}