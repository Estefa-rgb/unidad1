package com.example.appunidad01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class appBanco : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_banco)

        val txtUser = findViewById<EditText>(R.id.txtUser)
        val txtPassword = findViewById<EditText>(R.id.txtPassword)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        findViewById<TextView>(R.id.tvEmpresa).text = getString(R.string.nombre_banco)
        findViewById<TextView>(R.id.tvDomicilio).text = getString(R.string.domicilio_banco)

        btnIngresar.setOnClickListener {
            val user = txtUser.text.toString()
            val pass = txtPassword.text.toString()

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Captura usuario y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (user == getString(R.string.user_banco) && pass == getString(R.string.pass_banco)) {
                val intent = Intent(this, CuentBancoActivity::class.java)
                intent.putExtra("usuario", user)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}