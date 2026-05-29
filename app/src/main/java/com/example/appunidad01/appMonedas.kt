package com.example.appunidad01

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class appMonedas : AppCompatActivity() {
    private lateinit var txtCantidad : EditText
    private lateinit var spnMonedas: Spinner
    private lateinit var txtResultado: TextView
    private lateinit var btnConvertir : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnSalir : Button
    private var pos : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_monedas)
        iniciarComponentes()
        eventoClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        txtCantidad = findViewById<EditText>(R.id.txtCantidad)
        txtResultado = findViewById<TextView>(R.id.txtResultado)
        spnMonedas = findViewById<Spinner>(R.id.spnMonedas)
        btnConvertir = findViewById<Button>(R.id.btnConvertir)
        btnSalir = findViewById<Button>(R.id.btnSalir)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)


        val items= resources.getStringArray(R.array.monedas)
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, items)
        spnMonedas.adapter = adapter
    }

    fun eventoClic(){
        //buscar la posición del elemento seleccionado
        spnMonedas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                pos = p2
            }

            override fun equals(other: Any?): Boolean {
                return super.equals(other)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        btnConvertir.setOnClickListener {
            //validar
            if (txtCantidad.text.toString().contentEquals("")){
                Toast.makeText(this,R.string.strMensaje,
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var dolarA : Float = getString(R.string.dolarA).toFloat()
            var dolarC : Float = getString(R.string.dolarC).toFloat()
            var euro : Float = getString(R.string.euro).toFloat()
            var libra : Float = getString(R.string.libra).toFloat()
            var cantMx : Float = txtCantidad.text.toString().toFloat()

            var resultado = when(pos){
                0-> cantMx/dolarA
                1-> cantMx/dolarC
                2-> cantMx/euro
                3-> cantMx/libra
                else -> 0.0f
            }
            txtResultado.setText(resultado.toString())
        }

        btnLimpiar.setOnClickListener {
            txtCantidad.setText("")
            txtResultado.setText(R.string.strResultado)
            spnMonedas.setSelection(0)
            pos = 0
            txtCantidad.requestFocus()
        }

        btnSalir.setOnClickListener {
            val builder = AlertDialog.Builder(this)

            builder.setTitle(getString(R.string.strConMonedas))
            builder.setMessage(getString(R.string.strMenSalir))

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