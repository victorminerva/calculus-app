package com.minervavi.app.workcalcapp.fragment;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;
import com.minervavi.app.workcalcapp.mvp.calcular.CalcularHEPresente;
import com.minervavi.app.workcalcapp.mvp.calcular.ICalcular;
import com.minervavi.app.workcalcapp.mvp.calcular.ICalcularHoraExtra;
import com.minervavi.app.workcalcapp.mvp.fragment.FragmentPresenter;
import com.minervavi.app.workcalcapp.mvp.fragment.IFragment;
import com.vicmikhailau.maskededittext.MaskedEditText;

public class DadosHoraExtraFragment extends Fragment implements IFragment.IFragmentView, ICalcular.ICalcularView {

    private IFragment.IFragmentPresenter    fragmentPresenter;
    private ICalcularHoraExtra              calcularPresenter;

    private DadosInput              dadosInput;
    private DadosResult             result;

    private FloatingActionButton    fab;
    private MaskedEditText          etJornadaMensal;
    private MaskedEditText          etAdicionalHE;
    private MaskedEditText          etNumHoraExtra;

    private Double                  valorUnitarioHE;
    private Double                  valorTotalHE;

    private SharedPreferences       preferences;

    public DadosHoraExtraFragment() {
        // Required empty public constructor
    }

    public static DadosHoraExtraFragment newInstance() {
        DadosHoraExtraFragment fragment = new DadosHoraExtraFragment();
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
        final View view = inflater.inflate(R.layout.fragment_dados_hora_extra, container, false);
        init(view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaDadosObrigatorios()) {
                    dadosInput = calcularPresenter.populaDadosHoraExtra(view, etJornadaMensal, etAdicionalHE, etNumHoraExtra);
                    calcular();
                    result = calcularPresenter.populaDadosResult(dadosInput, valorUnitarioHE, valorTotalHE);

                    fragmentPresenter.showSlideUpFragCurrent(fab);
                    replaceContainerSlideWithFragCurrent();
                }
            }
        });

        return view;
    }

    @Override
    public void init(View view) {
        preferences = getContext().getSharedPreferences(getString(R.string.preference_file_key), Activity.MODE_PRIVATE);

        this.fragmentPresenter = new FragmentPresenter();
        this.fragmentPresenter.setView(this.getContext());
        this.fragmentPresenter.setActivity(getActivity());

        this.calcularPresenter  = new CalcularHEPresente();
        this.result             = new DadosResult();

        this.etNumHoraExtra     = (MaskedEditText) view.findViewById(R.id.et_num_hora_extra);
        this.etAdicionalHE      = (MaskedEditText) view.findViewById(R.id.et_adicional_he);
        this.etJornadaMensal    = (MaskedEditText) view.findViewById(R.id.et_jornada_mensal);
        this.fab                = (FloatingActionButton) view.findViewById(R.id.fab);

        fragmentPresenter.recuperaDadosHoraExtra(preferences, etJornadaMensal, etAdicionalHE, etNumHoraExtra);
    }

    @Override
    public void replaceContainerSlideWithFragCurrent() {
        ResultDadosHoraExtraFragment fragment = ResultDadosHoraExtraFragment.newInstance(result);
        fragmentPresenter.replaceContainerSlideWithFragCurrent(getFragmentManager(), fragment);
    }

    @Override
    public Boolean validaDadosObrigatorios() {
        return calcularPresenter.validaDadosObrigatorios(getView(), etJornadaMensal, etAdicionalHE, etNumHoraExtra);
    }

    @Override
    public void calcular() {
        valorUnitarioHE  = calcularPresenter.calcularValorHoraExtra(dadosInput);
        valorTotalHE     = calcularPresenter.calcularTotalHoraExtra(dadosInput, valorUnitarioHE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentPresenter.manterDadosHoraExtra(preferences, etJornadaMensal, etAdicionalHE, etNumHoraExtra);
    }
}
