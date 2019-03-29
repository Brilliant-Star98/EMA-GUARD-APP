package com.optisoft.emaguard.Webservices;


import com.optisoft.emaguard.Model.ResponseModel;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_DISTRESS_MESSAGES;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_FORGOT_PASSWORD;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_LOAD_GUARD_DATA;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_LOAD_GUARD_USERS;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_LOAD_VISITORS;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_LOGIN;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_LOGIN_AGENT;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_LOGIN_GUARD;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_REPLY_VISITER;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_RESET_PASSWORD;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_UPDATE_PROFILE;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_VISITOR_ENTRY;
import static com.optisoft.emaguard.HelperClasses.ApiConstant.API_VISITOR_UPDATE;


/**
 * Created by OptiSoft_A on 8/30/2017.
 */

 public interface ApiService {

    @FormUrlEncoded
    @POST(API_LOGIN)
    Call<ResponseModel> requestLogin(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST(API_LOGIN_AGENT)
    Call<ResponseModel> requestAgentLogin(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST(API_LOGIN_GUARD)
    Call<ResponseModel> requestGuardLogin(@FieldMap HashMap<String, String> params);

    @Multipart
    @POST(API_UPDATE_PROFILE)
    Call<ResponseModel> requestUpdateProfile(@PartMap HashMap<String, RequestBody> param);

    @Multipart
    @POST(API_VISITOR_ENTRY)
    Call<ResponseModel> requestEntryVisitor(@PartMap HashMap<String, RequestBody> param);

    @Multipart
    @POST(API_VISITOR_UPDATE)
    Call<ResponseModel> requestUpdateVisitor(@PartMap HashMap<String, RequestBody> param);

    @GET(API_LOAD_GUARD_DATA)
    Call<ResponseModel> requestLoadGuardData(@Path("guard_id") String guard_id, @Path("date") String date);

    @GET(API_LOAD_VISITORS)
    Call<ResponseModel> requestLoadVisitor(@Path("guard_id") String guard_id, @Path("date") String date);

    @GET(API_DISTRESS_MESSAGES)
    Call<ResponseModel> requestDistressMessages(@Path("user_id") String agent_codeSt);

    @GET(API_LOAD_GUARD_USERS)
    Call<ResponseModel> requestGuardUsers(@Path("agent_id") String agentId);

   @FormUrlEncoded
   @POST(API_RESET_PASSWORD)
   Call<ResponseModel> requestresetPassword(@FieldMap HashMap<String, String> params);

   @FormUrlEncoded
   @POST(API_FORGOT_PASSWORD)
   Call<ResponseModel> requestForgotPassword(@FieldMap HashMap<String, String> params);

   @FormUrlEncoded
   @POST(API_REPLY_VISITER)
   Call<ResponseModel> requestReplyVisiter(@FieldMap HashMap<String, String> params);

}