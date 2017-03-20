package com.minervavi.app.workcalcapp.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.mvp.fragment.FragmentPresenter;
import com.minervavi.app.workcalcapp.mvp.fragment.IFragment;

public class DadosDecimoFragment extends Fragment implements IFragment.IFragmentView {

    private IFragment.IFragmentPresenter fragmentPresenter;

    private FloatingActionButton fab;

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
        View view = inflater.inflate(R.layout.fragment_dados_decimo, container, false);
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
        this.fragmentPresenter = new FragmentPresenter();
        this.fragmentPresenter.setView(this.getContext());
        this.fragmentPresenter.setActivity(getActivity());

        this.fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    @Override
    public void replaceContainerSlideWithFragCurrent() {
        ResultDadosDecimoFragment fragment = ResultDadosDecimoFragment.newInstance();
        fragmentPresenter.replaceContainerSlideWithFragCurrent(getFragmentManager(), fragment);
    }

    @Override
    public Boolean validaDadosObrigatorios() {
        return null;
    }

}
