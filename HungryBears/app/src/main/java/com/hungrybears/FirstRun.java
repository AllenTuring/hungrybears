package com.hungrybears;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class FirstRun extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize the content view in the Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_run);

        // Spinner
        Spinner meal_plan = (Spinner) findViewById(R.id.meal_plan);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meal_plans, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal_plan.setAdapter(adapter);
        meal_plan.setOnItemSelectedListener(this);

        // Button Next
        Button bttnNext = (Button) findViewById(R.id.bttn_next);
        bttnNext.setOnClickListener(this);

        // Create a SharedPreferences value
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
        sharedPrefEditor.putBoolean("Setup Complete", false);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Spinner meal_plan = (Spinner) findViewById(R.id.meal_plan);
        String selected_plan = parent.getItemAtPosition(pos).toString();
        LinearLayout other = (LinearLayout) findViewById(R.id.LL_other_meal_points);
        if(selected_plan.equals("Other")) {
            //throw new IllegalArgumentException();
            other.setVisibility(View.VISIBLE);
        }
        else {
            other.setVisibility(View.GONE);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Do Nothing
    }


    @Override
    public void onClick(View v) {
        // Spinner
        Spinner meal_plan = (Spinner) findViewById(R.id.meal_plan);
        double points = MealPlan.getPointsFromPlan(meal_plan.getSelectedItem().toString());

        // Create
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
        sharedPrefEditor.putBoolean("Setup Complete", true);
    }
}
