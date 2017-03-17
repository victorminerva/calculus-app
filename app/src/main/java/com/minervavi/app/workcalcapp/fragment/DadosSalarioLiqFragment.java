package com.minervavi.app.workcalcapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.mvp.fragment.FragmentPresenter;
import com.minervavi.app.workcalcapp.mvp.fragment.IFragment;

public class DadosSalarioLiqFragment extends Fragment implements IFragment.IFragmentView{

    IFragment.IFragmentPresenter fragmentPresenter;

    private CurrencyEditText    etSalario;
    private EditText            etNumDep;
    private CurrencyEditText    etDescontos;
    private ImageButton         btnAdd;
    private LinearLayout        llDescontos;

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
        fragmentPresenter = new FragmentPresenter();
        fragmentPresenter.setView(this.getContext());
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
                fragmentPresenter.onAddDescontoClick(etDescontos, btnAdd, llDescontos);
            }
        });
    }

    @Override
    public void init(View view) {
        this.llDescontos    = (LinearLayout) view.findViewById(R.id.ll_descontos);
        this.etDescontos    = (CurrencyEditText) view.findViewById(R.id.et_descontos);
        this.btnAdd         = (ImageButton) view.findViewById(R.id.btn_add);
        this.etNumDep       = (EditText) view.findViewById(R.id.et_num_dep);
        this.etSalario      = (CurrencyEditText ) view.findViewById(R.id.et_salario);
    }
}
