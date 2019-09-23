package com.optisoft.emaguard.Webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.optisoft.emaguard.Activity.AddVisitorActivity;
import com.optisoft.emaguard.Activity.ForgotPasswordActivity;
import com.optisoft.emaguard.Activity.ListVisitorActivity;
import com.optisoft.emaguard.Activity.LoginActivity;
import com.optisoft.emaguard.Activity.ProfileActivity;
import com.optisoft.emaguard.Firebase.NotificationActivity;
import com.optisoft.emaguard.Fragments.GuardDashboardFragment;
import com.optisoft.emaguard.Model.ResponseModel;
import com.optisoft.emaguard.R;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_BASE_URL;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.SUCCESS_TAG;

public class CallApi {

    public ProgressDialog progressDialog;
    Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .disableHtmlEscaping()
            .setLenient()
            .create();

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS).build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();


    ApiService service = retrofit.create(ApiService.class);

    public void dialogShow(Context context, String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(msg);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public void dialogHide() {
        if (progressDialog != null) {
            if (progressDialog.isShowing())
                progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void requestLogin(final LoginActivity context, HashMap<String, String> map) {
        dialogShow(context, "processing...");
        Call<ResponseModel> call = service.requestLogin(map);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    context.responseLogin(response.body());
                    Log.e(SUCCESS_TAG, response.body().toString());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }

    public void requestAgentLogin(final LoginActivity context, HashMap<String, String> map) {
        dialogShow(context, "processing...");
        Call<ResponseModel> call = service.requestAgentLogin(map);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    context.responseLogin(response.body());
                    Log.e(SUCCESS_TAG, response.body().toString());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }

    public void requestGuardLogin(final LoginActivity context, HashMap<String, String> map) {
        dialogShow(context, "processing...");
        Call<ResponseModel> call = service.requestGuardLogin(map);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    context.responseLogin(response.body());
                    Log.e(SUCCESS_TAG, response.body().toString());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }

    public void requestUpdateProfile(final ProfileActivity context, HashMap<String, RequestBody> map) {
        dialogShow(context, "processing...");
        Call<ResponseModel> call = service.requestUpdateProfile( map);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    Log.e(SUCCESS_TAG, response.body().getMessage());
                    context.responseUpdateProfile(response.body());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }


    public void requestresetPassword(final ProfileActivity context, HashMap<String, String> map) {
        dialogShow(context, "processing...");
        Call<ResponseModel> call = service.requestresetPassword(map);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    context.requestresetPassword(response.body());
                    Log.e(SUCCESS_TAG, response.body().toString());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }

    public void requestForgotPassword(final ForgotPasswordActivity context, HashMap<String, String> map) {
        dialogShow(context, "processing...");
        Call<ResponseModel> call = service.requestForgotPassword(map);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    context.requestForgotPassword(response.body());
                    Log.e(SUCCESS_TAG, response.body().toString());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }

    public void requestReplyVisiter(final com.optisoft.emaguard.Firebase.NotificationActivity context, HashMap<String, String> map) {
        dialogShow(context, "processing...");
        Call<ResponseModel> call = service.requestReplyVisiter(map);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    context.requestReplyVisiter(response.body());
                    Log.e(SUCCESS_TAG, response.body().toString());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }

    public void requestSetExtension(final NotificationActivity context, HashMap<String, String> map){
        dialogShow(context, "processing...");
        Call<ResponseModel> call = service.requestSetExtension(map);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    Log.e(SUCCESS_TAG, response.body().toString());
                    context.responseSetExtension(response.body());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }

    public void requestGuardUsers(final AddVisitorActivity context, String agentId ) {
        dialogShow(context, "processing...");
        Call<ResponseModel> call = service.requestGuardUsers(agentId);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    context.responseGuardUsers(response.body());
                    Log.e(SUCCESS_TAG, response.body().toString());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }

    public void requestEntryVisitor(final AddVisitorActivity context, HashMap<String, RequestBody> map) {
        dialogShow(context, "processing...");
        Call<ResponseModel> call = service.requestEntryVisitor( map);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    Log.e(SUCCESS_TAG, response.body().getMessage());
                    context.responseEntryVisitor(response.body());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }

    public void requestUpdateVisitor(final AddVisitorActivity context, HashMap<String, RequestBody> map) {
        dialogShow(context, "processing...");
        Call<ResponseModel> call = service.requestUpdateVisitor( map);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    Log.e(SUCCESS_TAG, response.body().getMessage());
                    context.responseUpdateVisitor(response.body());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }

    public void requestLoadVisitor(final ListVisitorActivity context, String guard_id, String date) {
        dialogShow(context, "processing...");
        Call<ResponseModel> call = service.requestLoadVisitor( guard_id, date);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    Log.e(SUCCESS_TAG, response.body().getMessage());
                    context.responseLoadVisitor(response.body());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }
    public void requestLoadGuardData(final GuardDashboardFragment context, String guard_id, String date) {
        dialogShow(context.getActivity(), "processing...");
        Call<ResponseModel> call = service.requestLoadGuardData( guard_id, date);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                dialogHide();
                if (response.body() != null){
                    Log.e(SUCCESS_TAG, response.body().getMessage());
                    context.responseLoadVisitor(response.body());
                }else {
                    context.customToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("OTH_RES_Error", t.toString());
                dialogHide();
                context.customToast(context.getString(R.string.server_not_responding));
            }
        });
    }
}
