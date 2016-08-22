package com.calculator.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.calculator.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecoverActivity extends AppCompatActivity {

    @Bind(R.id.email_recovery)
    EditText emailRecovery;
    @Bind(R.id.recover_layout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.input_layout_recovery)
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.send_recovery)
    void sendRecovery() {

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailRecovery.getText()).matches()) {
            textInputLayout.setErrorEnabled(false);
            // Snackbar.make(coordinatorLayout,"Mail sent to " + emailRecovery.getText().toString(),Snackbar.LENGTH_LONG);
            Toast.makeText(this, "Mail sent to " + emailRecovery.getText().toString(), Toast.LENGTH_LONG).show();

        } else {
            textInputLayout.setError("invalid email!");
        }

    }
}
