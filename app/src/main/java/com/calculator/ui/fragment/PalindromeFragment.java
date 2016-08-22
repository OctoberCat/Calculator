package com.calculator.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.calculator.R;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by valentyn on 07.08.16.
 */
public class PalindromeFragment extends Fragment {
    @Bind(R.id.palindrome_answer)
    TextView palindromeAnswer;
    @Bind(R.id.input_layout_palindrome)
    TextInputLayout inputLayoutPalindrome;
    @Bind(R.id.input_palindrome)
    EditText inputPalindrome;

    @BindString(R.string.pal_correct)
    String palCorrect;
    @BindString(R.string.pal_incorrect)
    String palIncorrect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_palindrome, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;

    }

    @OnClick(R.id.palindrome_btn)
    void calculate() {
        if (inputPalindrome.getText().toString().equals("")) {
            inputLayoutPalindrome.setError("Invalid input. Fill this field, please");
        } else {
            inputLayoutPalindrome.setErrorEnabled(false);
            long number = Long.valueOf(inputPalindrome.getText().toString());
            if (isPalindrome(number)) {
                palindromeAnswer.setText(palCorrect);
            } else {
                palindromeAnswer.setText(palIncorrect);
            }
        }
    }

    boolean isPalindrome(long num) {
        long original = num;
        long reverse = 0;
        while (original > 0) {
            reverse = reverse * 10 + original % 10;
            original /= 10;
        }

        return num == reverse;
    }
}
