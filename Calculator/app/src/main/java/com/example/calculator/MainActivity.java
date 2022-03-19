package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int curPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, curPos);
        String rightStr = oldStr.substring(curPos);
        if(getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(curPos + 1);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(curPos + 1);
        }
    }

    public void zeroBtn(View view) {
        updateText("0");
    }
    public void oneBtn(View view) {
        updateText("1");
    }
    public void twoBtn(View view) {
        updateText("2");
    }
    public void threeBtn(View view) {
        updateText("3");
    }
    public void fourBtn(View view) {
        updateText("4");
    }
    public void fiveBtn(View view) {
        updateText("5");
    }
    public void sixBtn(View view) {
        updateText("6");
    }
    public void sevenBtn(View view) {
        updateText("7");
    }
    public void eightBtn(View view) {
        updateText("8");
    }
    public void nineBtn(View view) {
        updateText("9");
    }
    public void congBtn(View view) {
        updateText("+");
    }
    public void truBtn(View view) {
        updateText("-");
    }
    public void nhanBtn(View view) {
        updateText("*");
    }
    public void chiaBtn(View view) {
        updateText("/");
    }

    public void pointBtn(View view) {
        updateText(".");
    }

    public void equalBtn(View view) {
        String str = display.getText().toString();
        //str = str.replaceAll(":", "/");
        //str = str.replaceAll("x", "*");

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        Double result = null;
        try {
            result = (Double) engine.eval(str);
        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        if (result != null) {
            display.setText(String.valueOf(result));
            display.setSelection(String.valueOf(result).length());
        }
    }
    public void delBtn(View view) {
        int curPos = display.getSelectionStart();
        int textLeng = display.getText().length();
        if (curPos != 0 && textLeng != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(curPos - 1, curPos, "");
            display.setText(selection);
            display.setSelection(curPos - 1);
        }
    }

    public void clearBtn(View view) {
        display.setText("");
    }
}