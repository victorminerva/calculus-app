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

import com.blackcat.currencyedittext.CurrencyEditText;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;
import com.minervavi.app.workcalcapp.mvp.calcular.CalcularFeriasPresenter;
import com.minervavi.app.workcalcapp.mvp.calcular.ICalcular;
import com.minervavi.app.workcalcapp.mvp.calcular.ICalcularFerias;
import com.minervavi.app.workcalcapp.mvp.fragment.FragmentPresenter;
import com.minervavi.app.workcalcapp.mvp.fragment.IFragment;

public class DadosFeriasFragment extends Fragment implements IFragment.IFragmentView, ICalcular.ICalcularView  {

    private IFragment.IFragmentPresenter    fragmentPresenter;
    private ICalcularFerias                 calcularPresenter;

    private FloatingActionButton    fab;
    private CurrencyEditText        etValorMedioHE;
    private EditText                etDiasFerias;
    private RadioGroup              radioAbonar;
    private RadioGroup              radioAdiantar;

    private DadosInput              dadosInput;
    private DadosResult             result;

    private Double                  valorBase;
    private Double                  valorAbono;
    private Double                  valorAdiantamento;
    private Double                  valorTerco;
    private Double                  salarioBase;
    private Double                  inss;
    private Double                  percentualInss;
    private Double                  irrf;
    private Double                  percentualIrrf;
    private Double                  salarioLiq;
    private Integer                 qtdeDiasFeriasAux;

    private SharedPreferences       preferences;

    public DadosFeriasFragment() {
        // Required empty public constructor
    }

    public static DadosFeriasFragment newInstance() {
        DadosFeriasFragment fragment = new DadosFeriasFragment();
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
        final View view = inflater.inflate(R.layout.fragment_dados_ferias, container, false);
        init(view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaDadosObrigatorios()) {
                    dadosInput = calcularPresenter.populaDadosFerias(view, etValorMedioHE, etDiasFerias, radioAbonar, radioAdiantar);
                    calcular();
                    result = calcularPresenter.populaDadosResult(dadosInput, valorTerco, valorAbono, valorAdiantamento, inss, percentualInss, irrf, percentualIrrf, salarioLiq);

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

        fragmentPresenter = new FragmentPresenter();
        fragmentPresenter.setView(this.getContext());
        fragmentPresenter.setActivity(getActivity());

        this.calcularPresenter  = new CalcularFeriasPresenter();
        this.result             = new DadosResult();

        this.etValorMedioHE = (CurrencyEditText) view.findViewById(R.id.et_valor_medio_he);
        this.etDiasFerias   = (EditText) view.findViewById(R.id.et_dias_ferias);
        this.radioAbonar    = (RadioGroup) view.findViewById(R.id.radio_abonar);
        this.radioAdiantar  = (RadioGroup) view.findViewById(R.id.radio_adiantar);

        this.fab            = (FloatingActionButton) view.findViewById(R.id.fab);

        fragmentPresenter.recuperaDadosFerias(view, preferences, etValorMedioHE, etDiasFerias, radioAbonar, radioAdiantar);
    }

    @Override
    public void replaceContainerSlideWithFragCurrent() {
        ResultDadosFeriasFragment fragment = ResultDadosFeriasFragment.newInstance(result);
        fragmentPresenter.replaceContainerSlideWithFragCurrent(getFragmentManager(), fragment);
    }

    @Override
    public Boolean validaDadosObrigatorios() {
        return calcularPresenter.validaDadosObrigatorios(getView(), etValorMedioHE, etDiasFerias,radioAbonar,radioAdiantar);
    }

    @Override
    public void calcular() {
        dadosInput.getDadosSalarioLiq().setSalarioBruto(dadosInput.getDadosSalarioLiq().getSalarioBruto() + dadosInput.getDadosFerias().getMediaHorasExtrasAno());
        this.valorAdiantamento= dadosInput.getDadosFerias().getAdiantarPrimeiraDecimo() ? (dadosInput.getDadosSalarioLiq().getSalarioBruto() / 100) / 2 : null;
        this.valorAbono = null;
        if(dadosInput.getDadosFerias().getAbonoPecuniario()) {
            qtdeDiasFeriasAux = dadosInput.getDadosFerias().getQtdeDiasFerias();
            dadosInput.getDadosFerias().setQtdeDiasFerias(30 - qtdeDiasFeriasAux);
            this.valorAbono = calcularPresenter.calcularValorBasePorQtdeDias(dadosInput) / 100;
            dadosInput.getDadosFerias().setQtdeDiasFerias(qtdeDiasFeriasAux);
        }

        this.valorBase        = calcularPresenter.calcularValorBasePorQtdeDias(dadosInput);
        this.valorTerco       = calcularPresenter.calcularTercoFerias(valorBase);
        this.salarioBase      = valorBase + valorTerco;
        dadosInput.getDadosSalarioLiq().setSalarioBruto(salarioBase);

        this.inss             = calcularPresenter.calcularINSS(dadosInput);
        this.percentualInss   = calcularPresenter.percentualINSS(dadosInput);
        this.irrf             = calcularPresenter.calcularIRRF(dadosInput, inss);
        this.percentualIrrf   = calcularPresenter.percentualIRRF(dadosInput, inss);
        this.salarioLiq       = calcularPresenter.calularSalarioFerias(dadosInput, inss, irrf, valorAbono, valorAdiantamento);
        dadosInput.getDadosSalarioLiq().setSalarioBruto(valorBase);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentPresenter.manterDadosFerias(preferences, etValorMedioHE, etDiasFerias, radioAbonar, radioAdiantar);
    }
}
