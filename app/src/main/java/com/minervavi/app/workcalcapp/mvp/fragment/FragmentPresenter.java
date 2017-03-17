package com.minervavi.app.workcalcapp.mvp.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minervavi.app.workcalcapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victo on 14/03/2017.
 */

public class FragmentPresenter implements IFragment.IFragmentPresenter {

    Context context;

    private List<String> listOfDescontos;

    public FragmentPresenter(){
        listOfDescontos = new ArrayList<>();
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setView(Context context) {
        this.context = context;
    }

    @Override
    public void onAddDescontoClick(final EditText etDesconto, final ImageButton btnAdd, final LinearLayout llDesconto) {
        if ("".equals(etDesconto.getText().toString())) {
            etDesconto.setError("Preencha este campo!");
        } else {
            listOfDescontos.add(etDesconto.getText().toString().replaceAll("[R$,.]", ""));

            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowDesconto = layoutInflater.inflate(R.layout.row_desconto, null);

            final TextView tvDesconto = (TextView) rowDesconto.findViewById(R.id.tv_desconto);
            ImageButton btnRemove = (ImageButton) rowDesconto.findViewById(R.id.remove);

            tvDesconto.setText(etDesconto.getText().toString());

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((LinearLayout) rowDesconto.getParent()).removeView(rowDesconto);
                    String valorDesconto = tvDesconto.getText().toString().replaceAll("[R$,.]", "");
                    if(listOfDescontos.contains(valorDesconto))
                        listOfDescontos.remove(valorDesconto);

                    btnAdd.setVisibility(View.VISIBLE);
                    etDesconto.setVisibility(View.VISIBLE);
                }
            });

            llDesconto.addView(rowDesconto, 1);

            etDesconto.setText("");

            if (listOfDescontos.size() == 8) {
                btnAdd.setVisibility(View.GONE);
                etDesconto.setVisibility(View.GONE);
            }
        }
    }
}
