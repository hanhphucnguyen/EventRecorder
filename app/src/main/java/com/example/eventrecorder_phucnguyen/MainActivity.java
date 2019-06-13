package com.example.eventrecorder_phucnguyen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void recordActivity(View view) {

        Intent intent = new Intent(this, recordActivity.class);
        /*   intent.putExtra("name",editTextName.getText().toString());
        intent.putExtra("spinner",spinnerNum.getSelectedItem().toString());
        intent.putExtra("rating",ratingBar.getRating());*/
        startActivity(intent);
    }

    public void showActivity(View view) {

        Intent intent = new Intent(this, showActivity.class);
        /*   intent.putExtra("name",editTextName.getText().toString());
           intent.putExtra("spinner",spinnerNum.getSelectedItem().toString());
           intent.putExtra("rating",ratingBar.getRating());*/
        startActivity(intent);
    }

    public void aboutActivity(View view) {

        Intent intent = new Intent(this, aboutActivity.class);
        /*   intent.putExtra("name",editTextName.getText().toString());
           intent.putExtra("spinner",spinnerNum.getSelectedItem().toString());
           intent.putExtra("rating",ratingBar.getRating());*/
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // save shared_pref
        SharedPreferences settings = getSharedPreferences("eventrecord",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

       /* editor.putString("name",editTextName.getText().toString());
        editor.putInt("spinner",spinnerNum.getSelectedItemPosition());
        editor.putFloat("rating",ratingBar.getRating());*/

        // write shared pref to file
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //restore our shared_pref
        SharedPreferences settings = getSharedPreferences("eventrecord",
                Context.MODE_PRIVATE);

        /*editTextName.setText(settings.getString("name",""));
        spinnerNum.setSelection(settings.getInt("spinner",0));
        ratingBar.setRating(settings.getFloat("rating",0.0f));*/
    }
}
