package com.minervavi.app.workcalcapp.mvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.minervavi.app.workcalcapp.mvp.app.IApp;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.util.List;

/**
 * Created by victo on 14/03/2017.
 */

public interface IFragment {

    interface IFragmentModel {

    }

    interface IFragmentPresenter {
        Context getContext();

        void setView(Context context);

        void setViewApp(IApp.IAppView appView);

        void setActivity(Activity activity);

        List<String> onAddDescontoClick(final CurrencyEditText etDesconto, final ImageButton btnAdd, final LinearLayout llDesconto);

        List<String> onRemoveDescontoClick(View rowDesconto, final CurrencyEditText etDesconto, TextView tvDesconto, final ImageButton btnAdd);

        void showSlideUpFragCurrent(FloatingActionButton fab);

        void replaceContainerSlideWithFragCurrent(FragmentManager fragManager, Fragment fragment);

        void manterDadosSalarioLiq(SharedPreferences preferences, EditText etSalario, EditText etNumDep, List<String> listDescontos);

        void manterDadosFerias(SharedPreferences preferences, CurrencyEditText etvalormediohe, EditText etdiasferias, RadioGroup radioabonar, RadioGroup radioadiantar);

        void recuperaDadosFerias(View view, SharedPreferences preferences, CurrencyEditText etvalormediohe, EditText etdiasferias, RadioGroup radioabonar, RadioGroup radioadiantar);

        void manterDadosHoraExtra(SharedPreferences preferences, MaskedEditText etJornadaMensal, MaskedEditText etAdicionalHE, MaskedEditText etNumHoraExtra);

        void recuperaDadosHoraExtra(SharedPreferences preferences, MaskedEditText etJornadaMensal, MaskedEditText etAdicionalHE, MaskedEditText etNumHoraExtra);

        List<String> retrieveListOfDesconts();
    }

    interface IFragmentView {

        void init(View view);

        void replaceContainerSlideWithFragCurrent();

        Boolean validaDadosObrigatorios();
    }
}
