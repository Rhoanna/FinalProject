package com.example.lab8;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link nav_other#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nav_other extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView myTextView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public nav_other() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nav_other.
     */
    // TODO: Rename and change types and number of parameters
    public static nav_other newInstance(String param1, String param2) {
        nav_other fragment = new nav_other();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflates the custom fragment layout
        View view = inflater.inflate(
                R.layout.fragment_nav_other, container, false);

        // Finds the TextView in the custom fragment
        myTextView = (TextView)view.findViewById(R.id.nav_other);

        // Gets the data from the passed bundle
        Bundle bundle = getArguments();
        String message = bundle.getString("mText");

        // Sets the derived data (type String) in the
        // TextView
        myTextView.setText(message);



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_other, container, false);
    }
}