package com.minervavi.app.workcalcapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.minervavi.app.workcalcapp.R;
import com.minervavi.app.workcalcapp.adapter.ItemSettingsAdapter;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    ArrayList<String> dataModels;
    protected ListView lvSettings;
    private static ItemSettingsAdapter adapter;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataModels = new ArrayList<>();
        dataModels.add("Sobre");
        dataModels.add("Politica de Privacidade");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        lvSettings = (ListView) view.findViewById(R.id.lv_settings);

        adapter = new ItemSettingsAdapter(dataModels, getContext().getApplicationContext());

        lvSettings.setAdapter(adapter);

        lvSettings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Teste", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
