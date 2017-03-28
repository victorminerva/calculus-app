package com.minervavi.app.workcalcapp.fragment;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;
import com.minervavi.app.workcalcapp.mvp.calcular.ICalcular;
import com.minervavi.app.workcalcapp.mvp.fragment.FragmentPresenter;
import com.minervavi.app.workcalcapp.mvp.fragment.IFragment;

public class DadosRetroativoFragment extends Fragment implements IFragment.IFragmentView, ICalcular.ICalcularView {
    protected IFragment.IFragmentPresenter fragmentPresenter;

    protected CurrencyEditText      etSalarioAnterior;
    protected EditText              etPercentual;
    protected EditText              etQtdMeses;
    protected FloatingActionButton  fab;

    protected DadosInput            dadosInput;
    protected DadosResult           result;

    protected SharedPreferences     preferences;

    public DadosRetroativoFragment() {
        // Required empty public constructor
    }

    public static DadosRetroativoFragment newInstance() {
        DadosRetroativoFragment fragment = new DadosRetroativoFragment();
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
        View view = inflater.inflate(R.layout.fragment_dados_retroativo, container, false);
        init(view);

        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentPresenter.showSlideUpFragCurrent(fab);
                replaceContainerSlideWithFragCurrent();
            }
        });

        return view;
    }

    @Override
    public void init(View view) {
        preferences = getContext().getSharedPreferences(getString(R.string.preference_file_key), Activity.MODE_PRIVATE);

        this.fragmentPresenter  = new FragmentPresenter();
        this.fragmentPresenter.setView(this.getContext());
        this.fragmentPresenter.setActivity(getActivity());

        this.etSalarioAnterior  = (CurrencyEditText) view.findViewById(R.id.et_salario_anterior);
        this.etPercentual       = (EditText) view.findViewById(R.id.et_percentual);
        this.etQtdMeses         = (EditText) view.findViewById(R.id.et_qtd_meses);
        this.fab                = (FloatingActionButton) view.findViewById(R.id.fab);

        fragmentPresenter.recuperaDadosRetroativo(preferences, etSalarioAnterior, etPercentual, etQtdMeses);
    }

    @Override
    public void replaceContainerSlideWithFragCurrent() {

    }

    @Override
    public Boolean validaDadosObrigatorios() {
        return null;
    }

    @Override
    public void calcular() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentPresenter.manterDadosRetroativo(preferences, etSalarioAnterior, etPercentual, etQtdMeses);
    }
}
