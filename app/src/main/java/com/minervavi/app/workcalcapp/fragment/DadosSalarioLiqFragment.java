package com.minervavi.app.workcalcapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.databinding.domain.DadosInput;
import com.minervavi.app.workcalcapp.databinding.domain.DadosResult;
import com.minervavi.app.workcalcapp.mvp.calcular.CalcularPresenter;
import com.minervavi.app.workcalcapp.mvp.calcular.ICalcular;
import com.minervavi.app.workcalcapp.mvp.fragment.FragmentPresenter;
import com.minervavi.app.workcalcapp.mvp.fragment.IFragment;

import java.util.List;

public class DadosSalarioLiqFragment extends Fragment implements IFragment.IFragmentView, ICalcular.ICalcularView {

    private IFragment.IFragmentPresenter fragmentPresenter;
    private ICalcular.ICalcularPresenter calcularPresenter;

    private CurrencyEditText        etSalario;
    private EditText                etNumDep;
    private CurrencyEditText        etDescontos;
    private ImageButton             btnAdd;
    private LinearLayout            llDescontos;
    private FloatingActionButton    fab;

    private DadosInput              dadosInput;
    private DadosResult             result;
    private Double                  descontoTotal;
    private Double                  inss;
    private Double                  percentualInss;
    private Double                  irrf;
    private Double                  percentualIrrf;
    private Double                  salarioLiq;

    private List<String>            listDescontos;

    public DadosSalarioLiqFragment() {
        // Required empty public constructor
    }

    public static DadosSalarioLiqFragment newInstance() {
        DadosSalarioLiqFragment fragment = new DadosSalarioLiqFragment();
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
        View view = inflater.inflate(R.layout.fragment_dados_salario_liq, container, false);
        init(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDescontos = fragmentPresenter.onAddDescontoClick(etDescontos, btnAdd, llDescontos);
                etDescontos.setText("0");
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaDadosObrigatorios()){
                    dadosInput = calcularPresenter.populaDadosSalarioLiq(etSalario, etNumDep, descontoTotal);
                    calcular();
                    result = calcularPresenter.populaDadosResult(dadosInput, inss, percentualInss, irrf, percentualIrrf, salarioLiq, descontoTotal);

                    fragmentPresenter.showSlideUpFragCurrent(fab);
                    replaceContainerSlideWithFragCurrent();
                }
            }
        });
    }

    @Override
    public void init(View view) {
        this.fragmentPresenter  = new FragmentPresenter();
        this.fragmentPresenter.setView(this.getContext());
        this.fragmentPresenter.setActivity(getActivity());

        this.calcularPresenter  = new CalcularPresenter();
        this.result             = new DadosResult();

        this.llDescontos    = (LinearLayout) view.findViewById(R.id.ll_descontos);
        this.etDescontos    = (CurrencyEditText) view.findViewById(R.id.et_descontos);
        this.btnAdd         = (ImageButton) view.findViewById(R.id.btn_add);
        this.etNumDep       = (EditText) view.findViewById(R.id.et_num_dep);
        this.etSalario      = (CurrencyEditText ) view.findViewById(R.id.et_salario);
        this.fab            = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    @Override
    public void replaceContainerSlideWithFragCurrent() {
        ResultDadosSalarioLiqFragment fragment  = ResultDadosSalarioLiqFragment.newInstance(result);
        fragmentPresenter.replaceContainerSlideWithFragCurrent(getFragmentManager(), fragment);
    }

    @Override
    public void calcular() {
        descontoTotal   = calcularPresenter.calcularDescontos(listDescontos);
        inss            = calcularPresenter.calcularINSS(dadosInput);
        percentualInss  = calcularPresenter.percentualINSS(dadosInput);
        irrf            = calcularPresenter.calcularIRRF(dadosInput, inss);
        percentualIrrf  = calcularPresenter.percentualIRRF(dadosInput, inss);
        salarioLiq      = calcularPresenter.calcularSalarioLiq(dadosInput, inss, irrf, descontoTotal / 100);
    }

    @Override
    public Boolean validaDadosObrigatorios() {
        Boolean isValid = Boolean.TRUE;
        if(etSalario.getText() == null || "".equals(etSalario.getText().toString().trim())){
            etSalario.setError("Preencha este campo!");
            isValid = Boolean.FALSE;
        }
        if(etNumDep.getText() == null || "".equals(etNumDep.getText().toString().trim())){
            etNumDep.setText("0");
        }
        return isValid;
    }
}