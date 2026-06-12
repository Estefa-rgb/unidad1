package com.example.appunidad01

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appunidad01.com.example.appunidad01.Cotizacion
import kotlin.math.abs

class cotizacionActivity: AppCompatActivity() {
    private lateinit var txtCliente: TextView
    private lateinit var txtFolio: TextView
    private lateinit var txtDescripcion: EditText
    private lateinit var txtPrecio: EditText
    private lateinit var txtPorPagI: EditText
    private lateinit var rdb12: RadioButton
    private lateinit var rdb24: RadioButton
    private lateinit var rdb36: RadioButton
    private lateinit var rdb48: RadioButton
    private lateinit var txtPagoInical: TextView
    private lateinit var txtTotalFin: TextView
    private lateinit var txtPagoMensual: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cotizacion)

        inciarComponentes()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun inciarComponentes(){
        txtCliente = findViewById(R.id.txtCliente) as TextView
        txtFolio = findViewById(R.id.txtFolio) as TextView
        txtDescripcion = findViewById(R.id.txtDescripcion) as EditText
        txtPrecio = findViewById(R.id.txtPrecio) as EditText
        txtPorPagI = findViewById(R.id.txtPorcentaje) as EditText
        txtPagoInical = findViewById(R.id.txtPagoInicial) as TextView
        txtPagoMensual = findViewById(R.id.txtPagoMensual) as TextView
        txtTotalFin = findViewById(R.id.txtTotalFin) as TextView

        rdb12 = findViewById(R.id.rdb12) as RadioButton
        rdb24 = findViewById(R.id.rdb24) as RadioButton
        rdb36 = findViewById(R.id.rdb36) as RadioButton
        rdb48 = findViewById(R.id.rdb48) as RadioButton

        btnCalcular = findViewById(R.id.btnCalcular) as Button
        btnLimpiar = findViewById(R.id.btnLimpiar) as Button
        btnCerrar = findViewById(R.id.btnCerrar) as Button

        var strCliente: String = intent.getStringExtra("cliente").toString()
        txtCliente.text = strCliente.toString()

        var folio: Int = abs(Cotizacion().generaFolio())
        txtFolio.text = "Folio:" + folio.toString()
    }

    public fun eventosClic(){
        btnCalcular.setOnClickListener(View.OnClickListener {
            var cotizacion = Cotizacion()

            if(txtDescripcion.text.toString().contentEquals("") ||
                txtPrecio.text.toString().contentEquals("") ||
                txtPorPagI.text.toString().contentEquals("")){
                Toast.makeText( this, "Falto capturar algun dato", Toast.LENGTH_SHORT).show()
            }
            else {
                txtFolio.text = cotizacion.generaFolio().toString()
                cotizacion.descripcion = txtDescripcion.text.toString()
                cotizacion.precio = txtPrecio.text.toString().toFloat()
                cotizacion.porPagInicial = txtPorPagI.text.toString().toFloat()

                //plazos
                if(rdb12.isChecked) cotizacion.plazos = 12
                if(rdb24.isChecked) cotizacion.plazos = 24
                if(rdb36.isChecked) cotizacion.plazos = 36
                if(rdb48.isChecked) cotizacion.plazos = 48

                // calculos
                txtPagoInical.text = "Pago Inicial" + ":" + cotizacion.calcularPagoInicial().toString() + ":"
                txtTotalFin.text = "Total a Financiar" + ":" + cotizacion.calcularTotalFin().toString()
                txtPagoMensual.text = "Pago Mensual" + ":" + cotizacion.calcularPagoMensual().toString()
            }
        })

        btnLimpiar.setOnClickListener(View.OnClickListener {
            //Limpiar TextView
            txtFolio.text=""
            txtPagoInical.text="Pago Inicial"
            txtTotalFin.text = "Total a Financiar"
            txtPagoMensual.text = "Pago Mensual"
            // limpiar EditText
            txtDescripcion.setText("")
            txtPrecio.setText("")
            txtPorPagI.setText("")
            // Activar el radio12Meses
            rdb12.isChecked=true
        })

        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Cotización")
            builder.setMessage("¿Deseas salir de la aplicación Cotización?")

            builder.setPositiveButton("Cerrar") { _, _ ->
                finish()
            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            builder.show()
        })
    }
}