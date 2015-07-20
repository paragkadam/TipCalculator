package com.psk.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class TipCalc extends ActionBarActivity {
    private final static String BILL_AMOUNT = "BILL_AMOUNT";
    private final static String TIP_AMOUNT = "TIP_AMOUNT";
    private final static String FINAL_AMOUNT = "FINAL_AMOUNT";

    private double bill;
    private double tip;
    private double final_amount;
    int flag = 0, flag1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calc);


        if (savedInstanceState == null) {
            bill = 0.0;
            tip = 0.15;
            final_amount = 0.0;
        } else {
            bill = savedInstanceState.getDouble(BILL_AMOUNT);
            tip = savedInstanceState.getDouble(TIP_AMOUNT);
            final_amount = savedInstanceState.getDouble(FINAL_AMOUNT);
        }
        EditText bill_edit_text = (EditText) findViewById(R.id.bill_edittext);
        EditText tip_edit_text = (EditText) findViewById(R.id.tip_edittext);
        EditText final_amount_edit_text = (EditText) findViewById(R.id.final_amount_edittext);
        SeekBar tip_seekBar = (SeekBar) findViewById(R.id.tip_seekBar);
        CheckBox intro_friendly = (CheckBox) findViewById(R.id.intro_friendly);
        CheckBox intro_special = (CheckBox) findViewById(R.id.intro_special);
        CheckBox intro_opinions = (CheckBox) findViewById(R.id.intro_opinions);
        RadioGroup radio_availability = (RadioGroup) findViewById(R.id.radio_avaiability);
        Spinner spinner_problem = (Spinner) findViewById(R.id.spinner_problem);

       /* Interface text watcher from android:text watches text change from editText
        Methods inside of the interface are overridden.
        Checking text inside the Bill amount for any changes*/

        bill_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    bill = Double.parseDouble(s.toString());
                    final_amount = bill + (bill * tip);
                    //Log.d("---Bill---",String.format("%f",bill));
                    EditText final_amount_edit_text = (EditText) findViewById(R.id.final_amount_edittext);
                    final_amount_edit_text.setText(String.format("%.02f", final_amount));
                } catch (NumberFormatException e) {
                    bill = 0.0;
                    //Log.e("---Bill---",e.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /*Checking text inside the Bill amount for any changes*/

        tip_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    tip = Double.parseDouble(s.toString());
                    final_amount = bill + (bill * tip);
                    //Log.d("---Tip---",String.format("%f",tip));
                    EditText final_amount_edit_text = (EditText) findViewById(R.id.final_amount_edittext);
                    final_amount_edit_text.setText(String.format("%.02f", final_amount));
                } catch (NumberFormatException e) {
                    tip = 0.15;
                    //Log.e("---Tip---",e.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        tip_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    tip = Double.parseDouble(String.format("%d", progress));
                    tip = tip * 0.01;
                    final_amount = bill + (bill * tip);
                    //Log.d("---Tip---",String.format("%f",tip));
                    EditText final_amount_edit_text = (EditText) findViewById(R.id.final_amount_edittext);
                    final_amount_edit_text.setText(String.format("%.02f", final_amount));
                } catch (NumberFormatException e) {
                    tip = 0.15;
                } catch (Exception e) {
                    tip = 0.15;
                }
                setTip(tip);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        intro_friendly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tip += 0.02;
                    setTip(tip);
                } else {
                    tip -= 0.02;
                    setTip(tip);
                }
            }
        });
        intro_special.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tip += 0.02;
                    setTip(tip);
                } else {
                    tip -= 0.02;
                    setTip(tip);
                }
            }
        });
        intro_opinions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tip += 0.02;
                    setTip(tip);
                } else {
                    tip -= 0.02;
                    setTip(tip);
                }
            }
        });
        radio_availability.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (flag) {
                    case 1:
                        tip += .04;
                        break;
                    case 2:
                        tip -= .02;
                        break;
                    case 3:
                        tip -= .04;
                        break;
                }
                switch (checkedId) {
                    case R.id.radio_bad:
                        tip -= 0.04;
                        setTip(tip);
                        flag = 1;
                        break;
                    case R.id.radio_okay:
                        tip += .02;
                        setTip(tip);
                        flag = 2;
                        break;
                    case R.id.radio_good:
                        tip += .04;
                        setTip(tip);
                        flag = 3;
                        break;
                }
            }
        });

        spinner_problem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (flag1) {
                    case 1:
                        tip += .04;
                        break;
                    case 2:
                        tip -= .02;
                        break;
                    case 3:
                        tip -= .04;
                        break;
                }

                switch (position) {
                    case 1:
                        tip -= 0.04;
                        setTip(tip);
                        flag1 = 1;
                        break;
                    case 2:
                        tip += 0.02;
                        setTip(tip);
                        flag1 = 2;
                        break;
                    case 3:
                        tip += 0.04;
                        setTip(tip);
                        flag1 = 3;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void setTip(double tip) {
        EditText tip_edit_text = (EditText) findViewById(R.id.tip_edittext);
        tip_edit_text.setText(String.format("%.02f", tip));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tip_calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(BILL_AMOUNT, bill);
        outState.putDouble(TIP_AMOUNT, tip);
        outState.putDouble(FINAL_AMOUNT, final_amount);
    }
}
