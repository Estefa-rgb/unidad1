package com.example.appunidad01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class calculoNomina : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo_nomina)

        val txtNomExamen2 = findViewById<EditText>(R.id.txtNomExamen2)
        val btnEntrarExamen2 = findViewById<Button>(R.id.btnEntrarExamen2)
        val btnSalirExamen2 = findViewById<Button>(R.id.btnSalirExamen2)

        btnEntrarExamen2.setOnClickListener {
            val nombre = txtNomExamen2.text.toString().trim()
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ReciboNominaActivity::class.java)
                intent.putExtra("NOMBRE_TRABAJADOR", nombre)
                startActivity(intent)
            }
        }

        btnSalirExamen2.setOnClickListener {
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            builder.setTitle("Salir")
            builder.setMessage("¿Desea salir de la aplicacion?")

            builder.setPositiveButton("Cerrar") { dialog, which ->
                finish()
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

            builder.show()
        }

    }
}