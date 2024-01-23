package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private StringBuilder inputStringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        inputStringBuilder = new StringBuilder();
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "=":
                calculateResult();
                break;
            default:
                inputStringBuilder.append(buttonText);
                updateEditText();
                break;
        }
    }
    public void onClearButtonClick(View view) {
        inputStringBuilder.setLength(0);
        updateEditText();
    }

    private void updateEditText() {
        editText.setText(inputStringBuilder.toString());
    }

    private void calculateResult() {
        try {
            // Evaluate the expression using your own parsing logic
            double result = evaluateExpression(inputStringBuilder.toString());
            editText.setText(String.valueOf(result));
            inputStringBuilder.setLength(0);
            inputStringBuilder.append(result);
        } catch (Exception e) {
            editText.setText("Error");
            inputStringBuilder.setLength(0);
        }
    }

    private double evaluateExpression(String expression) {
        // Implement your own expression parsing and evaluation logic here
        // For simplicity, you can use a library or write a basic parser
        // This example assumes simple arithmetic operations
        return SimpleExpressionParser.evaluate(expression);
    }
}
