package com.example.appunidad01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class appimc : AppCompatActivity() {

    private lateinit var txtAltura: EditText
    private lateinit var txtPeso: EditText
    private lateinit var txtRes: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_appimc)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        iniciarComponentes()
        eventosClick()
    }

    private fun iniciarComponentes() {
        txtAltura = findViewById(R.id.txtAltura)
        txtPeso = findViewById(R.id.txtPeso)
        txtRes = findViewById(R.id.txtResultado)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
    }

    private fun eventosClick() {
        btnCalcular.setOnClickListener {
            val alturaStr = txtAltura.text.toString()
            val pesoStr = txtPeso.text.toString()

            var esValido = true

            if (alturaStr.isEmpty()) {
                txtAltura.error = "La altura es obligatoria"
                esValido = false
            } else {
                val alturaNum = alturaStr.toFloatOrNull()
                if (alturaNum == null || alturaNum <= 0) {
                    txtAltura.error = "Ingresa una altura válida (ej. 1.75)"
                    esValido = false
                }
            }

            if (pesoStr.isEmpty()) {
                txtPeso.error = "El peso es obligatorio"
                esValido = false
            } else {
                val pesoNum = pesoStr.toFloatOrNull()
                if (pesoNum == null || pesoNum <= 0) {
                    txtPeso.error = "Ingresa un peso válido"
                    esValido = false
                }
            }

            // 3. Si todo está correcto, hacemos el cálculo matemático
            if (esValido) {
                val altura = alturaStr.toFloat()
                val peso = pesoStr.toFloat()

                val imc = peso / (altura * altura)
                txtRes.text = "Tu IMC es: ${String.format("%.2f", imc)}"
            } else {
                txtRes.text = "Verifica los datos en rojo"
            }
        }

        btnLimpiar.setOnClickListener {
            txtAltura.text.clear()
            txtPeso.text.clear()

            txtAltura.error = null
            txtPeso.error = null

            txtRes.text = "Su resultado aparecerá aquí"
        }

        btnCerrar.setOnClickListener {
            finish()
        }
    }
}