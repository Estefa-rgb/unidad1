package com.example.appunidad01.com.example.appunidad01

class ReciboNomina (
    var numRecibo: Int,
    var nombre: String,
    var horasTrabNormal: Float,
    var horasTrabExtras: Float,
    var puesto: Int
) {
    fun calcularSubtotal(): Float {
    val pagoBase = 200f
    var pagoPorHora = pagoBase

    when (puesto) {
        1 -> pagoPorHora += pagoBase * 0.20f
        2 -> pagoPorHora += pagoBase * 0.50f
        3 -> pagoPorHora += pagoBase * 1.00f
    }

    val pagoNormales = horasTrabNormal * pagoPorHora
    val pagoExtras = horasTrabExtras * (pagoPorHora * 2)

    return pagoNormales + pagoExtras
}

    fun calcularImpuesto(): Float {
        return calcularSubtotal() * 0.16f
    }

    fun calcularTotal(): Float {
        return calcularSubtotal() - calcularImpuesto()
    }
}