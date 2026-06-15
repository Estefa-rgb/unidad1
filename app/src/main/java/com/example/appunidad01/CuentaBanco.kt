package com.example.appunidad01

class CuentaBanco(
    var numCuenta: String,
    var nombre: String,
    var banco: String,
    var saldo: Float
) {
    fun obtenerSaldo(): Float {
        return saldo
    }

    fun retirarDinero(cantidad: Float): Boolean {
        if (cantidad <= saldo) {
            saldo -= cantidad
            return true
        }
        return false
    }

    fun hacerDeposito(cantidad: Float) {
        saldo += cantidad
    }
}