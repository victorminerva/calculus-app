package com.minervavi.app.workcalcapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.minervavi.app.workcalcapp.R;

public class SettingsFragment extends Fragment {

    protected ListView lvSettings;
    protected ArrayAdapter<String> settingsAdapter;

    protected String[] itemsAdapter;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        lvSettings = (ListView) view.findViewById(R.id.lv_settings);

        itemsAdapter = new String[]{"Sobre", "Politica de Privacidade"};
        settingsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, itemsAdapter);

        lvSettings.setAdapter(settingsAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
