package com.minervavi.app.workcalcapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minervavi.app.workcalcapp.R;

public class DadosDecimoFragment extends Fragment {

    public DadosDecimoFragment() {
        // Required empty public constructor
    }

    public static DadosDecimoFragment newInstance() {
        DadosDecimoFragment fragment = new DadosDecimoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dados_decimo, container, false);
    }

}
