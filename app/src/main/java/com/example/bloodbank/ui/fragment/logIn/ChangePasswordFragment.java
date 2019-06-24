package com.example.bloodbank.ui.fragment.logIn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.user.newPassword.NewPassword;
import com.example.bloodbank.data.rest.ApiServices;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.rest.RetrofitClient.getClient;
import static com.example.bloodbank.helper.helper.ree;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {


    @BindView(R.id.Fragment_Change_Password_Cb_Check_Code)
    EditText FragmentChangePasswordCbCheckCode;
    @BindView(R.id.Fragment_Change_Password_Cb_New_Password)
    EditText FragmentChangePasswordCbNewPassword;
    @BindView(R.id.Fragment_Change_Password_et_Confirm_Password)
    EditText FragmentChangePasswordEtConfirmPassword;
    @BindView(R.id.Fragment_Change_Password_btn_ChangePassword)
    Button FragmentChangePasswordBtnChangePassword;
    Unbinder unbinder;
    private ApiServices apiServices;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        unbinder = ButterKnife.bind(this, view);
         apiServices=getClient().create(ApiServices.class);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Fragment_Change_Password_btn_ChangePassword)
    public void onViewClicked() {
        String checkedCode = FragmentChangePasswordCbCheckCode.getText().toString();
        String newPassword = FragmentChangePasswordCbNewPassword.getText().toString();
        String newPasswordConfirm = FragmentChangePasswordEtConfirmPassword.getText().toString();
        String phone=getArguments().getString("phone");
        apiServices.addNewPassword(newPassword,newPasswordConfirm,checkedCode,phone).enqueue(new Callback<NewPassword>() {
            @Override
            public void onResponse(Call<NewPassword> call, Response<NewPassword> response) {
                if (response.body().getStatus()==1){
                    LoginOrRegisterFragment loginOrRegisterFragment=new LoginOrRegisterFragment();
                    ree(loginOrRegisterFragment,R.id.activity_log_in_fl_log_in_framework,getFragmentManager().beginTransaction());

                }
                else {
                    Toast.makeText(getContext(),"the status is not equal one",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<NewPassword> call, Throwable t) {
                Toast.makeText(getContext(),"this is on failure",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
