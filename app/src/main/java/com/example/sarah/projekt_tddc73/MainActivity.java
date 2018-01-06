package com.example.sarah.projekt_tddc73;

/* Sarah Fosse sarfo265
   Projekt
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create account button
        b = new Button(this);
        b.setText("Create account");
        //Initialize it as disabled
        b.setEnabled(false);

        final PasswordStrengthMeter psm = new PasswordStrengthMeter(this, b);

        //If all the fields are filled correctly, this function will be called.
        psm.setOnPandClickListener(new PClickListener() {
            @Override
            public void canCreate() {

                Toast.makeText(MainActivity.this, "Account can be created", Toast.LENGTH_SHORT).show();
                //Enables Create button.
                b.setEnabled(true);
            }
        });

        psm.addView(b);
        setContentView(psm);
    }
}
