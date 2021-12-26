package com.example.androidcalculatorkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.androidcalculatorkotlin.R
import com.example.androidcalculatorkotlin.domain.CalculatorImp
import com.example.androidcalculatorkotlin.domain.Operation
import java.util.HashMap

class CalculatorActivity : AppCompatActivity(), CalculatorView {

    private lateinit var argOneField: TextView
    private lateinit var argTwoField: TextView
    private lateinit var operatorField: TextView
    private lateinit var txtResult: TextView
    private val presenter: CalculatorPresenter = CalculatorPresenter(this, CalculatorImp())
    private var digits: Map<Int, Int> = HashMap()
    private var operators: Map<Int, Operation> = HashMap<Int, Operation>()
    private val saveArgOne = "SAVE_ARG_ONE"
    private val saveArgTwo = "SAVE_ARG_TWO"
    private val saveResult = "SAVE_RESULT"
    private val saveOperation = "SAVE_OPERATION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        putKey()
        initTextView()
        showResult()

        val digitsPress =
            View.OnClickListener { view: View ->
                presenter.onDigitPressed(
                    digits[view.id]!!
                )
            }
        val operatorsPress =
            View.OnClickListener { view: View ->
                presenter.onOperationPressed(
                    operators[view.id]!!
                )
            }
        initKey(digitsPress, operatorsPress)
        findViewById<View>(R.id.key_dot).setOnClickListener { presenter.onDotPressed() }
        findViewById<View>(R.id.key_equal).setOnClickListener { presenter.displayResult() }
        findViewById<View>(R.id.key_clear).setOnClickListener { presenter.displayClear() }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(saveArgOne, presenter.argOne)
        outState.putDouble(saveArgTwo, presenter.argTwo)
        outState.putDouble(saveResult, presenter.result)
        outState.putSerializable(saveOperation, presenter.operation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        presenter.argOne = (savedInstanceState.getDouble(saveArgOne))
        presenter.argTwo = (savedInstanceState.getDouble(saveArgTwo))
        presenter.result = (savedInstanceState.getDouble(saveResult))
        presenter.operation = (savedInstanceState.getSerializable(saveOperation) as Operation)
        showResult()
    }

    override fun showResult() {
        argOneField.text = presenter.argOne.toString()
        argTwoField.text = presenter.argTwo.toString()
        operatorField.text = presenter.operation.toString()
        txtResult.text = presenter.result.toString()
    }

    override fun showResultWithoutEquals() {
        argOneField.text = presenter.argOne.toString()
        argTwoField.text = presenter.argTwo.toString()
        operatorField.text = presenter.operation.toString()
    }

    private fun putKey() {
        digits = mapOf(
            R.id.key_0 to 0,
            R.id.key_1 to 1,
            R.id.key_2 to 2,
            R.id.key_3 to 3,
            R.id.key_4 to 4,
            R.id.key_5 to 5,
            R.id.key_6 to 6,
            R.id.key_7 to 7,
            R.id.key_8 to 8,
            R.id.key_9 to 9,
        )
        operators = mapOf(
            R.id.key_plus to Operation.PLUS,
            R.id.key_multiply to Operation.MULTIPLICATION,
            R.id.key_deduct to Operation.DEDUCT,
            R.id.key_divide to Operation.DIVISION
        )
    }

    private fun initTextView() {
        argOneField = findViewById(R.id.argOneField)
        argTwoField = findViewById(R.id.argTwoField)
        operatorField = findViewById(R.id.operatorField)
        txtResult = findViewById(R.id.resultField)
    }

    private fun initKey(digitsPress: View.OnClickListener?, operatorsPress: View.OnClickListener?) {
        findViewById<View>(R.id.key_0).setOnClickListener(digitsPress)
        findViewById<View>(R.id.key_1).setOnClickListener(digitsPress)
        findViewById<View>(R.id.key_2).setOnClickListener(digitsPress)
        findViewById<View>(R.id.key_3).setOnClickListener(digitsPress)
        findViewById<View>(R.id.key_4).setOnClickListener(digitsPress)
        findViewById<View>(R.id.key_5).setOnClickListener(digitsPress)
        findViewById<View>(R.id.key_6).setOnClickListener(digitsPress)
        findViewById<View>(R.id.key_7).setOnClickListener(digitsPress)
        findViewById<View>(R.id.key_8).setOnClickListener(digitsPress)
        findViewById<View>(R.id.key_9).setOnClickListener(digitsPress)
        findViewById<View>(R.id.key_plus).setOnClickListener(operatorsPress)
        findViewById<View>(R.id.key_multiply).setOnClickListener(operatorsPress)
        findViewById<View>(R.id.key_deduct).setOnClickListener(operatorsPress)
        findViewById<View>(R.id.key_divide).setOnClickListener(operatorsPress)
    }
}