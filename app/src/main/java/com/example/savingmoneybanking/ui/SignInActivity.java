package com.example.savingmoneybanking.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savingmoneybanking.MainActivity;
import com.example.savingmoneybanking.R;
import com.example.savingmoneybanking.database.DatabaseHelper;

public class SignInActivity extends AppCompatActivity {
    TextView txtSignUp;
    EditText edtEmail, edtPass;
    Button btnLogin;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        anhXa();
        databaseHelper = new DatabaseHelper(getApplicationContext());
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
        btnLogin.setOnClickListener(clickToLogin());
    }


    private void anhXa() {
        txtSignUp = findViewById(R.id.txtSignUp);
        edtEmail = findViewById(R.id.edtAccount);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @NonNull
    private View.OnClickListener clickToLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPass.getText().toString();
                String userAdmin = edtEmail.getText().toString();
                String passAdmin = edtPass.getText().toString();
                Boolean isValid = checkAccountUser(email,password);
                Boolean checkAccount = databaseHelper.checkAccount(email, password);
                Boolean checkEmail = databaseHelper.checkEmail(email);
                if(userAdmin.equals("admin@gmail.com") && passAdmin.equals("admin123")) {
                    Toast.makeText(SignInActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignInActivity.this, TransactionActivity.class);
                    startActivity(i);
                    finish();
                }
                if(isValid && checkAccount == true){
                    Toast.makeText(SignInActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(i);
                }else if(isValid && checkEmail == false ){
                    Toast.makeText(SignInActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SignInActivity.this, "Chưa đăng ký tài khoản", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private boolean checkAccountUser(String email, String password){
        if(email.isEmpty()){
            edtEmail.setError("Vui lòng không để trống");
            return false;
        }
        if(!isValidEmail(email)){
            edtEmail.setError("Sai cách nhập");
            return false;
        }
        if(password.isEmpty()){
            edtPass.setError("Vui lòng không để trống");
            return false;
        }
        return true;
    }
}