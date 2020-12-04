package com.example.bmiapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class FourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        final EditText username =  findViewById(R.id.username);

        final EditText password =  findViewById(R.id.password);

        final Button login =  findViewById(R.id.login);

        TextView content = findViewById(R.id.textView);
        content.setVisibility(View.VISIBLE);

        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FourActivity.this,SixActivity.class);
                startActivity(intent);
            }
        });

        password.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        final FirebaseAuth fAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    username.setError("Email is required. ");
                    return;
                }
                if (TextUtils.isEmpty(pwd)){
                    password.setError("Password is required. ");
                    return;
                }
                if(pwd.length() < 6){
                    password.setError("Password must be >= 6 Characters");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(FourActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(FourActivity.this);
                            builder.setIcon(R.drawable.ic_round);
                            builder.setTitle("Error!!");
                            builder.setMessage("Invalid username/password");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }
                });

            }
        });
        }


    public void imageclick(View v){
        Intent img = new Intent();
        img.setClass(this, ThirdActivity.class);
        startActivity(img);
    }

}