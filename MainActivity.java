package com.brandon.apportionmentcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText states, population, quota, initialFairShare, finalFairShare, seats, scr1, scr2, scr3, scr4, scr5, scrtotalState, scrtotalPopulation, scrtotalQuota, scrtotalInitial, scrtotalFinal;
    Button add, remove, calculate, clear;
    TableLayout t1;
    TableRow tr;
    Spinner methods;
    TableRow totalRow;
    private ArrayList<TableRow> list = new ArrayList<>();
    private ArrayList<EditText> stateCol = new ArrayList<>();
    private ArrayList<EditText> populationCol = new ArrayList<>();
    private ArrayList<EditText> quotaCol = new ArrayList<>();
    private ArrayList<EditText> initialCol = new ArrayList<>();
    private ArrayList<EditText> finalCol = new ArrayList<>();
    private int rows;
    private int amountOfSeats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        states = (EditText)findViewById(R.id.states);
        population = (EditText)findViewById(R.id.population);
        quota = (EditText)findViewById(R.id.quota);
        initialFairShare = (EditText)findViewById(R.id.initialFairShare);
        finalFairShare = (EditText)findViewById(R.id.finalFairShare);
        seats = (EditText)findViewById(R.id.amountOfSeats);
        String temp = seats.getText().toString();
        try {
            amountOfSeats = Integer.parseInt(temp);
        } catch (NumberFormatException e){}
        add = (Button)findViewById(R.id.add);
        remove = (Button)findViewById(R.id.remove);
        calculate = (Button)findViewById(R.id.calculate);
        clear = (Button)findViewById(R.id.clearButton);
        methods = (Spinner)findViewById(R.id.spinnerMethods);
        t1 = (TableLayout)findViewById(R.id.t1);
        t1.setColumnStretchable(0, true);
        t1.setColumnStretchable(1, true);
        t1.setColumnStretchable(2, true);
        t1.setColumnStretchable(3, true);
        t1.setColumnStretchable(4, true);

        totalRow = new TableRow(this);

        states.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    for (int i = 0; i < list.size() + 1; i++) {
                        stateCol.get(i).setBackgroundResource(R.drawable.highlight);
                    }
                } else {
                    for (int i = 0; i < list.size() + 1; i++) {
                        stateCol.get(i).setBackgroundResource(R.drawable.backtext);
                    }
                }
            }
        });

        population.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    for (int i = 0; i < list.size() + 1; i++) {
                        populationCol.get(i).setBackgroundResource(R.drawable.highlight);
                    }
                } else {
                    for (int i = 0; i < list.size() + 1; i++) {
                        populationCol.get(i).setBackgroundResource(R.drawable.backtext);
                    }
                }
            }
        });

        quota.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    for (int i = 0; i < list.size() + 1; i++) {
                        quotaCol.get(i).setBackgroundResource(R.drawable.highlight);
                    }
                } else {
                    for (int i = 0; i < list.size() + 1; i++) {
                        quotaCol.get(i).setBackgroundResource(R.drawable.backtext);
                    }
                }
            }
        });

        initialFairShare.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    for (int i = 0; i < list.size() + 1; i++) {
                        initialCol.get(i).setBackgroundResource(R.drawable.highlight);
                    }
                } else {
                    for (int i = 0; i < list.size() + 1; i++) {
                        initialCol.get(i).setBackgroundResource(R.drawable.backtext);
                    }
                }
            }
        });

        finalFairShare.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    for (int i = 0; i < list.size() + 1; i++) {
                        finalCol.get(i).setBackgroundResource(R.drawable.highlight);
                    }
                } else {
                    for (int i = 0; i < list.size() + 1; i++) {
                        finalCol.get(i).setBackgroundResource(R.drawable.backtext);
                    }
                }
            }
        });

        rows = 0;


        //setup default row
        tr = new TableRow(this);
        scr1 = new EditText(this);
        scr2 = new EditText(this);
        scr3 = new EditText(this);
        scr4 = new EditText(this);
        scr5 = new EditText(this);

        scr1.setText("S" + (rows + 1));
        scr1.setGravity(Gravity.CENTER);
        scr1.setBackgroundResource(R.color.white);
        scr1.setTextSize(12);
        scr1.setKeyListener(null);
        scr1.setBackgroundResource(R.drawable.backtext);
        scr1.setMaxWidth(0);
        scr1.setMaxLines(1);
        stateCol.add(scr1);

        scr2.setText("");
        scr2.setGravity(Gravity.CENTER);
        scr2.setBackgroundResource(R.color.white);
        scr2.setTextSize(12);
        scr2.setBackgroundResource(R.color.pink);
        scr2.setBackgroundResource(R.drawable.backtext);
        scr2.setMaxWidth(0);
        scr2.setMaxLines(1);
        scr2.setHint("Enter Number");
        scr2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL); //for decimal numbers
        populationCol.add(scr2);

        scr3.setText("");
        scr3.setGravity(Gravity.CENTER);
        scr3.setBackgroundResource(R.color.white);
        scr3.setTextSize(12);
        scr3.setKeyListener(null);
        scr3.setBackgroundResource(R.drawable.backtext);
        scr3.setMaxWidth(0);
        scr3.setMaxLines(1);
        quotaCol.add(scr3);

        scr4.setText("");
        scr4.setGravity(Gravity.CENTER);
        scr4.setBackgroundResource(R.color.white);
        scr4.setTextSize(12);
        scr4.setKeyListener(null);
        scr4.setBackgroundResource(R.drawable.backtext);
        scr4.setMaxWidth(0);
        scr4.setMaxLines(1);
        initialCol.add(scr4);

        scr5.setText("");
        scr5.setGravity(Gravity.CENTER);
        scr5.setBackgroundResource(R.color.white);
        scr5.setTextSize(12);
        scr5.setKeyListener(null);
        scr5.setBackgroundResource(R.drawable.backtext);
        scr5.setMaxWidth(0);
        scr5.setMaxLines(1);
        finalCol.add(scr5);

        tr.addView(scr1);
        tr.addView(scr2);
        tr.addView(scr3);
        tr.addView(scr4);
        tr.addView(scr5);

        t1.addView(tr);

        //setup spinner
        ArrayList<String> methodList = new ArrayList<>();
        methodList.add("Select Apportionment Method");
        methodList.add("Hamilton's Method");
        //methodList.add("Jefferson's Method");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_spinner_style, methodList);
        methods.setAdapter(adapter);



    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                rows++;

                //clear highlighted rows
                for (int i = 0; i < list.size() + 1; i++) {
                    stateCol.get(i).setBackgroundResource(R.drawable.backtext);
                    populationCol.get(i).setBackgroundResource(R.drawable.backtext);
                    quotaCol.get(i).setBackgroundResource(R.drawable.backtext);
                    initialCol.get(i).setBackgroundResource(R.drawable.backtext);
                    finalCol.get(i).setBackgroundResource(R.drawable.backtext);
                }

                tr = new TableRow(this);
                scr1 = new EditText(this);
                scr2 = new EditText(this);
                scr3 = new EditText(this);
                scr4 = new EditText(this);
                scr5 = new EditText(this);



                scr1.setText("S" + (rows + 1));
                scr1.setGravity(Gravity.CENTER);
                scr1.setBackgroundResource(R.color.white);
                scr1.setTextSize(12);
                scr1.setKeyListener(null);
                scr1.setBackgroundResource(R.drawable.backtext);
                scr1.setMaxWidth(0);
                scr1.setMaxLines(1);
                scr1.setClickable(false);
                stateCol.add(scr1);

                scr2.setText("");
                scr2.setGravity(Gravity.CENTER);
                scr2.setBackgroundResource(R.color.white);
                scr2.setTextSize(12);
                scr2.setBackgroundResource(R.color.pink);
                scr2.setBackgroundResource(R.drawable.backtext);
                scr2.setMaxWidth(0);
                scr2.setMaxLines(1);
                scr2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL); //for decimal numbers
                scr2.setHint("Enter Number");
                populationCol.add(scr2);

                scr3.setText("");
                scr3.setGravity(Gravity.CENTER);
                scr3.setBackgroundResource(R.color.white);
                scr3.setTextSize(12);
                scr3.setKeyListener(null);
                scr3.setBackgroundResource(R.drawable.backtext);
                scr3.setMaxWidth(0);
                scr3.setMaxLines(1);
                scr3.setClickable(false);
                quotaCol.add(scr3);

                scr4.setText("");
                scr4.setGravity(Gravity.CENTER);
                scr4.setBackgroundResource(R.color.white);
                scr4.setTextSize(12);
                scr4.setKeyListener(null);
                scr4.setBackgroundResource(R.drawable.backtext);
                scr4.setMaxWidth(0);
                scr4.setMaxLines(1);
                initialCol.add(scr4);

                scr5.setText("");
                scr5.setGravity(Gravity.CENTER);
                scr5.setBackgroundResource(R.color.white);
                scr5.setTextSize(12);
                scr5.setKeyListener(null);
                scr5.setBackgroundResource(R.drawable.backtext);
                scr5.setMaxWidth(0);
                scr5.setMaxLines(1);
                finalCol.add(scr5);

                tr.addView(scr1);
                tr.addView(scr2);
                tr.addView(scr3);
                tr.addView(scr4);
                tr.addView(scr5);

                t1.addView(tr);
                list.add(tr);

                totalRow.removeAllViews();
                t1.removeView(totalRow);

                System.out.println("list size: " + list.size());
                break;
            case R.id.remove:
                if (rows > 0) {
                    rows--;
                    t1.removeViewAt(rows + 2);
                    list.remove(list.get(list.size() - 1));
                    System.out.println("list size: " + list.size());
                    stateCol.remove(stateCol.get(stateCol.size() - 1));
                    populationCol.remove(populationCol.get(populationCol.size() - 1));
                    quotaCol.remove(quotaCol.get(quotaCol.size() - 1));
                    initialCol.remove(initialCol.get(initialCol.size() - 1));
                    finalCol.remove(finalCol.get(finalCol.size() - 1));
                    //clear highlighted rows
                    for (int i = 0; i < list.size() + 1; i++) {
                        stateCol.get(i).setBackgroundResource(R.drawable.backtext);
                        populationCol.get(i).setBackgroundResource(R.drawable.backtext);
                        quotaCol.get(i).setBackgroundResource(R.drawable.backtext);
                        initialCol.get(i).setBackgroundResource(R.drawable.backtext);
                        finalCol.get(i).setBackgroundResource(R.drawable.backtext);
                    }

                    totalRow.removeAllViews();
                    t1.removeView(totalRow);
                }
                break;
            case R.id.calculate:
                TextView temp = (TextView)methods.getSelectedView();
                String methodChoice = "";
                try {
                    methodChoice = temp.getText().toString();
                } catch (NullPointerException e){}

                seats = (EditText)findViewById(R.id.amountOfSeats);
                String result = seats.getText().toString();
                try {
                    amountOfSeats = Integer.parseInt(result);
                } catch (NumberFormatException e){}

                System.out.println("method: " + methodChoice);
                System.out.println("amount of seats: " + amountOfSeats);

                try {

                    switch (methodChoice) {
                        case "Hamilton's Method":

                            HamiltonMethod method = new HamiltonMethod(list.size() + 1, amountOfSeats, populationCol, quotaCol, initialCol, finalCol);
                            method.hamilton();

                            TextView divisorView = (TextView) findViewById(R.id.DivisorView);
                            BigDecimal divisor = new BigDecimal(0);
                            divisor = method.getDivisor();
                            divisorView.setText("Divisor: " + divisor);

                            ArrayList<EditText> newQuota = new ArrayList<>();
                            ArrayList<EditText> newInitial = new ArrayList<>();
                            ArrayList<EditText> newFinal = new ArrayList<>();

                            newQuota = method.getOutput_quotas();
                            newInitial = method.getOutput_initial_fair_shares();
                            newFinal = method.getOutput_final_fair_shares();

                            for (int i = 0; i < list.size() + 1; i++) {
                                quotaCol.get(i).setText(newQuota.get(i).getText().toString());
                                initialCol.get(i).setText(newInitial.get(i).getText().toString());
                                finalCol.get(i).setText(newFinal.get(i).getText().toString());
                                System.out.println("new quota: " + quotaCol.get(i).getText().toString());
                            }

                            //total row
                            //TableRow totalRow= new TableRow(this);

                            totalRow.removeAllViews();
                            t1.removeView(totalRow);

                            scrtotalState = new EditText(this);
                            scrtotalPopulation = new EditText(this);
                            scrtotalQuota = new EditText(this);
                            scrtotalInitial = new EditText(this);
                            scrtotalFinal = new EditText(this);

                            scrtotalState.setText("" + (rows + 1));
                            scrtotalState.setGravity(Gravity.CENTER);
                            scrtotalState.setBackgroundResource(R.color.white);
                            scrtotalState.setTextSize(12);
                            scrtotalState.setKeyListener(null);
                            scrtotalState.setBackgroundResource(R.drawable.highlight);
                            scrtotalState.setMaxWidth(0);
                            scrtotalState.setMaxLines(1);

                            scrtotalPopulation.setText("" + method.getTotal_population());
                            scrtotalPopulation.setGravity(Gravity.CENTER);
                            scrtotalPopulation.setBackgroundResource(R.color.white);
                            scrtotalPopulation.setTextSize(12);
                            scrtotalPopulation.setKeyListener(null);
                            scrtotalPopulation.setBackgroundResource(R.drawable.highlight);
                            scrtotalPopulation.setMaxWidth(0);
                            scrtotalPopulation.setMaxLines(1);

                            scrtotalQuota.setText("" + method.getTotal_quota());
                            scrtotalQuota.setGravity(Gravity.CENTER);
                            scrtotalQuota.setBackgroundResource(R.color.white);
                            scrtotalQuota.setTextSize(12);
                            scrtotalQuota.setKeyListener(null);
                            scrtotalQuota.setBackgroundResource(R.drawable.highlight);
                            scrtotalQuota.setMaxWidth(0);
                            scrtotalQuota.setMaxLines(1);

                            scrtotalInitial.setText("" + method.getInitialFs());
                            scrtotalInitial.setGravity(Gravity.CENTER);
                            scrtotalInitial.setBackgroundResource(R.color.white);
                            scrtotalInitial.setTextSize(12);
                            scrtotalInitial.setKeyListener(null);
                            scrtotalInitial.setBackgroundResource(R.drawable.highlight);
                            scrtotalInitial.setMaxWidth(0);
                            scrtotalInitial.setMaxLines(1);

                            scrtotalFinal.setText("" + method.getFinalFs());
                            scrtotalFinal.setGravity(Gravity.CENTER);
                            scrtotalFinal.setBackgroundResource(R.color.white);
                            scrtotalFinal.setTextSize(12);
                            scrtotalFinal.setKeyListener(null);
                            scrtotalFinal.setBackgroundResource(R.drawable.highlight);
                            scrtotalFinal.setMaxWidth(0);
                            scrtotalFinal.setMaxLines(1);

                            totalRow.addView(scrtotalState);
                            totalRow.addView(scrtotalPopulation);
                            totalRow.addView(scrtotalQuota);
                            totalRow.addView(scrtotalInitial);
                            totalRow.addView(scrtotalFinal);
                            t1.addView(totalRow);

                            break;
                    }




                } catch (NumberFormatException e) {
                    for (int i = 0; i < populationCol.size(); i++) {
                        if (populationCol.get(i).getText().toString().equals("")) {
                            populationCol.get(i).setBackgroundResource(R.drawable.highlight);
                        }
                    }
                }
                break;
            case R.id.clearButton:
                //clear all values

