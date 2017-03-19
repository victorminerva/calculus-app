package com.minervavi.app.workcalcapp.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.databinding.FragmentResultDadosSalarioLiqBinding;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;

public class ResultDadosSalarioLiqFragment extends Fragment {
    private FragmentResultDadosSalarioLiqBinding binding;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_dados_salario_liq, container, false);

        DadosResult result = new DadosResult();

        binding.setResult(result);

        return binding.getRoot();
    }

}
