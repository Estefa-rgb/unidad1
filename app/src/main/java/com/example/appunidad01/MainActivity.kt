package com.example.appunidad01

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var txtSaludo : EditText
    private lateinit var btnSaludar : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        iniciarComponentes()
        eventoClick()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes() {
        txtSaludo = findViewById<EditText>(R.id.txtNombr)
        btnSaludar = findViewById<Button>(R.id.btnPulsar)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)
    }

    fun eventoClick() {

        btnSaludar.setOnClickListener (View.OnClickListener{

            var strNombre : String = ""
            if (txtSaludo.text.toString().contentEquals("")) {
                Toast.makeText(applicationContext, "Nombre no capturado", Toast.LENGTH_SHORT).show()
            } else {
                strNombre = "Hola " + txtSaludo.text.toString() + " como estas"
                txtSaludo.setText(strNombre)
            }
        })

        btnLimpiar.setOnClickListener {
            txtSaludo.setText("")
        }

        btnCerrar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("app Hola")
            builder.setMessage("¿deseas cerrar la aplicación")
            builder.setPositiveButton("Aceptar"){
                dialog , wich -> finish()
            }
            builder.setNegativeButton("cancelar"){
                dialog , which ->
                Toast.makeText(applicationContext, "Continuamos en la app",Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

    }
}