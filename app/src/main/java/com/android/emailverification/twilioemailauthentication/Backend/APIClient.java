package com.android.emailverification.twilioemailauthentication.Backend;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/**
 * Created by Ahmed Sadiq
 */
public interface APIClient {
    /**
     * Update your end point here to link this call to your own server
     */
    @POST("/API END POINT")
    @FormUrlEncoded
    Call<ResponseModel> sendVerificationCode(@FieldMap Map<String, String> params);
}