package com.example.androidcalculatorkotlin.domain

class CalculatorImp: Calculator {

    override fun performOperation(argOne: Double, argTwo: Double, operation: Operation): Double {
        return when(operation){
            Operation.PLUS -> {
                argOne + argTwo
            }
            Operation.MULTIPLICATION -> {
                argOne + argTwo
            }
            Operation.DIVISION -> {
                argOne + argTwo
            }
            Operation.DEDUCT -> {
                argOne + argTwo
            }
            else -> 0.0
        }
    }
}