package com.minervavi.app.workcalcapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minervavi.app.workcalcapp.R;

public class ResultDadosSalarioLiqFragment extends Fragment {

    public ResultDadosSalarioLiqFragment() {
        // Required empty public constructor
    }

    public static ResultDadosSalarioLiqFragment newInstance() {
        ResultDadosSalarioLiqFragment fragment = new ResultDadosSalarioLiqFragment();
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
        return inflater.inflate(R.layout.fragment_result_dados_salario_liq, container, false);
    }

}
