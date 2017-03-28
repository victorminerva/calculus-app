package com.minervavi.app.workcalcapp.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
                switch (position){
                    case 0:
                        break;
                    case 1:
                        showPrivacyPolicyDialog();
                        break;
                }

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void showPrivacyPolicyDialog() {
        final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.privacy_policy_dialog);

        TextView tvPrivacyPolicy = (TextView) dialog.findViewById(R.id.tv_privacy_policy);
        tvPrivacyPolicy.setText(Html.fromHtml(html));

        LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.ll_dialog);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private String html = "<h2>Privacy Policy</h2>\n" +
            "<p> Victor Minerva built the Calculus app as a Freemium app. This SERVICE is provided by Victor Minerva at no cost and is intended for use as is. </p>\n" +
            "<p> This page is used to inform website visitors regarding my policies with the collection, use, and disclosure of Personal Information if anyone decided to use my Service. </p>\n" +
            "<p> If you choose to use my Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that I collect is used for providing and improving the Service. I will not use or share your information with anyone except as described in this Privacy Policy. </p>\n" +
            "<p>The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which is accessible at Calculus unless otherwise defined in this Privacy Policy. </p>\n" +
            "<p><strong>Information Collection and Use</strong></p>\n" +
            "<p> For a better experience, while using our Service, I may require you to provide us with certain personally identifiable information. The information that I request is retained on your device and is not collected by me in any way </p>\n" +
            "<p> The app does use third party services that may collect information used to identify you. </p>\n" +
            "<p><strong>Log Data</strong></p>\n" +
            "<p> I want to inform you that whenever you use my Service, in a case of an error in the app I collect data and information (through third party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing my Service, the time and date of your use of the Service, and other statistics. </p>\n" +
            "<p><strong>Cookies</strong></p>\n" +
            "<p> Cookies are files with small amount of data that is commonly used an anonymous unique identifier. These are sent to your browser from the website that you visit and are stored on your device internal memory. </p>\n" +
            "<p> This Service does not use these “cookies” explicitly. However, the app may use third party code and libraries that use “cookies” to collection information and to improve their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use some portions of this Service. </p>\n" +
            "<p><strong>Service Providers</strong></p>\n" +
            "<p> I may employ third-party companies and individuals due to the following reasons:</p>\n" +
            "<ul>\n" +
            "   <li>To facilitate our Service;</li>\n" +
            "   <li>To provide the Service on our behalf;</li>\n" +
            "   <li>To perform Service-related services; or</li>\n" +
            "   <li>To assist us in analyzing how our Service is used.</li>\n" +
            "</ul>\n" +
            "<p> I want to inform users of this Service that these third parties have access to your Personal Information. The reason is to perform the tasks assigned to them on our behalf. However, they are obligated not to disclose or use the information for any other purpose. </p>\n" +
            "<p><strong>Security</strong></p>\n" +
            "<p> I value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and I cannot guarantee its absolute security. </p>\n" +
            "<p><strong>Links to Other Sites</strong></p>\n" +
            "<p>This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by me. Therefore, I strongly advise you to review the Privacy Policy of these websites. I have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services. </p>\n" +
            "<p><strong>Children’s Privacy</strong></p>\n" +
            "<p>These Services do not address anyone under the age of 13. I do not knowingly collect personally identifiable information from children under 13. In the case I discover that a child under 13 has provided me with personal information, I immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact me so that I will be able to do necessary actions </p>\n" +
            "<p><strong>Changes to This Privacy Policy</strong></p>\n" +
            "<p> I may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Privacy Policy on this page. These changes are effective immediately after they are posted on this page. </p>\n" +
            "<p><strong>Contact Us</strong></p>\n" +
            "<p>If you have any questions or suggestions about my Privacy Policy, do not hesitate to contact me. </p>\n" +
            "<p>This Privacy Policy page was created at <a href=\"https://privacypolicytemplate.net\" target=\"_blank\">privacypolicytemplate.net</a> and modified/generated by <a href=\"https://app-privacy-policy-generator.firebaseapp.com/\" target=\"_blank\">App Privacy Policy Generator</a></p>\n";
}
