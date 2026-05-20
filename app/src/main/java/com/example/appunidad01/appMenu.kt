package com.example.appunidad01

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class appMenu : AppCompatActivity() {

    private lateinit var crvHola: CardView
    private lateinit var crvImc: CardView
    private lateinit var crvGrados: CardView
    private lateinit var crvMonedas: CardView
    private lateinit var crvCotizacion: CardView
    private lateinit var crvSpinner: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_app_menu)

        iniciarComponentes()
        eventoClick()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun iniciarComponentes() {
        crvHola = findViewById(R.id.crvHola)
        crvImc = findViewById(R.id.crvImc)
        crvGrados = findViewById(R.id.crvGrados)
        crvMonedas = findViewById(R.id.crvMonedas)
        crvCotizacion = findViewById(R.id.crvCotizacion)
        crvSpinner = findViewById(R.id.crvSpinner)
    }

    private fun eventoClick() {
        crvHola.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}