package com.example.harishmurari.curries.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.harishmurari.curries.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by hmurarishetty on 9/24/17.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText user, pass;
    private Button register, reguser;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);

        auth = FirebaseAuth.getInstance();

//        if(auth.getCurrentUser() != null) {
//            finish();
//            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//        }

        user = (EditText) findViewById(R.id.user_name);
        pass = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        reguser = (Button) findViewById(R.id.registered_user);

        reguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String email = user.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Toast.makeText(getApplicationContext(),"createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_LONG).show();

                                if(task.isSuccessful()) {
                                    finish();
                                    Toast.makeText(getApplicationContext(),"Registration Success", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                                } else {
                                    Toast.makeText(getApplicationContext(), "Registration Error", Toast.LENGTH_LONG).show();
                                }
                            }

                        });
            }
        });

    }

}

