package com.example.appunidad01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CuentBancoActivity : AppCompatActivity() {

    private var miCuenta: CuentaBanco? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuent_banco)

        val lblNombre = findViewById<TextView>(R.id.lblNombre)
        val txtNumCuenta = findViewById<EditText>(R.id.txtNumCuenta)
        val txtNombreCliente = findViewById<EditText>(R.id.txtNombreCliente)
        val txtBanco = findViewById<EditText>(R.id.txtBanco)
        val txtSaldo = findViewById<EditText>(R.id.txtSaldo)
        val txtCantidad = findViewById<EditText>(R.id.txtCantidad)
        val rgMovimientos = findViewById<RadioGroup>(R.id.rgMovimientos)
        val tvNuevoSaldo = findViewById<TextView>(R.id.tvNuevoSaldo)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnAplicar = findViewById<Button>(R.id.btnAplicar)

        val usuario = intent.getStringExtra("usuario") ?: ""
        lblNombre.text = "Usuario: $usuario"

        btnRegistrar.setOnClickListener {
            val numCuenta = txtNumCuenta.text.toString()
            val nombre = txtNombreCliente.text.toString()
            val banco = txtBanco.text.toString()
            val saldoStr = txtSaldo.text.toString()

            if (numCuenta.isEmpty() || nombre.isEmpty() || banco.isEmpty() || saldoStr.isEmpty()) {
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            miCuenta = CuentaBanco(numCuenta, nombre, banco, saldoStr.toFloat())
            tvNuevoSaldo.text = "Nuevo Saldo: $${miCuenta!!.obtenerSaldo()}"
            Toast.makeText(this, "Cuenta registrada", Toast.LENGTH_SHORT).show()
        }

        btnAplicar.setOnClickListener {
            if (miCuenta == null) {
                Toast.makeText(this, "Registra la cuenta", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedId = rgMovimientos.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Selecciona movimiento", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cantidadStr = txtCantidad.text.toString()
            val rbtnSelected = findViewById<RadioButton>(selectedId)
            val movimiento = rbtnSelected.text.toString()

            if (movimiento != "Consultar Saldo" && cantidadStr.isEmpty()) {
                Toast.makeText(this, "Captura cantidad", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cantidad = if (cantidadStr.isNotEmpty()) cantidadStr.toFloat() else 0f

            when (movimiento) {
                "Deposito" -> miCuenta!!.hacerDeposito(cantidad)
                "Retiro" -> {
                    if (!miCuenta!!.retirarDinero(cantidad)) {
                        Toast.makeText(this, "Saldo insuficiente", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            tvNuevoSaldo.text = "Nuevo Saldo: $${miCuenta!!.obtenerSaldo()}"
            txtCantidad.text.clear()
        }
    }
}