package com.github.ugwulo.afrilang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button privateUser = (Button) findViewById(R.id.button_reg_individual);
        Button signIn = (Button) findViewById(R.id.button_sign_in);


        privateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PrivateUserRegActivity.class);
                startActivity(intent);

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(signInIntent);
            }
        });


    }
}