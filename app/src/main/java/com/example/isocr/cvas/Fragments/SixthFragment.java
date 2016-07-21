package com.example.isocr.cvas.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.isocr.cvas.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SixthFragment extends Fragment {


    public SixthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sixth, container, false);
        getActivity().setTitle("Cuenta");
        return view;
    }

}
