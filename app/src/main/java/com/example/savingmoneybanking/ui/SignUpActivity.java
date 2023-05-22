package com.example.savingmoneybanking.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savingmoneybanking.R;
import com.example.savingmoneybanking.database.DatabaseHelper;
import com.example.savingmoneybanking.model.User;

public class SignUpActivity extends AppCompatActivity {
    private EditText edtEmailRes, edtNameRes, edtPassRes, edtConform;
    private TextView txtLogin;
    private Button btnSignUp;
    private DatabaseHelper databaseHelper;
    private int id_user = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        anhXa();
        btnSignUp.setOnClickListener(clickToRegister());
    }

    @NonNull
    private View.OnClickListener clickToRegister() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper = new DatabaseHelper(getApplicationContext());
                String email = edtEmailRes.getText().toString();
                String username = edtNameRes.getText().toString();
                String password = edtPassRes.getText().toString();
                String confirm = edtConform.getText().toString();

                Boolean isValid = checkAccountRes(username) && checkPassRes(password, confirm);
                Boolean checkEmail = databaseHelper.checkEmail(email);
                if(isValid && checkEmail == true ){
                    databaseHelper.insertUser(email, username, password);
                    if(id_user != -1){
                        Toast.makeText(SignUpActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignUpActivity.this,SignInActivity.class);
                        startActivity(i);
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void anhXa() {
        edtEmailRes = findViewById(R.id.edtEmailRes);
        edtNameRes = findViewById(R.id.edtAccountRes);
        edtPassRes = findViewById(R.id.edtPassRes);
        edtConform = findViewById(R.id.edtPassConfirm);
        txtLogin = findViewById(R.id.txtLogin);
        btnSignUp = findViewById(R.id.btnRes);
    }
    private String value = "Vui lòng không để trống";
    boolean checkAccountRes(String username){
        if(username.isEmpty()){
            edtNameRes.setError(value);
            return false;
        }
        return true;
    }
    boolean checkPassRes(String password, String confirm){
        if(password.isEmpty()){
            edtPassRes.setError(value);
            return false;
        }
        if(confirm.isEmpty()){
            edtConform.setError(value);
            return false;
        }
        if(!password.equals(confirm)){
            Toast.makeText(this,"Xác nhận mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}