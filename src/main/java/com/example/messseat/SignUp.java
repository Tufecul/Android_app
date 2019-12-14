package com.example.messseat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private EditText usermail,username,userpassword,usermobile;
    private TextView signup,acchave;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mAuth = FirebaseAuth.getInstance();

        usermail=findViewById(R.id.signupmail);
        username=findViewById(R.id.signupname);
        userpassword=findViewById(R.id.signuppassword);
        usermobile=findViewById(R.id.signupmobile);
        signup=findViewById(R.id.signUptext);
        acchave=findViewById(R.id.already);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=usermail.getText().toString().trim();
                String name=username.getText().toString().trim();
                String pass=userpassword.getText().toString().trim();
                String phn=usermobile.getText().toString().trim();

                if(mail.isEmpty())
                {
                    usermail.setError("Enter an email address");
                    usermail.requestFocus();
                    return;
                }

                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches())
                {
                    usermail.setError("Enter a valid email address");
                    usermail.requestFocus();
                    return;
                }

                if(pass.isEmpty())
                {
                    usermail.setError("Enter a password");
                    usermail.requestFocus();
                    return;
                }
                if(pass.length()<8)
                {
                    userpassword.setError("Enter at least 8 number or charecter");
                    userpassword.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration is successfull", Toast.LENGTH_SHORT).show();

                        }


                        else {
                            Toast.makeText(getApplicationContext(), "Registration is not  successfull", Toast.LENGTH_SHORT).show();


                        }

                    }
                });



            }
        });

        acchave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUp.this,MainActivity.class);
                startActivity(i);

            }
        });

    }
}
