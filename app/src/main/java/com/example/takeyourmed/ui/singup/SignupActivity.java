package com.example.takeyourmed.ui.singup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.takeyourmed.MainActivity;
import com.example.takeyourmed.R;
import com.example.takeyourmed.data.model.LoggedInUser;
import com.example.takeyourmed.data.util.CustomToast;
import com.example.takeyourmed.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText username,email,phone,password,confirmPassword;
    Button btn_confirm,btn_cancel;

    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.edit_username);
        email = findViewById(R.id.edit_email);
        phone = findViewById(R.id.edit_phone);
        password = findViewById(R.id.edit_password);
        confirmPassword = findViewById(R.id.edit_confirmPassword);

        btn_confirm = findViewById(R.id.btn_signup);
        btn_cancel = findViewById(R.id.btn_cancel);

        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameI = username.getText().toString();
                final String emailI = email.getText().toString();
                final String phoneI = phone.getText().toString();
                final String passwordI = password.getText().toString();
                final String confirmI = confirmPassword.getText().toString();

                if(usernameI.isEmpty())
                {
                    username.setError(("Please provide username"));
                    username.requestFocus();
                }
                else if(emailI.isEmpty())
                {
                    email.setError("Please provide Email");
                    email.requestFocus();
                }
                else if(phoneI.isEmpty())
                {
                    phone.setError("Please provide phone number");
                    phone.requestFocus();
                }
                else if(passwordI.isEmpty())
                {
                    password.setError("Please provide password");
                    password.requestFocus();
                }
                else if(confirmI.isEmpty())
                {
                    confirmPassword.setError("Please provide confirm Password");
                    confirmPassword.requestFocus();
                }
                else if(!passwordI.equals(confirmI))
                {
                    confirmPassword.setError("password did not match!");
                    confirmPassword.requestFocus();
                }
                else if(!(usernameI.isEmpty() && emailI.isEmpty() && passwordI.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(emailI,passwordI)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        CustomToast.createToast(SignupActivity.this,
                                                "Signup Unsuccessful, Please try Again!" + task.getException().getMessage(),
                                                true);
                                    }
                                    else
                                    {
                                        LoggedInUser user = new LoggedInUser(usernameI, emailI, phoneI);
                                        String uid = task.getResult().getUser().getUid();
                                        firebaseDatabase.getReference(uid).setValue(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);

                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        intent.putExtra("username", usernameI);
                                                        startActivity(intent);
                                                    }
                                                });
                                    }
                                }
                            });
                }
                else
                {
                    CustomToast.createToast(SignupActivity.this, "Error Occured!", true);
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

    }
}