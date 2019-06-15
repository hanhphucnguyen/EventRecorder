package com.example.eventrecorder_phucnguyen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class showActivity extends AppCompatActivity {
    TextView showRecordText;
    int numberOfLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        showRecordText = findViewById(R.id.showRecordText);
        showRecordText.setMovementMethod(new ScrollingMovementMethod());
    }


    /*// Read the name from file OLD version
    public void onButtonRead(View view) {

        try {
            numberOfLine = 0;
            // reading from data/data/packagename/File
            FileInputStream fin = openFileInput("eventData.txt");
            InputStreamReader isr = new InputStreamReader(fin);
            char[] inputBuffer = new char[100];
            String str = "";
            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
                numberOfLine += 3;
            }
            showRecordText.setText(str);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }*/

    // Read the name from file
    public void onButtonRead(View view) {
        String str = "";
        numberOfLine = 0;
        try // Open the file
        {
            FileInputStream fstream = openFileInput("eventData.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                str += strLine + "\n";
                numberOfLine++;
            }
            //Close the input stream
            fstream.close();
            showRecordText.setText(str);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void deleteAll(View view) {

        //Delete old txt data
        try {
            FileOutputStream ofile = openFileOutput("eventData.txt", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        showRecordText.setText("");
        Toast.makeText(getApplicationContext(), "Deleted all record!", Toast.LENGTH_LONG).show();
    }

    public void deleteLast(View view) {
        int count = 0;
        String str = "";
        if (numberOfLine < 3) {
            Toast.makeText(getApplicationContext(), "no event left!", Toast.LENGTH_LONG).show();
        } else {
            try // Open the file
            {
                FileInputStream fstream = openFileInput("eventData.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String strLine;

                //Read File Line By Line
                while (((strLine = br.readLine()) != null) && (count <= numberOfLine - 6)) {
                    count++;
                    str += strLine + "\n";
                }
                //Close the input stream
                fstream.close();
                showRecordText.setText(str);
                FileOutputStream ofile = openFileOutput("eventData.txt", MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(ofile);
                osw.write(str);
                osw.flush();
                osw.close();
                numberOfLine = count;
            } catch (Exception ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public void onButtonClose(View view) {
        finish();
    }
}
