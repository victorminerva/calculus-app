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
import android.widget.RadioGroup;

import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;
import com.minervavi.app.workcalcapp.mvp.calcular.CalcularDecimoPresenter;
import com.minervavi.app.workcalcapp.mvp.calcular.ICalcular;
import com.minervavi.app.workcalcapp.mvp.calcular.ICalcularDecimo;
import com.minervavi.app.workcalcapp.mvp.fragment.FragmentPresenter;
import com.minervavi.app.workcalcapp.mvp.fragment.IFragment;

public class DadosDecimoFragment extends Fragment implements IFragment.IFragmentView, ICalcular.ICalcularView{

    private IFragment.IFragmentPresenter    fragmentPresenter;
    private ICalcularDecimo                 calcularPresenter;

    private EditText etNumMeses;
    private RadioGroup radioParcela;
    private FloatingActionButton    fab;

    private DadosInput              dadosInput;
    private DadosResult             result;

    private Double                  inss;
    private Double                  percentualInss;
    private Double                  irrf;
    private Double                  percentualIrrf;
    private Double                  parcelaBruto;
    private Double                  decimoLiquido;

    private SharedPreferences       preferences;

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
        final View view = inflater.inflate(R.layout.fragment_dados_decimo, container, false);
        init(view);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaDadosObrigatorios()) {
                    dadosInput  = calcularPresenter.populaDadosDecimo(view, etNumMeses, radioParcela);
                    calcular();
                    result      = calcularPresenter.populaDadosResult(dadosInput, inss, percentualInss, irrf, percentualIrrf, decimoLiquido);

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

        this.calcularPresenter  = new CalcularDecimoPresenter();
        this.result             = new DadosResult();

        this.fab            = (FloatingActionButton) view.findViewById(R.id.fab);
        this.radioParcela   = (RadioGroup) view.findViewById(R.id.radio_parcela);
        this.etNumMeses     = (EditText) view.findViewById(R.id.et_num_meses);

        fragmentPresenter.recuperaDadosDecimo(view, preferences, etNumMeses, radioParcela);
    }

    @Override
    public void replaceContainerSlideWithFragCurrent() {
        ResultDadosDecimoFragment fragment = ResultDadosDecimoFragment.newInstance(result);
        fragmentPresenter.replaceContainerSlideWithFragCurrent(getFragmentManager(), fragment);
    }

    @Override
    public Boolean validaDadosObrigatorios() {
        return calcularPresenter.validaDadosObrigatorios(getView(), etNumMeses, radioParcela);
    }

    @Override
    public void calcular() {

        this.parcelaBruto     = calcularPresenter.calcularValorParcelaBruto(dadosInput) * 100;
        dadosInput.getDadosSalarioLiq().setSalarioBruto(parcelaBruto);

        this.inss             = calcularPresenter.calcularINSS(dadosInput);
        this.percentualInss   = calcularPresenter.percentualINSS(dadosInput);
        this.irrf             = calcularPresenter.calcularIRRF(dadosInput, inss);
        this.percentualIrrf   = calcularPresenter.percentualIRRF(dadosInput, inss);
        this.decimoLiquido    = calcularPresenter.calcularDecimoLiq(dadosInput, inss, irrf);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentPresenter.manterDadosDecimo(preferences, etNumMeses, radioParcela);
    }
}
