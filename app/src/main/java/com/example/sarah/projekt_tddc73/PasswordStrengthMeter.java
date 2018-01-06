package com.example.sarah.projekt_tddc73;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class PasswordStrengthMeter extends LinearLayout {

    //Declaration of variables.
    Context c;

    PClickListener listener;

    Button b;

    EditText user;
    EditText inputPassword;
    EditText mail;

    RatingBar r;

    TextView message;
    TextView userView;
    TextView mailView;
    TextView inputView;
    TextView description;
    TextView starText;

    //Set all the booleans to false.
    boolean checkUser = false;
    boolean checkMail = false;
    boolean checkPassword = false;
    boolean checkLength = false;

    //Constructor
    public PasswordStrengthMeter(Context context, Button b) {
        super(context);
        this.c = context;
        this.b = b;
        init();
        addListeners();
    }

    public PasswordStrengthMeter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordStrengthMeter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PasswordStrengthMeter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //Initializating function for all the components in the view.
    private void init() {

        //Layout
        LinearLayout.LayoutParams paramsW = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsW.gravity = Gravity.CENTER;
        setOrientation(LinearLayout.VERTICAL);

        //Header
        description = new TextView(c);
        description.setText("Create a new user");
        description.setLayoutParams(paramsW);
        description.setTextSize(30);
        description.setTypeface(Typeface.DEFAULT_BOLD);

        //User field
        userView = new TextView(c);
        userView.setText("User: ");
        user = new EditText(c);
        user.setSingleLine(true);

        //Mail field
        mailView = new TextView(c);
        mailView.setText("Mail: ");
        mail = new EditText(c);
        mail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        mail.setSingleLine(true);

        //Password field
        inputView = new TextView(c);
        inputView.setText("Password: ");
        inputPassword = new EditText(c);
        inputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        inputPassword.setSingleLine(true);

        //Description for the password.
        message = new TextView(c);
        message.setText("Password needs to be at least 8 characters.");

        //RatingBar
        r = new RatingBar(c);
        r.setLayoutParams(paramsW);
        r.setActivated(false);
        r.setRating(0);

        //Description for the stars
        starText = new TextView(c);
        starText.setText("Security level");
        starText.setGravity(Gravity.CENTER);

        //Add all the components.
        addView(description);
        addView(userView);
        addView(user);
        addView(mailView);
        addView(mail);
        addView(inputView);
        addView(inputPassword);
        addView(message);
        addView(r);
        addView(starText);
    }

    //Function that checks the security of the created password.
    private int checkPassword(){

        //Get password from password field
        String password = inputPassword.getText().toString();

        //Get password length
        int length = password.length();

        //A counter, add a point for every security level.
        int points = 0;

        //LowerCase too see if the password changes.
        String low = password.toLowerCase();

        //Create regex to compare with the password
        String numRegex   = ".*[0-9].*";
        String specialRegex = ".*[!@#$%&*()_+=|<>¤/´`?{}\\[\\]~-].*";

        // 1. More than 8 characters
        if(length>8){

            checkLength = true;
            //System.out.println("Adding one point... more than 8 letters ");
            points++;

            // 2. More than 12 characters
            if(length>12){
               // System.out.println("Adding one point... more than 12 letters");
                points++;
            }
        }

        // 3. Big and small letters
        if(password != low){
           // System.out.println("Adding one point... contains big and small letters");
            points++;
        }

        // 4. Contains numbers?
        if (password.matches(numRegex)) {
          //  System.out.println("Adding one point... contains numbers ");
            points++;
        }

        // 5. Special characters
        if(password.matches(specialRegex)){
           // System.out.println("Adding one point... contains special characters ");
            points++;
        }

        System.out.println("Total points: " + points);
        return points;
    }

    //Initialization for all the listeners for the different compontents in the view.
    public void addListeners(){

        //Add TextWatcher to user field
        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String userName = user.getText().toString();

                //Set background color to red until it is correct
                user.setBackgroundColor(0x80ff0000); //Opaque red

                //Check if field is empty
                if(userName.matches("")){
                    //Warns if the field is not filled.
                    Toast.makeText(c, "There has to be a Username", Toast.LENGTH_SHORT).show();
                }
                else{
                    //If user field is filled correctly, set true.
                    checkUser = true;
                    user.setBackgroundColor(0x8000ff00); //Opaque green
                }

                //Check if the user is allowed to create an account yet.
                checkAndCall();
            }
        });


        //Add TextWatcher to mail field
        mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String mailAdress = mail.getText().toString();

                //Set background color to red until it is correct
                mail.setBackgroundColor(0x80ff0000); //Opaque red

                if(mailAdress.contains("@")){
                    if(mailAdress.endsWith(".se") || mailAdress.endsWith(".com") || mailAdress.endsWith(".nu") ){
                        checkMail = true;
                        System.out.println("Mail TRUE");
                        mail.setBackgroundColor(0x8000ff00); //Opaque green
                    }
                }

                //Check if the user is allowed to create an account yet.
                checkAndCall();
            }
        });

        //Add TextWatcher to password field
        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //Sets rating depending on how secure the password is.
                int rating = checkPassword();
                r.setRating(rating);
                int starColor;

                //Set background color to red until it is correct
                inputPassword.setBackgroundColor(0x80ff0000); //Opaque red

                System.out.println("Rating " + rating);

                //Change color on the rating stars depending on the rating points
                switch (rating) {
                    case 1: starColor = Color.RED;
                        checkPassword = false;
                        break;
                    case 2: starColor = Color.RED;
                        checkPassword = true;
                        //The password also has to have a qualified length.
                        if(checkLength){
                            inputPassword.setBackgroundColor(0x8000ff00);
                        }
                        break;
                    case 3: starColor = Color.YELLOW;
                        checkPassword = true;
                        if(checkLength){
                            inputPassword.setBackgroundColor(0x8000ff00);
                        }
                        break;
                    case 4: starColor = Color.YELLOW;
                        checkPassword = true;
                        if(checkLength){
                            inputPassword.setBackgroundColor(0x8000ff00);
                        }
                        break;
                    case 5: starColor = Color.GREEN;
                        checkPassword = true;
                        if(checkLength){
                            inputPassword.setBackgroundColor(0x8000ff00);
                        }
                        break;
                    default: starColor = Color.WHITE; //If the password are lower than one star.
                        checkPassword = false;
                        Toast.makeText(c,"Create a safer password!", Toast.LENGTH_SHORT).show();
                        break;
                }

                //Add the color to the stars
                LayerDrawable stars = (LayerDrawable) r.getProgressDrawable();
                stars.getDrawable(2).setColorFilter(starColor, PorterDuff.Mode.SRC_ATOP);

                //Check if the user is allowed to create an account yet.
                checkAndCall();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Add OnClickListener to create button, only works when enabled.
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, "Created an account", Toast.LENGTH_SHORT).show();
            }
        });

        //Makes the rating bar unclickable
        r.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    //Check function that checks that all the input fields are filled correctly.
    private void checkAndCall() {

        System.out.println("user " + checkUser + " mail " + checkMail + " lenght " + checkLength);

        //Check if all the fields are correctly filled.
        if(checkUser && checkMail && checkPassword && checkLength){
            if (listener != null){
                //canCreate() is called which enables the "Create account"-button.
                listener.canCreate();
            }
        }
    }

    public void setOnPandClickListener(PClickListener listener){
        this.listener = listener;
    }

}
