package com.calculator.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.calculator.R;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {

    private final String TAG = RegistrationActivity.class.getSimpleName();

    @Bind(R.id.first_name)
    EditText firstName;
    @Bind(R.id.last_name)
    EditText lastName;
    @Bind(R.id.email)
    EditText email;
    @Bind(R.id.phone)
    EditText phone;

    @Bind(R.id.child_last_name)
    EditText childLastName;
    @Bind(R.id.dob)
    EditText dateOfBirth;
    @Bind(R.id.reg_login)
    EditText childRegistrationLogin;
    @Bind(R.id.reg_password)
    EditText childRegistrationPassword;


    @Bind(R.id.input_layout_first_name)
    TextInputLayout layoutFirstName;
    @Bind(R.id.input_layout_last_name)
    TextInputLayout layoutlastName;
    @Bind(R.id.input_layout_email)
    TextInputLayout layoutEmail;
    @Bind(R.id.input_layout_phone)
    TextInputLayout layoutPhone;
    @Bind(R.id.input_layout_child_last_name)
    TextInputLayout layoutChildLastName;
    @Bind(R.id.input_layout_dob)
    TextInputLayout layoutDob;
    @Bind(R.id.input_layout_reg_login)
    TextInputLayout layoutRegLogin;
    @Bind(R.id.input_layout_reg_password)
    TextInputLayout layoutRegPassword;
    @BindString(R.string.empty_field_err)
    String emptyFieldError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.send_registration)
    void registrate() {

        if (firstName.getText().toString().isEmpty()) {
            layoutFirstName.setError(emptyFieldError);
        } else if (lastName.getText().toString().isEmpty()) {
            layoutlastName.setError(emptyFieldError);
        } else if (email.getText().toString().isEmpty()) {
            layoutEmail.setError(emptyFieldError);
        } else if (phone.getText().toString().isEmpty()) {
            layoutPhone.setError(emptyFieldError);
        } else {
            layoutFirstName.setErrorEnabled(false);
            layoutlastName.setErrorEnabled(false);
            layoutEmail.setErrorEnabled(false);
            layoutPhone.setErrorEnabled(false);
            Log.i(TAG, "registrate: firstName " + firstName.getText().toString());
            Log.i(TAG, "registrate: lastName " + lastName.getText().toString());
            Log.i(TAG, "registrate: email " + email.getText().toString());
            Log.i(TAG, "registrate: phone " + phone.getText().toString());
        }
        if (childLastName.getText().toString().isEmpty()) {
            layoutChildLastName.setError(emptyFieldError);
        } else if (childRegistrationLogin.getText().toString().isEmpty()) {
            layoutRegLogin.setError(emptyFieldError);
        } else if (childRegistrationPassword.getText().toString().isEmpty()) {
            layoutRegPassword.setError(emptyFieldError);
        } else if (dateOfBirth.getText().toString().isEmpty()) {
            layoutDob.setError(emptyFieldError);
        } else {
            layoutChildLastName.setErrorEnabled(false);
            layoutRegLogin.setErrorEnabled(false);
            layoutRegPassword.setErrorEnabled(false);
            layoutDob.setErrorEnabled(false);
            Log.i(TAG, "registrate: childLastName " + childLastName.getText().toString());
            Log.i(TAG, "registrate: childRegistrationLogin " + childRegistrationLogin.getText().toString());
            Log.i(TAG, "registrate: childRegistrationPassword " + childRegistrationPassword.getText().toString());
            Log.i(TAG, "registrate: dateOfBirth " + dateOfBirth.getText().toString());

        }
        Toast.makeText(this, " registration comleted", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.add_kids)
    void addChildren() {
        Toast.makeText(this, "add children clicked", Toast.LENGTH_LONG).show();
    }
}


