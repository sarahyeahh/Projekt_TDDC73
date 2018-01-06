package com.example.sarah.projekt_tddc73;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    MainActivity mainActivity;
    PasswordStrengthMeter psm;


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.sarah.projekt_tddc73", appContext.getPackageName());
    }

    public void testText() {
        // simulate user action to input some value into EditText:
        final EditText mUsername = (EditText) psm.user;
        final EditText mPassword = (EditText) psm.inputPassword;
        final EditText mMail = (EditText) psm.mail;

        final Button button = (Button) psm.b;
        final Boolean checkuser = (Boolean) psm.checkUser;

        mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                mUsername.setText("Sarah");
                mMail.setText("sarah@mail.se");
                mPassword.setText("Hello123");
            }
        });

        // Check if the EditText is properly set:
        assertEquals("Sarah", mUsername.getText());
        assertEquals("sarah@mail.se", mMail.getText());
        assertEquals("Hello123", mPassword.getText());
        assertTrue(checkuser);

    }
}
