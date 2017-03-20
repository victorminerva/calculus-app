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

public class DadosFeriasFragment extends Fragment implements IFragment.IFragmentView {

    private IFragment.IFragmentPresenter fragmentPresenter;

    private FloatingActionButton fab;

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
        fragmentPresenter = new FragmentPresenter();
        fragmentPresenter.setView(this.getContext());
        fragmentPresenter.setActivity(getActivity());

        this.fab = (FloatingActionButton) view.findViewById(R.id.fab);
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

}
