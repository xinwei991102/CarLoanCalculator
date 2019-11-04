package com.example.carloancalculator

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener {
            //TODO: get all inputs from user and perform calculation
           checkValid()
        }

    }

    private fun checkValid() {
        if(editTextCarPrice.text.isBlank()) {
            editTextCarPrice.setError(getString(R.string.error))
        }
        if(editTextInterestRate.text.isBlank()) {
            editTextInterestRate.setError(getString(R.string.error))
        }
        if(editTextDownPayment.text.isBlank()) {
            editTextDownPayment.setError(getString(R.string.error))
        }
        if(editTextLoanPeriod.text.isBlank()) {
            editTextLoanPeriod.setError(getString(R.string.error))
        }
        if(!editTextCarPrice.text.isBlank() && !editTextInterestRate.text.isBlank() && !editTextDownPayment.text.isBlank() && !editTextLoanPeriod.text.isBlank()) {
            calculateLoan()
        }
    }

    private fun calculateLoan() {
        val carPrice = editTextCarPrice.text.toString().toFloat()
        val downPayment = editTextDownPayment.text.toString().toInt()
        val loanPeriod = editTextLoanPeriod.text.toString().toInt()
        val interestRate = editTextInterestRate.text.toString().toFloat()

        val currency = Currency.getInstance(Locale.getDefault())
        val symbol = currency.symbol

        var carLoan = carPrice - downPayment
        textViewLoan.setText(getString(R.string.loan) + symbol + "${carLoan}")

        var interest = carLoan * interestRate * loanPeriod
        textViewInterest.setText(getString(R.string.interest) + symbol + "${interest}")

        var monthlyRepayment = (carLoan + interest) / loanPeriod / 12
        textViewMonthlyRepayment.setText(getString(R.string.monthly_repayment) + symbol + "${monthlyRepayment}")

    }

    fun resetInput(view: View) {
        editTextCarPrice.setText("")
        editTextDownPayment.setText("")
        editTextInterestRate.setText("")
        editTextLoanPeriod.setText("")
        textViewLoan.setText(getString(R.string.loan))
        textViewInterest.setText(getString(R.string.interest))
        //TODO: Clear all inputs and outputs
    }

}