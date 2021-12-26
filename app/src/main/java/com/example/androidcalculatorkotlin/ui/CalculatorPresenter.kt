package com.example.androidcalculatorkotlin.ui

import android.util.Log
import com.example.androidcalculatorkotlin.domain.Calculator
import com.example.androidcalculatorkotlin.domain.Operation

class CalculatorPresenter(private var view: CalculatorView, private var calculator: Calculator) {


    var argOne = 0.0
    var argTwo = 0.0
    var result = 0.0
    var operation = Operation.EMPTY
    private val base = 10
    private var isDotPress = false
    private var divider = 0

    fun onDigitPressed(digit: Int) {
        if (operation == Operation.EMPTY) {
            if (isDotPress) {
                argOne += digit / divider.toDouble()
                divider *= base
            } else {
                argOne = argOne * base + digit
            }
        } else {
            if (isDotPress) {
                argTwo += digit / divider.toDouble()
                divider *= base
            } else {
                argTwo = argTwo * base + digit
            }
        }
        view.showResultWithoutEquals()
    }

    fun onOperationPressed(operation: Operation) {
        this.operation = operation
        isDotPress = false
        view.showResult()
        Log.d("myLog", "$isDotPress | $divider | $argOne")

    }

    fun onDotPressed() {
        if (!isDotPress) {
            isDotPress = true
            divider = base
            Log.d("myLog", "$isDotPress | $divider | $argOne")
        }
    }

    fun displayResult() {
        result = calculator.performOperation(argOne, argTwo, operation)
        view.showResult()
        argOne = result
        argTwo = 0.0
        result = 0.0
    }

    fun displayClear() {
        argOne = 0.0
        argTwo = 0.0
        result = 0.0
        operation = Operation.EMPTY
        isDotPress = false
        view.showResult()
    }
}