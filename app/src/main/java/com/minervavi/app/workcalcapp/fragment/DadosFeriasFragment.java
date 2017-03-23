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
import com.minervavi.app.workcalcapp.mvp.fragment.FragmentPresenter;
import com.minervavi.app.workcalcapp.mvp.fragment.IFragment;

public class DadosFeriasFragment extends Fragment implements IFragment.IFragmentView {

    private IFragment.IFragmentPresenter fragmentPresenter;

    private FloatingActionButton fab;
    private CurrencyEditText etvalormediohe;
    private EditText etdiasferias;
    private RadioGroup radioabonar;
    private RadioGroup radioadiantar;

    private SharedPreferences preferences;

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
        View view = inflater.inflate(R.layout.fragment_dados_ferias, container, false);
        init(view);

        fab.setOnClickListener(new View.OnClickListener() {
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

        fragmentPresenter = new FragmentPresenter();
        fragmentPresenter.setView(this.getContext());
        fragmentPresenter.setActivity(getActivity());

        this.etvalormediohe = (CurrencyEditText) view.findViewById(R.id.et_valor_medio_he);
        this.etdiasferias = (EditText) view.findViewById(R.id.et_dias_ferias);
        this.radioabonar = (RadioGroup) view.findViewById(R.id.radio_abonar);
        this.radioadiantar = (RadioGroup) view.findViewById(R.id.radio_adiantar);

        this.fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fragmentPresenter.recuperaDadosFerias(view, preferences, etvalormediohe, etdiasferias, radioabonar, radioadiantar);
        /*etvalormediohe.setText(preferences.getString(getString(R.string.key_valor_medio_hora_extra), ""));
        etdiasferias.setText(preferences.getString(getString(R.string.key_dias_de_ferias), ""));
        int abonoPecuniarioID   = preferences.getInt(getString(R.string.key_abono_pecuniario), -1);
        int adiantarDecima      = preferences.getInt(getString(R.string.key_adiantar_decima), -1);

        if(abonoPecuniarioID != -1) {
            RadioButton rbAbono = (RadioButton) view.findViewById(abonoPecuniarioID);
            rbAbono.setChecked(true);
        }
        if(adiantarDecima != -1) {
            RadioButton rbAdiantar = (RadioButton) view.findViewById(adiantarDecima);
            rbAdiantar.setChecked(true);
        }*/

    }

    @Override
    public void replaceContainerSlideWithFragCurrent() {
        ResultDadosFeriasFragment fragment = ResultDadosFeriasFragment.newInstance();
        fragmentPresenter.replaceContainerSlideWithFragCurrent(getFragmentManager(), fragment);
    }

    @Override
    public Boolean validaDadosObrigatorios() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentPresenter.manterDadosFerias(preferences, etvalormediohe, etdiasferias, radioabonar, radioadiantar);
    }
}
