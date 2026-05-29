package com.example.appunidad01

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class appOperaciones : AppCompatActivity() {
    private lateinit var txtNum1: EditText
    private lateinit var txtNum2: EditText
    private lateinit var spnOperaciones: Spinner
    private lateinit var txtResultado: TextView
    private lateinit var imgOperacion: ImageView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    private var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_operaciones)
        iniciarComponentes()
        eventoClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes() {
        txtNum1 = findViewById<EditText>(R.id.txtNum1)
        txtNum2 = findViewById<EditText>(R.id.txtNum2)
        spnOperaciones = findViewById<Spinner>(R.id.spnOperaciones)
        txtResultado = findViewById<TextView>(R.id.txtResultado)
        imgOperacion = findViewById<ImageView>(R.id.imgOperacion)
        btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)

        val items = resources.getStringArray(R.array.operaciones)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        spnOperaciones.adapter = adapter
    }

    fun eventoClic() {
        spnOperaciones.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                pos = p2
                when (pos) {
                    0 -> imgOperacion.setImageResource(R.mipmap.suma)
                    1 -> imgOperacion.setImageResource(R.mipmap.resta)
                    2 -> imgOperacion.setImageResource(R.mipmap.multiplicar)
                    3 -> imgOperacion.setImageResource(R.mipmap.dividir)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        btnCalcular.setOnClickListener {
            if (txtNum1.text.toString().contentEquals("") || txtNum2.text.toString()
                    .contentEquals("")
            ) {
                Toast.makeText(this, R.string.strErrorVacio, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var num1: Float = txtNum1.text.toString().toFloat()
            var num2: Float = txtNum2.text.toString().toFloat()

            if (num1 == 0.0f || num2 == 0.0f) {
                Toast.makeText(this, R.string.strErrorCero, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var resultado: Float = 0.0f

            when (pos) {
                0 -> resultado = num1 + num2
                1 -> resultado = num1 - num2
                2 -> resultado = num1 * num2
                3 -> resultado = num1 / num2
            }

            txtResultado.setText(String.format("%.2f", resultado))
        }

        btnLimpiar.setOnClickListener {
            txtNum1.setText("")
            txtNum2.setText("")
            txtResultado.setText(R.string.strResultado)
            spnOperaciones.setSelection(0)
            pos = 0
            imgOperacion.setImageResource(R.mipmap.operaciones) // Vuelve a la imagen base
            txtNum1.requestFocus()
        }

        btnCerrar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.strOpBasicas))
            builder.setMessage(getString(R.string.strMenOperaciones))

            builder.setPositiveButton(getString(R.string.strcerrar)) { _, _ ->
                finish()
            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            builder.show()
        }
    }
}