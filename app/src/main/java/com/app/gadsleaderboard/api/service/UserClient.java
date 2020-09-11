package com.app.gadsleaderboard.api.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserClient {
    @FormUrlEncoded
    @POST("1FAIpQLScqImoJGLser5zI6XU9T_tGf4-WqzF4wBbRYiEZ0GC4YCAsrg/formResponse")
    Call<ResponseBody> sendUserFeedback(
            @Field("entry.2005620554") String firstname,
            @Field("entry.1045781291") String lastname,
            @Field("entry.1065046570") String email,
            @Field("entry.1166974658") String link

    );

}
