package com.calculator.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.calculator.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by valentyn on 07.08.16.
 */
public class FactorialFragment extends Fragment {
    @Bind(R.id.factorial_answer)
    TextView factAnswer;
    @Bind(R.id.input_factorial)
    EditText inputPalindrome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_factorial, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;

    }

    @OnClick(R.id.factorial_btn)
    void calculate() {
        long n = Integer.valueOf(inputPalindrome.getText().toString());
        int factSum = findFactorialSum(n);
        factAnswer.setText(String.valueOf(factSum));
    }

    int findFactorialSum(long n) {

        int result;

        if (n < 0) {
            throw new IllegalArgumentException("Use positive numbers only");
        }
        if (n == 0 || n == 1) {
            return 1;
        }

        int[] factArr = factorialOf(n);

        result = findSum(factArr);
        return result;
    }

    int[] factorialOf(long n) {
        int[] res = new int[200];// just for sure
        res[0] = 1;// initial

        for (int i = 1; i <= n; i++) {
            int temp = 0;
            for (int j = 0; j < res.length; j++) {
                int x = res[j] * i + temp;
                res[j] = x % 10;
                temp = x / 10;
            }
        }

        return res;
    }

    int findSum(int[] factArr) {

        int sum = 0;
        for (int aFactArr : factArr) {
            if (aFactArr == 0)
                continue;
            sum += aFactArr;
        }

        return sum;
    }

}
