package com.example.isocr.cvas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {


    public EditText email, password;
    public Button login;
    public static String EMAIL_DEMO = "admin";
    public static String PASSWORD_DEMO = "admin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //FADE ANIMATION
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);

        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);

        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        layout.setAnimation(animation);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        TextView tv = (TextView) findViewById(R.id.pass);
        tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        EditTextStyles();

        //LOGIN EVENT
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String pa = password.getText().toString();

                if(em.equals(EMAIL_DEMO) & pa.equals(PASSWORD_DEMO)){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    //DRAWABLE STYLE
    public void EditTextStyles(){
        Drawable d = email.getCompoundDrawables()[0];
        Drawable wrappedDrawable = DrawableCompat.wrap(d);
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#444444"));
        email.setCompoundDrawablesWithIntrinsicBounds(wrappedDrawable, null, null, null);

        Drawable d1 = password.getCompoundDrawables()[0];
        Drawable wrappedDrawable2 = DrawableCompat.wrap(d1);
        DrawableCompat.setTint(wrappedDrawable2, Color.parseColor("#444444"));
        password.setCompoundDrawablesWithIntrinsicBounds(wrappedDrawable2, null, null, null);

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Drawable d = email.getCompoundDrawables()[0];
                    Drawable wrappedDrawable = DrawableCompat.wrap(d);
                    DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#1565C0"));
                    email.setCompoundDrawablesWithIntrinsicBounds(wrappedDrawable, null, null, null);
                }else {
                    Drawable d = email.getCompoundDrawables()[0];
                    Drawable wrappedDrawable = DrawableCompat.wrap(d);
                    DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#444444"));
                    email.setCompoundDrawablesWithIntrinsicBounds(wrappedDrawable, null, null, null);
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Drawable d = password.getCompoundDrawables()[0];
                    Drawable wrappedDrawable = DrawableCompat.wrap(d);
                    DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#1565C0"));
                    password.setCompoundDrawablesWithIntrinsicBounds(wrappedDrawable, null, null, null);
                }else {
                    Drawable d = password.getCompoundDrawables()[0];
                    Drawable wrappedDrawable = DrawableCompat.wrap(d);
                    DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#444444"));
                    password.setCompoundDrawablesWithIntrinsicBounds(wrappedDrawable, null, null, null);
                }
            }
        });

        //CONFIGURATION OF LAYOUT FOCUS
        LinearLayout touchInterceptor = (LinearLayout)findViewById(R.id.layout);
        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (email.isFocused()) {
                        Rect outRect = new Rect();
                        email.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            email.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                    if (password.isFocused()) {
                        Rect outRect = new Rect();
                        password.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            password.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                }
                return false;
            }
        });
    }

}
