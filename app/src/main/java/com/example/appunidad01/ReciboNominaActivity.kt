package com.example.appunidad01

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class ReciboNominaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recibo_nomina)

        val txtNumRecibo = findViewById<EditText>(R.id.txtNumRecibo)
        val txtNombreEmpleado = findViewById<EditText>(R.id.txtNombreEmpleado)
        val txtHorasNormales = findViewById<EditText>(R.id.txtHorasNormales)
        val txtHorasExtras = findViewById<EditText>(R.id.txtHorasExtras)
        val rgbPuestos = findViewById<RadioGroup>(R.id.rgbPuestos)

        val lblSubtotal = findViewById<TextView>(R.id.lblSubtotal)
        val lblImpuesto = findViewById<TextView>(R.id.lblImpuesto)
        val lblTotal = findViewById<TextView>(R.id.lblTotal)

        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        val nombreIntent = intent.getStringExtra("NOMBRE_TRABAJADOR")
        if (!nombreIntent.isNullOrEmpty()) {
            txtNombreEmpleado.setText(nombreIntent)
        }

        btnCalcular.setOnClickListener {
            if (txtNumRecibo.text.isEmpty() || txtNombreEmpleado.text.isEmpty() ||
                txtHorasNormales.text.isEmpty() || txtHorasExtras.text.isEmpty()) {
                Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val numRecibo = txtNumRecibo.text.toString().toInt()
            val nombre = txtNombreEmpleado.text.toString()
            val horasNormales = txtHorasNormales.text.toString().toFloat()
            val horasExtras = txtHorasExtras.text.toString().toFloat()

            val puestoSeleccionado = when (rgbPuestos.checkedRadioButtonId) {
                R.id.rdbAuxiliar -> 1
                R.id.rdbAlbanil -> 2
                R.id.rdbIngObra -> 3
                else -> 1
            }

            val recibo = ReciboNomina(numRecibo, nombre, horasNormales, horasExtras, puestoSeleccionado)

            lblSubtotal.text = getString(R.string.label_subtotal) + " $" + String.format("%.2f", recibo.calcularSubtotal())
            lblImpuesto.text = getString(R.string.label_impuesto) + " $" + String.format("%.2f", recibo.calcularImpuesto())
            lblTotal.text = getString(R.string.label_total) + " $" + String.format("%.2f", recibo.calcularTotal())
        }

        btnLimpiar.setOnClickListener {
            txtNumRecibo.text.clear()
            txtNombreEmpleado.text.clear()
            txtHorasNormales.text.clear()
            txtHorasExtras.text.clear()
            rgbPuestos.check(R.id.rdbAuxiliar)
            lblSubtotal.text = getString(R.string.label_subtotal)
            lblImpuesto.text = getString(R.string.label_impuesto)
            lblTotal.text = getString(R.string.label_total)
        }

        btnRegresar.setOnClickListener {
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            builder.setTitle("Regresar")
            builder.setMessage("¿Desea regresar?")

            builder.setPositiveButton("Regresar") { dialog, which ->
                finish()
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

            builder.show()
        }
    }
}