//                    populationCol.get(i).setText("");
//                    quotaCol.get(i).setText("");
//                    initialCol.get(i).setText("");
//                    finalCol.get(i).setText("");


                    //t1.removeAllViews();
                TextView divisorView = (TextView) findViewById(R.id.DivisorView);
                divisorView.setText("");

                    while (t1.getChildCount() > 1) {
                        t1.removeView(t1.getChildAt(t1.getChildCount() - 1));
                    }
                    rows = 0;

                    list.clear();

                    stateCol.clear();
                    populationCol.clear();
                    quotaCol.clear();
                    initialCol.clear();
                    finalCol.clear();

                tr = new TableRow(this);
                scr1 = new EditText(this);
                scr2 = new EditText(this);
                scr3 = new EditText(this);
                scr4 = new EditText(this);
                scr5 = new EditText(this);

                scr1.setText("S" + (rows + 1));
                scr1.setGravity(Gravity.CENTER);
                scr1.setBackgroundResource(R.color.white);
                scr1.setTextSize(12);
                scr1.setKeyListener(null);
                scr1.setBackgroundResource(R.drawable.backtext);
                scr1.setMaxWidth(0);
                scr1.setMaxLines(1);
                stateCol.add(scr1);

                scr2.setText("");
                scr2.setGravity(Gravity.CENTER);
                scr2.setBackgroundResource(R.color.white);
                scr2.setTextSize(12);
                scr2.setBackgroundResource(R.color.pink);
                scr2.setBackgroundResource(R.drawable.backtext);
                scr2.setMaxWidth(0);
                scr2.setMaxLines(1);
                scr2.setHint("Enter Number");
                scr2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL); //for decimal numbers
                populationCol.add(scr2);

                scr3.setText("");
                scr3.setGravity(Gravity.CENTER);
                scr3.setBackgroundResource(R.color.white);
                scr3.setTextSize(12);
                scr3.setKeyListener(null);
                scr3.setBackgroundResource(R.drawable.backtext);
                scr3.setMaxWidth(0);
                scr3.setMaxLines(1);
                quotaCol.add(scr3);

                scr4.setText("");
                scr4.setGravity(Gravity.CENTER);
                scr4.setBackgroundResource(R.color.white);
                scr4.setTextSize(12);
                scr4.setKeyListener(null);
                scr4.setBackgroundResource(R.drawable.backtext);
                scr4.setMaxWidth(0);
                scr4.setMaxLines(1);
                initialCol.add(scr4);

                scr5.setText("");
                scr5.setGravity(Gravity.CENTER);
                scr5.setBackgroundResource(R.color.white);
                scr5.setTextSize(12);
                scr5.setKeyListener(null);
                scr5.setBackgroundResource(R.drawable.backtext);
                scr5.setMaxWidth(0);
                scr5.setMaxLines(1);
                finalCol.add(scr5);

                tr.addView(scr1);
                tr.addView(scr2);
                tr.addView(scr3);
                tr.addView(scr4);
                tr.addView(scr5);

                t1.addView(tr);

