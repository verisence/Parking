package com.example.parking.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = CreateAccountActivity.class.getSimpleName();

    private FirebaseAuth auth;
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editEmail) EditText editEmail;
    @BindView(R.id.editPassW) EditText editPassW;
    @BindView(R.id.editConfirmPassW) EditText editConfirmPassW;
    @BindView(R.id.signUpButton)
    Button signUpButton;
    @BindView(R.id.existingAccount) TextView existingAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();
        existingAccount.setOnClickListener(this);
        signUpButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == existingAccount){
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (view == signUpButton){
            createNewUser();
        }
    }

    private void createNewUser() {
        final String name = editName.getText().toString().trim();
        final String email = editEmail.getText().toString().trim();
        String password = editPassW.getText().toString().trim();
        String confirmPassword = editConfirmPassW.getText().toString().trim();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Authentication successful");
                        } else {
                            Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
