package com.example.androidcalculatorkotlin.domain

interface Calculator {

    fun performOperation(argOne: Double, argTwo: Double, operation: Operation): Double
}