//                    stateCol.clear();
//                    populationCol.clear();
//                    quotaCol.clear();
//                    initialCol.clear();
//                    finalCol.clear();
//                    list.clear();
//                    rows = 0;
//
//
//                    scr1 = new EditText(this);
//                    scr2 = new EditText(this);
//                    scr3 = new EditText(this);
//                    scr4 = new EditText(this);
//                    scr5 = new EditText(this);
//
//                    scr1.setText("S" + (rows + 1));
//                    scr1.setGravity(Gravity.CENTER);
//                    scr1.setBackgroundResource(R.color.white);
//                    scr1.setTextSize(12);
//                    scr1.setKeyListener(null);
//                    scr1.setBackgroundResource(R.drawable.backtext);
//                    scr1.setMaxWidth(0);
//                    scr1.setMaxLines(1);
//                    scr1.setClickable(false);
//                    stateCol.add(scr1);
//
//                    scr2.setText("");
//                    scr2.setGravity(Gravity.CENTER);
//                    scr2.setBackgroundResource(R.color.white);
//                    scr2.setTextSize(12);
//                    scr2.setBackgroundResource(R.color.pink);
//                    scr2.setBackgroundResource(R.drawable.backtext);
//                    scr2.setMaxWidth(0);
//                    scr2.setMaxLines(1);
//                    scr2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL); //for decimal numbers
//                    scr2.setHint("Enter Number");
//                    populationCol.add(scr2);
//
//                    scr3.setText("");
//                    scr3.setGravity(Gravity.CENTER);
//                    scr3.setBackgroundResource(R.color.white);
//                    scr3.setTextSize(12);
//                    scr3.setKeyListener(null);
//                    scr3.setBackgroundResource(R.drawable.backtext);
//                    scr3.setMaxWidth(0);
//                    scr3.setMaxLines(1);
//                    scr3.setClickable(false);
//                    quotaCol.add(scr3);
//
//                    scr4.setText("");
//                    scr4.setGravity(Gravity.CENTER);
//                    scr4.setBackgroundResource(R.color.white);
//                    scr4.setTextSize(12);
//                    scr4.setKeyListener(null);
//                    scr4.setBackgroundResource(R.drawable.backtext);
//                    scr4.setMaxWidth(0);
//                    scr4.setMaxLines(1);
//                    initialCol.add(scr4);
//
//                    scr5.setText("");
//                    scr5.setGravity(Gravity.CENTER);
//                    scr5.setBackgroundResource(R.color.white);
//                    scr5.setTextSize(12);
//                    scr5.setKeyListener(null);
//                    scr5.setBackgroundResource(R.drawable.backtext);
//                    scr5.setMaxWidth(0);
//                    scr5.setMaxLines(1);
//                    finalCol.add(scr5);
//
//                    tr.addView(scr1);
//                    tr.addView(scr2);
//                    tr.addView(scr3);
//                    tr.addView(scr4);
//                    tr.addView(scr5);
//
//                    t1.addView(tr);
//                    list.add(tr);




                break;
            default:

                break;
        }
    }

}