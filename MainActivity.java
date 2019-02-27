package com.example.jsu.project1mm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public enum Operator {
        CLEAR, ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION, SIGN_CHANGE, SQRT;
    }

    String clickedNumber;
    Operator lastOperation;
    double prevNum, currentNum;
    boolean repeatOperation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastOperation = Operator.CLEAR;
        clickedNumber = "";
        prevNum = currentNum = 0.0;
        repeatOperation = true;

    }

    public void onClick(View v) {

        String id = (v.getResources().getResourceName(v.getId())).split("/")[1];

        Button b = (Button)v;
        String bText = b.getText().toString();

        TextView textView = (TextView) findViewById(R.id.textView);
        String displayNum = textView.getText().toString();


        switch (id) {
            case "additionButton":
                if (repeatOperation){
                    calculate();
                }
                lastOperation = Operator.ADDITION;
                break;
            case "subtractionButton":
                if (repeatOperation){
                    calculate();
                }
                lastOperation = Operator.SUBTRACTION;
                break;
            case "multiplicationButton":
                if (repeatOperation){
                    calculate();
                }
                lastOperation = Operator.MULTIPLICATION;
                break;
            case "divisionButton":
                if (repeatOperation){
                    calculate();
                }
                lastOperation = Operator.DIVISION;
                break;
            case "decimalButton":
                clickedNumber += ".";
                break;
            case "squareRootButton":
                if (!repeatOperation){
                    lastOperation = Operator.SQRT;
                }
                if (textView.getText().equals("")){
                    currentNum = 0.0;
                }
                else{
                    currentNum = Math.sqrt(Double.valueOf(displayNum));
                }
                clickedNumber = Double.toString(currentNum);
                textView.setText(Double.toString(currentNum));
                break;
            case "percentButton":
                currentNum = Double.valueOf(displayNum);
                if (lastOperation == Operator.CLEAR){
                    currentNum = 0.0;
                }
                else if (lastOperation == Operator.ADDITION){
                    currentNum = prevNum * (currentNum / 100.0);
                }
                else if (lastOperation == Operator.MULTIPLICATION){
                    currentNum = currentNum / 100.0;
                }
                clickedNumber = Double.toString(currentNum);
                textView.setText(clickedNumber);
                break;
            case "signButton":
                if (!repeatOperation){
                    lastOperation = Operator.SIGN_CHANGE;
                }
                currentNum = 0 - Double.valueOf(displayNum);
                clickedNumber = Double.toString(currentNum);
                textView.setText(clickedNumber);
                break;
            case "clearButton":
                lastOperation = Operator.CLEAR;
                prevNum = currentNum = 0.0;
                clickedNumber = "";
                textView.setText(Double.toString(currentNum));
                break;
            case "equalButton":
                calculate();
                break;
            default:
                clickedNumber = clickedNumber + b.getText().toString();
                textView.setText(clickedNumber);
                break;
        }

        repeatOperation = !id.equals("equalButton");
    }
    public void calculate(){
        double result;
        TextView textView = (TextView) findViewById(R.id.textView);
        clickedNumber = textView.getText().toString();

        if(repeatOperation && !clickedNumber.equals("")){
            currentNum = Double.valueOf(clickedNumber);
        }

        switch(lastOperation){
            case ADDITION:
                result = prevNum + currentNum;
                break;
            case SUBTRACTION:
                result = prevNum - currentNum;
                break;
            case DIVISION:
                result = prevNum / currentNum;
                break;
            case MULTIPLICATION:
                result = prevNum * currentNum;
                break;
            default:
                result = currentNum;
        }
        prevNum = result;

        textView.setText(Double.toString(result));

        clickedNumber = "";
    }



}
