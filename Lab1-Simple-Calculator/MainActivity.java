package com.klcal.lab1simplecal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvFormula, tvAnswer;
    private Button btnPlus, btnMinus, btnTimes, btnDivide, btnEqual, btnCE;
    private Button btnTemp;
    private String tvFormulaText;
    private String[] operators = {"+", "-", "*", "/"};
    private int[] btnNumbersIDs;

    private boolean hasDecimal = false;

    private int result, firstNumber, secondNumber, flag = 0;
    private double divideResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all components
        init();

        // Reset all
        btnCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFormulaText = "";
                tvFormula.setText("");
                tvAnswer.setText("");
                flag = 0;
            }
        });

        // 0-9 Buttons listeners
        for (int buttonID : btnNumbersIDs) {
            btnTemp = (Button) findViewById(buttonID);
            btnTemp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (tvFormulaText.length() > 0 && tvAnswer.getText().toString().length() > 0) {
                        tvFormulaText = "";
                        tvAnswer.setText("");
                    }

                    tvFormulaText += String.valueOf(((Button) view).getText());
                    tvFormula.setText(tvFormulaText);
                }
            });
        }

        // Operators listeners
        buttonsListener(btnPlus, 1);
        buttonsListener(btnMinus, 2);
        buttonsListener(btnTimes, 3);
        buttonsListener(btnDivide, 4);

        // = Button listener
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (String operator : operators) {
                    // If the last character is operator, do nothing
                    if (tvFormulaText.substring(tvFormulaText.length() - 1).equals(operator)) {
                        return;
                    }
                }

                String tempFormulaText  = tvFormulaText.trim().replaceAll("[^0-9]", ",");
                String tempSecondNumber = tempFormulaText.split(",")[1];

                secondNumber = Integer.parseInt(tempSecondNumber);

                switch (flag) {
                    case 1:
                        result = firstNumber + secondNumber;
                        break;
                    case 2:
                        result = firstNumber - secondNumber;
                        break;
                    case 3:
                        result = firstNumber * secondNumber;
                        break;
                    case 4:
                        divideResult = (double) firstNumber / (double) secondNumber;
                        if (divideResult % 1 == 0) {
                            result = (int) divideResult;
                        } else {
                            hasDecimal = true;
                        }
                        break;
                }

                // Check if the result has decimal
                //      , return double type result
                // , otherwise
                //      , return int type result
                String resultString = (hasDecimal) ? (divideResult + "").trim() : (result + "").trim();

                tvAnswer.setText(resultString);

                flag = 0;
            }
        });

    }

    private void init() {
        tvFormula = (TextView) findViewById(R.id.tvFormula);
        tvAnswer = (TextView) findViewById(R.id.tvAnswer);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnTimes = (Button) findViewById(R.id.btnTimes);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnEqual = (Button) findViewById(R.id.btnEqual);
        btnCE = (Button) findViewById(R.id.btnCE);

        tvFormulaText = "";
        firstNumber = 0;
        secondNumber = 0;

        btnNumbersIDs = new int[]{
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

    }

    private void buttonsListener(Button button, final int id) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int tvFormulaLength = tvFormula.getText().toString().length();

                if (tvFormulaLength == 0 || flag != 0) {
                    return;
                }

                if (tvAnswer.getText().toString().length() <= 0) {
                    firstNumber = Integer.parseInt(tvFormula.getText().toString().trim());
                } else {
                    firstNumber = Integer.parseInt(tvAnswer.getText().toString().trim());
                    tvFormulaText = String.valueOf(firstNumber);
                    tvAnswer.setText("");
                }

                tvFormulaText += operators[id - 1];
                tvFormula.setText(tvFormulaText);
                flag = id;
            }
        });
    }
}
