package com.example.appunidad01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
    private lateinit var crvExamen: CardView
    private lateinit var crvPreExamen1: CardView
    private lateinit var crvPreExamen2: CardView
    private lateinit var crvSalir: CardView

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
        crvExamen = findViewById(R.id.crvExamen)
        crvPreExamen1 = findViewById(R.id.crvPreExamen1)
        crvPreExamen2 = findViewById(R.id.crvPreExamen2)
        crvSalir = findViewById(R.id.crvSalir)
    }

    private fun eventoClick() {

        crvHola.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        crvImc.setOnClickListener {
            val intent = Intent(this, appimc::class.java)
            startActivity(intent)
        }

        crvGrados.setOnClickListener {
            val intent = Intent(this, appGrados::class.java)
            startActivity(intent)
        }

        crvMonedas.setOnClickListener {
            val intent = Intent(this, appMonedas::class.java)
            startActivity(intent)
        }

        crvSalir.setOnClickListener {
            val builder = AlertDialog.Builder(this)

            builder.setTitle("App")
            builder.setMessage("¿Deseas cerrar la aplicación?")

            builder.setPositiveButton("Aceptar") { dialog, which ->
                finish()
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    "Continuemos en la app",
                    Toast.LENGTH_SHORT
                ).show()
            }

            builder.show()
        }
        crvPreExamen1.setOnClickListener {
            val intent = Intent(this, appOperaciones::class.java)
            startActivity(intent)
        }
        crvPreExamen2.setOnClickListener {
            val intent = Intent(this, calculoNomina::class.java)
            startActivity(intent)
        }

        crvCotizacion.setOnClickListener {
            val intent = Intent(this, clienteActivity::class.java)
            startActivity(intent)
        }

        crvExamen.setOnClickListener {
            val intent = Intent(this, appBanco::class.java)
            startActivity(intent)
        }

        }


}

