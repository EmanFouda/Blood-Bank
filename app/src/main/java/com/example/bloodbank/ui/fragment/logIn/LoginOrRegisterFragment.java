package com.example.bloodbank.ui.fragment.logIn;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.user.logIn.LogIn;
import com.example.bloodbank.data.rest.ApiServices;
import com.example.bloodbank.ui.activity.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bloodbank.data.rest.RetrofitClient.getClient;
import static com.example.bloodbank.helper.helper.ree;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginOrRegisterFragment extends Fragment {


    @BindView(R.id.mobile_et)
    EditText mobileEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.remember_cb)
    CheckBox rememberCb;
    @BindView(R.id.forget_password_tv)
    TextView forgetPasswordTv;
    @BindView(R.id.warning)
    ImageView warning;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.register_btn)
    Button registerBtn;
    Unbinder unbinder;
    private ApiServices apiServices;
    private String moile;
    private String password;
    private SharedPreferences mPrefs;
    private static final String PREFS_NAME="PrefsFile";
    private SharedPreferences sp;
    private boolean boolIsChecked;
    private boolean savedLogin;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public LoginOrRegisterFragment() {
        // Required empty public constructor
    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_or_register, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiServices=getClient().create(ApiServices.class);
        mPrefs=getContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        rememberme();

      //another method for shared preference
       // getPrefernceData();




        return view;
    }

    private void rememberme() {
         sharedPreferences=getContext().getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        editor=sharedPreferences.edit();
        savedLogin=sharedPreferences.getBoolean("checked",false);
        if (savedLogin==true){
            mobileEt.setText(sharedPreferences.getString("username", ""));
            passwordEt.setText(sharedPreferences.getString("password", ""));
            rememberCb.setChecked(true);
        }
    }




//    private void getPrefernceData() {
//         sp=getContext().getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
//        Toast.makeText(getContext(),sp.getString("pref_name","not"),Toast.LENGTH_SHORT).show();
//
//        if (sp.contains("pref_name")){
//            Toast.makeText(getContext(),"eshtaghal",Toast.LENGTH_SHORT).show();
//
//            String saved_mobile=sp.getString("pref_name","not found");
//            mobileEt.setText(saved_mobile);
//        }
//        if (sp.contains("pref_pass")){
//            String saved_pass=sp.getString("pref_pass","not found");
//            passwordEt.setText(saved_pass);
//        }
//        if (sp.contains("pref_check")){
//            boolean saved_check=sp.getBoolean("pref_check",false);
//            rememberCb.setChecked(saved_check);
//        }
//
//
//
//    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.remember_cb, R.id.forget_password_tv, R.id.login_btn, R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.remember_cb:

                break;
            case R.id.forget_password_tv:
                ForgetPasswordFragment forgetPasswordFragment=new ForgetPasswordFragment();
                ree(forgetPasswordFragment,R.id.activity_log_in_fl_log_in_framework,getFragmentManager().beginTransaction());
                break;
            case R.id.login_btn:

                logIn();
               //another method for shared preference
              //  logIn2();
               // rememberMe2();

                break;
            case R.id.register_btn:
                RegisterFragment registerFragment=new RegisterFragment();
                ree(registerFragment,R.id.activity_log_in_fl_log_in_framework,getFragmentManager().beginTransaction());
                break;
        }
    }

    private void logIn() {
        moile = mobileEt.getText().toString();
        password=passwordEt.getText().toString();
        Call<LogIn> call=apiServices.addLogin(moile,password);
        call.enqueue(new Callback<LogIn>() {
            @Override
            public void onResponse(Call<LogIn> call, Response<LogIn> response) {
                if (response.body().getStatus()==1){
                    if(rememberCb.isChecked()){
                   editor.putString("username",moile);
                   editor.putString("password",password);
                   editor.putBoolean("checked",true);
                   editor.commit();
                    }
                    else
                    {
                        editor.clear();
                        editor.commit();
                    }
                    Intent intent=new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getContext(),"status is not equal one ",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LogIn> call, Throwable t) {

            }
        });
    }


//    private void rememberMe2() {
//        if (rememberCb.isChecked()){
//             boolIsChecked=rememberCb.isChecked();
//            SharedPreferences.Editor editor=mPrefs.edit();
//            editor.putString("pref_name",moile);
//            editor.putString("pref_pass",password);
//            editor.putBoolean("pref_check",boolIsChecked);
//
//            editor.apply();
//            Toast.makeText(getContext(),"settings are saved",Toast.LENGTH_SHORT).show();
//
//        }
//        else {
//            mPrefs.edit().clear().apply();
//        }
//    }




//    public void   logIn2(){
//      moile = mobileEt.getText().toString();
//      password=passwordEt.getText().toString();
//      Call<LogIn> call= apiServices.addLogin(moile,password);
//      call.enqueue(new Callback<LogIn>() {
//          @Override
//          public void onResponse(Call<LogIn> call, Response<LogIn> response) {
//              LogIn loginResponse = response.body();
//              if(response.isSuccess()){
//                  //save user
//                  //openProfile
//                  Toast.makeText(getContext(),loginResponse.getMsg(),Toast.LENGTH_SHORT).show();
//
//              }
//              else {
//                Toast.makeText(getContext(),loginResponse.getMsg(),Toast.LENGTH_SHORT).show();
//              }
//
//          }
//
//          @Override
//          public void onFailure(Call<LogIn> call, Throwable t) {
//              Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
//
//          }
//      });
//
//  }



}
