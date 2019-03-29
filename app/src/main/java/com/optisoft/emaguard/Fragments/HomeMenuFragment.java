package com.optisoft.emaguard.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.optisoft.emaguard.Activity.AddVisitorActivity;
import com.optisoft.emaguard.Activity.ListVisitorActivity;
import com.optisoft.emaguard.Activity.LoginActivity;
import com.optisoft.emaguard.Activity.ProfileActivity;
import com.optisoft.emaguard.HelperClasses.CommonPrefrence;
import com.optisoft.emaguard.Model.UserModel;
import com.optisoft.emaguard.R;
import com.optisoft.emaguard.Webservices.CallApi;
import com.squareup.picasso.Picasso;

import static com.optisoft.emaguard.HelperClasses.ApiConstant.IMAGE_URL;


public class HomeMenuFragment extends Fragment implements View.OnClickListener {

    private TextView notification_tv, my_dues_tv, payment_history_tv, contact_us_tv, logout_tv, tv_distress_message, contact_us_tv_agent, tv_distress_message_agent;
    private TextView full_name, phone, edit_profile, tv_add_visitor, tv_list_visitor;
    private LinearLayout ly_agent, ly_user, ly_guard;
    private CommonPrefrence commonPrefrence;
    private UserModel userModel;
    private ImageView profile_image;
    private int roleId = 3;
    private CallApi callApi = new CallApi();
    private Gson gson = new Gson();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home_menu, container, false);
        commonPrefrence = new CommonPrefrence();
        userModel = commonPrefrence.getUserLoginSharedPref(getActivity());
        roleId = Integer.parseInt(userModel.getRoleId());

        full_name =(TextView)view.findViewById(R.id.full_name);
        phone =(TextView)view.findViewById(R.id.phone);
        profile_image =(ImageView)view.findViewById(R.id.profile_image);
        ly_agent =(LinearLayout) view.findViewById(R.id.ly_agent);
        ly_user  =(LinearLayout) view.findViewById(R.id.ly_user);
        ly_guard  =(LinearLayout) view.findViewById(R.id.ly_guard);


        notification_tv =(TextView)view.findViewById(R.id.notification_tv);
        my_dues_tv =(TextView)view.findViewById(R.id.my_dues_tv);
        payment_history_tv =(TextView)view.findViewById(R.id.payment_history_tv);
        contact_us_tv =(TextView)view.findViewById(R.id.contact_us_tv);
        logout_tv =(TextView)view.findViewById(R.id.logout_tv);
        edit_profile =(TextView)view.findViewById(R.id.edit_profile);
        tv_add_visitor =(TextView)view.findViewById(R.id.tv_add_visitor);
        tv_list_visitor =(TextView)view.findViewById(R.id.tv_list_visitor);
        tv_distress_message =(TextView)view.findViewById(R.id.tv_distress_message);
        contact_us_tv_agent =(TextView)view.findViewById(R.id.contact_us_tv_agent);
        tv_distress_message_agent =(TextView)view.findViewById(R.id.tv_distress_message_agent);


        tv_distress_message_agent.setOnClickListener(this);
        contact_us_tv_agent.setOnClickListener(this);
        tv_distress_message.setOnClickListener(this);
        notification_tv.setOnClickListener(this);
        my_dues_tv.setOnClickListener(this);
        payment_history_tv.setOnClickListener(this);
        contact_us_tv.setOnClickListener(this);
        logout_tv.setOnClickListener(this);
        edit_profile.setOnClickListener(this);
        tv_add_visitor.setOnClickListener(this);
        tv_list_visitor.setOnClickListener(this);

        if (userModel != null){
            setNavView();
        }
        return view;
    }

    private void setNavView() {
        if (!userModel.getFname().isEmpty())
        full_name.setText(userModel.getFname()+" "+ userModel.getLname());
        phone.setText("+"+userModel.getCountry_code()+"-"+userModel.getMobile());
        Picasso.with(getActivity()).load(IMAGE_URL+userModel.getProfile_picture()).placeholder(R.drawable.user).error(R.drawable.user).into(profile_image);

        if (roleId == 2){
            edit_profile.setVisibility(View.GONE);
            ly_user.setVisibility(View.GONE);
            ly_guard.setVisibility(View.GONE);
            ly_agent.setVisibility(View.VISIBLE);
        }else if (roleId == 4){
            edit_profile.setVisibility(View.GONE);
            ly_user.setVisibility(View.GONE);
            ly_agent.setVisibility(View.GONE);
            ly_guard.setVisibility(View.VISIBLE);
        }else {
            edit_profile.setVisibility(View.VISIBLE);
            ly_user.setVisibility(View.VISIBLE);
            ly_agent.setVisibility(View.GONE);
            ly_guard.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit_profile:
                Intent intent0 = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent0);
                break;

            case R.id.logout_tv:
                callLogout();
                break;
            case R.id.tv_add_visitor:
                callAddVisitor();
                break;
            case R.id.tv_list_visitor:
                callListVisitor();
                break;

        }
    }

    private void callListVisitor() {
        Intent intent = new Intent(getActivity(), ListVisitorActivity.class);
        startActivity(intent);
    }

    private void callAddVisitor() {
        Intent intent = new Intent(getActivity(), AddVisitorActivity.class);
        startActivity(intent);
    }


    private void callLogout() {
        commonPrefrence.setUserLoginSharedPref(getActivity(), null);
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finishAffinity();
    }

    public void customToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
