package com.example.androidcalculatorkotlin.domain

enum class Operation {

    EMPTY {
        override fun toString(): String { return "" }
    },
    PLUS {
        override fun toString(): String { return "+" }
    },
    DEDUCT {
        override fun toString(): String { return "-" }
    },
    DIVISION {
        override fun toString(): String { return "÷" }
    },
    MULTIPLICATION {
        override fun toString(): String { return "x" }
    }
}