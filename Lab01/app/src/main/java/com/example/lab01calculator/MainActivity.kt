package com.example.lab01calculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtCheckBase.setText("10") //default value

        hexLayout.setOnClickListener {
            baseSelect(16)
            txtResult.setText(txtHexValue.text.toString().toUpperCase())
            hexLayout.setBackgroundColor(Color.parseColor("#F57C00"));
        }

        decLayout.setOnClickListener {
            baseSelect(10)
            txtResult.setText(txtDecValue.text.toString())
            decLayout.setBackgroundColor(Color.parseColor("#F57C00"));
        }

        octLayout.setOnClickListener {
            baseSelect(8)
            txtResult.setText(txtOctValue.text.toString())
            octLayout.setBackgroundColor(Color.parseColor("#F57C00"));
        }
        binLayout.setOnClickListener {
            baseSelect(2)
            txtResult.setText(txtBinValue.text.toString())
            binLayout.setBackgroundColor(Color.parseColor("#F57C00"));
        }

    }

    fun baseSelect(base: Int){
        txtCheckBase.setText(base.toString())
        btnEnable(base)
        hexLayout.setBackgroundColor(Color.WHITE)
        decLayout.setBackgroundColor(Color.WHITE)
        octLayout.setBackgroundColor(Color.WHITE)
        binLayout.setBackgroundColor(Color.WHITE)
    }

    fun btnEnable(base: Int) {
        when(base){
            16->{
                btnA.isEnabled = true; btnB.isEnabled = true; btnC.isEnabled = true; btnD.isEnabled = true; btnE.isEnabled = true;
                btnF.isEnabled = true; btn9.isEnabled = true; btn8.isEnabled = true; btn7.isEnabled = true; btn6.isEnabled = true;
                btn5.isEnabled = true; btn4.isEnabled = true; btn3.isEnabled = true; btn2.isEnabled = true;
            }
            10->{
                btnEnable(16);
                btnA.isEnabled = false; btnB.isEnabled = false; btnC.isEnabled = false; btnD.isEnabled = false; btnE.isEnabled = false;
                btnF.isEnabled = false;
            }
            8->{
                btnEnable(10);
                btn9.isEnabled = false; btn8.isEnabled = false;
            }
            2->{
                btnEnable(8);
                btn7.isEnabled = false; btn6.isEnabled = false; btn5.isEnabled = false;
                btn4.isEnabled = false; btn3.isEnabled = false; btn2.isEnabled = false;
            }
        }
    }

    var isNewOp = true;

    fun baseConvert(base: Int, number: String): Int {
        var convertResult: Int = 0;

        for ((idx, eachNum) in number.withIndex()){
            when(eachNum){
                'A'->{ convertResult += (10 * base.toDouble().pow(number.length-idx-1)).toInt() }
                'B'->{ convertResult += (11 * base.toDouble().pow(number.length-idx-1)).toInt() }
                'C'->{ convertResult += (12 * base.toDouble().pow(number.length-idx-1)).toInt() }
                'D'->{ convertResult += (13 * base.toDouble().pow(number.length-idx-1)).toInt() }
                'E'->{ convertResult += (14 * base.toDouble().pow(number.length-idx-1)).toInt() }
                'F'->{ convertResult += (15 * base.toDouble().pow(number.length-idx-1)).toInt() }
                else->{
                    convertResult += (eachNum.toString().toInt() * base.toDouble().pow(number.length-idx-1)).toInt()
                }
            }
        }
        return convertResult
    }

    fun buNumEvent(view: View) {
        if(isNewOp){
            buCleanEvent(view)
        }
        isNewOp=false
        val buSelect = view as Button
        when(buSelect.id){
            btn0.id->{ txtResult.append("0"); }
            btn1.id->{ txtResult.append("1"); }
            btn2.id->{ txtResult.append("2"); }
            btn3.id->{ txtResult.append("3"); }
            btn4.id->{ txtResult.append("4"); }
            btn5.id->{ txtResult.append("5"); }
            btn6.id->{ txtResult.append("6"); }
            btn7.id->{ txtResult.append("7"); }
            btn8.id->{ txtResult.append("8"); }
            btn9.id->{ txtResult.append("9"); }
            btnA.id->{ txtResult.append("A"); }
            btnB.id->{ txtResult.append("B"); }
            btnC.id->{ txtResult.append("C"); }
            btnD.id->{ txtResult.append("D"); }
            btnE.id->{ txtResult.append("E"); }
            btnF.id->{ txtResult.append("F"); }

            btnDot.id->{
                //TODO: prevent adding more than 1 dot
                txtResult.append(".")
            }
            btnPlusMinus.id->{
                try {
                    txtResult.text = ((txtResult.text.toString().toDouble()) * (-1)).toString()
                } catch (e: Exception){
                    Log.d("Exception", "message" + e.message)
                }
            }
        }
        var baseInput = txtCheckBase.text.toString()
        var numInput: String = txtResult.text.toString()
        when(baseInput){
            "16"->{
                var decimalValue = baseConvert(16, numInput)
                conValue(decimalValue)
            }
            "10"->{
                txtHexValue.setText(numInput.toInt().toString(16).toUpperCase())
                txtDecValue.setText(numInput)
                txtOctValue.setText(numInput.toInt().toString(8))
                txtBinValue.setText(numInput.toInt().toString(2))
            }
            "8"->{
                var decimalValue = baseConvert(8, numInput)
                conValue(decimalValue)
            }
            "2"->{
                var decimalValue = baseConvert(2, numInput)
                conValue(decimalValue)
            }
        }
    }

    var op="*" //default value
    var oldNumber =""
    fun buOpEvent(view: View) {
        var baseInput = txtCheckBase.text.toString()
        oldNumber = txtResult.text.toString()
        when(baseInput){
            "16"->{
                oldNumber = baseConvert(16,oldNumber).toString()
            }
            "10"->{
                oldNumber = oldNumber
            }
            "8"->{
                oldNumber = baseConvert(8,oldNumber).toString()
            }
            "2"->{
                oldNumber = baseConvert(2,oldNumber).toString()
            }
        }
        isNewOp=true
        val buSelect = view as Button
        when(buSelect.id) {
            btnMul.id -> { op="*" }
            btnDiv.id -> { op="/" }
            btnSub.id -> { op="-" }
            btnSum.id -> { op="+" }
            btnModulus.id -> { op="%"}
            btnAnd.id ->{ op="AND" }
            btnOr.id ->{ op="OR" }
            btnNand.id ->{ op="NAND" }
            btnNor.id ->{ op="NOR" }
            btnXor.id ->{ op="XOR" }
            btnNot.id ->{
                var invNum = oldNumber.toInt().inv()
                txtResult.setText(invNum.toDouble().toString())
                txtHexValue.setText(invNum.toUInt().toString(16).toUpperCase())
                txtDecValue.setText(invNum.toString())
                txtOctValue.setText(invNum.toUInt().toString(8))
                txtBinValue.setText(invNum.toUInt().toString(2))
            }
        }

    }

    fun buEqualEvent(view: View) {
        var finalNum = 0
        var baseInput = txtCheckBase.text.toString()
        var newNumber = txtResult.text.toString()
        when(baseInput){
            "16"->{
                newNumber = baseConvert(16,newNumber).toString()
            }
            "10"->{
                newNumber = newNumber
            }
            "8"->{
                newNumber = baseConvert(8,newNumber).toString()
            }
            "2"->{
                newNumber = baseConvert(2,newNumber).toString()
            }
        }
        when(op){
            "*"->{
                finalNum = oldNumber.toInt() * newNumber.toInt()
            }
            "/"->{
                finalNum = oldNumber.toInt() / newNumber.toInt()
            }
            "-"->{
                finalNum = oldNumber.toInt() - newNumber.toInt()
            }
            "+"->{
                finalNum = oldNumber.toInt() + newNumber.toInt()
            }
            "%"->{
                finalNum = oldNumber.toInt() % newNumber.toInt()
            }
            "AND"->{
                finalNum = (oldNumber.toInt() and newNumber.toInt())
            }
            "OR"->{
                finalNum = (oldNumber.toInt() or newNumber.toInt())
            }

            "NAND"->{
                finalNum = (oldNumber.toInt() and newNumber.toInt()).inv()
            }
            "NOR"->{
                finalNum = (oldNumber.toInt() or newNumber.toInt()).inv()
            }
            "XOR"->{
                finalNum = (oldNumber.toInt() xor newNumber.toInt())
            }

        }
        when(baseInput){
            "16"->{txtResult.setText(finalNum.toString(16).toUpperCase())}
            "10"->{txtResult.setText(finalNum.toString())}
            "8"->{txtResult.setText(finalNum.toString(8))}
            "2"->{txtResult.setText(finalNum.toString(2))}
        }
        conValue(finalNum)
        isNewOp=true
    }

    fun conValue(inputNum: Int){
        txtHexValue.setText(inputNum.toString(16).toUpperCase())
        txtDecValue.setText(inputNum.toString(10))
        txtOctValue.setText(inputNum.toString(8))
        txtBinValue.setText(inputNum.toString(2))
    }

    fun buBackEvent(view: View){
        val string = txtResult.text.toString()
        if(string.isNotEmpty()){
            txtResult.setText(string.substring(0, string.length - 1))
        }
    }

    //clean the display
    fun buCleanEvent(view: View) {
        txtResult.setText("")
        txtHexValue.setText("")
        txtDecValue.setText("")
        txtOctValue.setText("")
        txtBinValue.setText("")
        isNewOp=true
    }

}