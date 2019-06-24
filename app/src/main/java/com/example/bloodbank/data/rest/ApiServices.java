package com.example.bloodbank.data.rest;

import com.example.bloodbank.data.model.general.bloodTypes.BloodTypes;
import com.example.bloodbank.data.model.general.categories.Categories;
import com.example.bloodbank.data.model.general.cities.Cities;
import com.example.bloodbank.data.model.general.governorates.Governorates;
import com.example.bloodbank.data.model.posts.posts.Posts;
import com.example.bloodbank.data.model.user.logIn.LogIn;
import com.example.bloodbank.data.model.user.newPassword.NewPassword;
import com.example.bloodbank.data.model.user.resetPassword.ResetPassword;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    @POST("register")
    @FormUrlEncoded
    Call<LogIn>addRegister(@Field("name") String name,
                              @Field("email") String email,
                              @Field("birth_date") String birth_date,
                              @Field("city_id") int city_id,
                              @Field("phone") String phone,
                              @Field("donation_last_date") String donation_last_date,
                              @Field("password") String password,
                              @Field("password_confirmation") String password_confirmation,
                              @Field("blood_type") String blood_type,
                              @Field("blood_type_id") int blood_type_id


                      );

    @POST("login")
    @FormUrlEncoded
    Call<LogIn> addLogin(@Field("phone") String phone,
                        @Field("password") String password
    );


    @GET("governorates")
    Call<Governorates> getGovernment();


    @GET("cities")

    Call<Cities> getCity(@Query("governorate_id") int governorate_id);



    @POST("reset-password")
    @FormUrlEncoded
    Call<ResetPassword> resetPassword(@Field("phone") String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<NewPassword> addNewPassword(@Field("password") String password,
                                     @Field("password_confirmation") String password_confirmation,
                                     @Field("pin_code") String pin_code,
                                     @Field("phone") String phone);


@GET("blood-types")
    Call<BloodTypes> getBooldTypes();


@GET("posts")
    Call<Posts> getPosts(@Query("api_token") String api_token,
                         @Query("page") int page);

@GET("categories")
    Call<Categories> getCategories();
}
