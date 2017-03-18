package com.minervavi.app.workcalcapp.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.mvp.fragment.IFragment;

public class DadosFeriasFragment extends Fragment implements IFragment.IFragmentView {

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
                Toast.makeText(getContext(), "Fragment FERIAS", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void init(View view) {
        this.fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }
}
