package com.minervavi.app.workcalcapp.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.databinding.FragmentResultDadosHoraExtraBinding;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;

public class ResultDadosHoraExtraFragment extends Fragment {
    private FragmentResultDadosHoraExtraBinding binding;

    private static DadosResult result;

    public ResultDadosHoraExtraFragment() {
        // Required empty public constructor
    }

    public static ResultDadosHoraExtraFragment newInstance(DadosResult resultParam) {
        ResultDadosHoraExtraFragment fragment = new ResultDadosHoraExtraFragment();
        result = resultParam;
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_dados_hora_extra, container, false);

        binding.setResult(result);

        return binding.getRoot();
    }

}
