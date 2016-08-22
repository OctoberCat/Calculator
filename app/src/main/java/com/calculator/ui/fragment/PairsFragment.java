package com.calculator.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.calculator.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by valentyn on 07.08.16.
 */
public class PairsFragment extends Fragment {
    @Bind(R.id.input_layout_pairs)
    TextInputLayout inputLayout;
    @Bind(R.id.input_pairs)
    EditText input_pairs;
    @Bind(R.id.pairs_answer)
    TextView answer;
    @BindString(R.string.no_pairs)
    String noPairs;
    @BindString(R.string.pairs_only)
    String pairsOnly;
    private final String TAG = PairsFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pairs, container, false);
        ButterKnife.bind(this, rootView);
        //input_pairs.addTextChangedListener(watcher);
        return rootView;

    }

//    private final TextWatcher watcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            if (!s.toString().matches("^[\\d\\s+]*$") || s.toString().isEmpty()) {
//                inputLayout.setError("Use digits and spaces only!");
//            } else {
//                inputLayout.setErrorEnabled(false);
//            }
//        }
//    };

    @OnClick(R.id.pairs_btn)
    void findPairs() {
        String[] strings;
        try {
            strings = input_pairs.getText().toString().split(" ");
            //ArrayList<int[]> list = new ArrayList<>();
            Log.i(TAG, "input String: " + Arrays.toString(strings));
            if (strings.length % 2 == 0 && strings.length > 2) {
                inputLayout.setErrorEnabled(false);

                int[][] pairs = new int[strings.length / 2][2];

                int m = 0;
                for (int i = 0; i < pairs.length; i++) {

                    for (int j = 0; j < pairs[i].length; j++) {
                        pairs[i][j] = Integer.parseInt(strings[m++]);
                    }

                }

                Log.i(TAG, "pairs input: " + Arrays.deepToString(pairs));

                int[][] result = findSublist(pairs);
                if (result.length == 0) {
                    answer.setText(noPairs);
                } else {
                    answer.setText(Arrays.deepToString(result));
                }
            } else {
                inputLayout.setError(pairsOnly);
            }
        } catch (NumberFormatException e) {
            inputLayout.setError("Invalid input!");
        }
    }

    public int[][] findSublist(int[][] input) {

        for (int[] v : input) {
            if (v.length != 2) {
                throw new IllegalArgumentException("You should provide pairs only");
            }
        }

        ArrayList<int[]> list = new ArrayList<>();
        ArrayList<int[]> resultList = new ArrayList<>();
        ArrayList<int[]> currentList = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            list.add(input[i]);
        }
        ListIterator<int[]> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (!iterator.hasPrevious()) {
                iterator.next();
            }
            int[] previous = iterator.previous();
            iterator.next();
            int[] next = iterator.next();
            if (previous[0] > next[0] || previous[1] < next[1]) {
                // currentList is not empty, we should check if it is bigger
                // than resultList
                if (!currentList.isEmpty()) {
                    if (resultList.isEmpty() || currentList.size() > resultList.size()) {
                        resultList.clear();
                        resultList.addAll(currentList);
                        currentList.clear();

                    }
                }

            } else {
                if (currentList.isEmpty()) {
                    currentList.add(previous);
                }
                currentList.add(next);
            }

            if (!iterator.hasNext() && currentList.size() > resultList.size()) {
                resultList.clear();
                resultList.addAll(currentList);
            }
        }

        int[][] output = new int[resultList.size()][2];
        int i = 0;
        for (int[] n : resultList) {
            output[i++] = n;
        }
        return output;
    }

}
