package com.example.bridgeit.externalstoragefile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText mEditTextFileName,mEditTextData;
    Button mSaveButton,mReadButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextFileName = (EditText)findViewById(R.id.editText1);
        mEditTextData = (EditText)findViewById(R.id.editText2);
        mSaveButton = (Button)findViewById(R.id.button1);
        mReadButton = (Button)findViewById(R.id.button2);

        //Performing action on save button
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = mEditTextFileName.getText().toString();
                String data = mEditTextData.getText().toString();


                try {
                    File myFile = new File("/sdcard/"+filename);
                    myFile.createNewFile();
                    FileOutputStream fOut = new

                            FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new

                            OutputStreamWriter(fOut);
                    myOutWriter.append(data);
                    myOutWriter.close();
                    fOut.close();

                    Toast.makeText(getApplicationContext(),filename + " saved",Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Performing action on Read Button
        mReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename=mEditTextFileName.getText().toString();
                String aDataRow = "";
                String aBuffer = "";
                try {
                    File myFile = new File("/sdcard/"+filename);
                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(
                            new InputStreamReader(fIn));

                    while ((aDataRow = myReader.readLine()) != null) {
                        aBuffer += aDataRow + "\n";
                    }
                    myReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext

                        (),aBuffer,Toast.LENGTH_LONG).show();

            }
        });
    }
}
