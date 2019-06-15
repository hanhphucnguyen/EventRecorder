package com.example.eventrecorder_phucnguyen;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class recordActivity extends AppCompatActivity {
    public class record
    {
        String eventName,eventType;
        Float level;
        record(){
            eventName="";
            eventType="";
            level=0.0f;
        }
    }
    ArrayList<record> listRecord;
    EditText eventName;
    Spinner eventType;
    RatingBar ratingBar;
    EditText chooseTime;
    String timeResult,dateTimeResult;
    int day,month,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        eventName = findViewById(R.id.eventName);
        eventType = findViewById(R.id.spinner);
        ratingBar = findViewById(R.id.ratingBar);
        listRecord = new ArrayList();
        timeResult="";
        dateTimeResult="";

        //Time
        chooseTime = findViewById(R.id.etChooseTime);
        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(recordActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        timeResult = hourOfDay + " : " + minutes;
                        chooseTime.setText(timeResult);
                        dateTimeResult += timeResult;
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

       /* //date
        DatePicker datePicker = findViewById(R.id.datePicker1);
        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth() + 1;
        year = datePicker.getYear();*/
    }

    public void onButtonClose(View view) {
        finish();
    }

    // Save the name to file and ArrayList
    public void onButtonSave(View view) {
        try
        {
            record re = new record();
            re.eventName = eventName.getText().toString();
            re.eventType = eventType.getSelectedItem().toString();
            re.level =ratingBar.getRating();
            listRecord.add(re);
            //date
            DatePicker datePicker = findViewById(R.id.datePicker1);
            day = datePicker.getDayOfMonth();
            month = datePicker.getMonth() + 1;
            year = datePicker.getYear();

            dateTimeResult += "    " + month + "/" + day  + "/" + year;
            // to save to file "eventData.txt" in data/data/packagename/File
            FileOutputStream ofile = openFileOutput("eventData.txt",MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(ofile);
            osw.write("Event:                       "+ re.eventName + "\n");
            osw.write("Event type:              "+ re.eventType + "\n");
            osw.write("Importance Level:  " + re.level + " Star" + "\n");
            osw.write("Date/Time:  " + dateTimeResult + "\n\n");
            osw.flush();
            osw.close();

            dateTimeResult ="";
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();
    }
}
