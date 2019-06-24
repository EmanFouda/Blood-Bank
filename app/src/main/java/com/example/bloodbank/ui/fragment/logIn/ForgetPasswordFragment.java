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
import com.example.bloodbank.data.model.user.resetPassword.ResetPassword;
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
public class ForgetPasswordFragment extends Fragment {


    @BindView(R.id.Frgment_Forget_Password_Ed_Mobile)
    EditText FrgmentForgetPasswordEdMobile;
    @BindView(R.id.Fragment_Forget_Password_Btn_Change_Password)
    Button FragmentForgetPasswordBtnChangePassword;
    Unbinder unbinder;
    private ApiServices apiServices;

    public ForgetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiServices=getClient().create(ApiServices.class);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Fragment_Forget_Password_Btn_Change_Password)
    public void onViewClicked() {
        final String mobile = FrgmentForgetPasswordEdMobile.getText().toString();
     apiServices.resetPassword(mobile).enqueue(new Callback<ResetPassword>() {
         @Override
         public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {
            if (response.body().getStatus()==1){

                ChangePasswordFragment changePasswordFragment=new ChangePasswordFragment();
                Bundle args=new Bundle();
                args.putString("phone",mobile);
                changePasswordFragment.setArguments(args);

                ree(changePasswordFragment,R.id.activity_log_in_fl_log_in_framework,getFragmentManager().beginTransaction());

            }
            else{
                Toast.makeText(getContext(),"status not equal one",Toast.LENGTH_SHORT).show();

            }
         }

         @Override
         public void onFailure(Call<ResetPassword> call, Throwable t) {
             Toast.makeText(getContext(),"this is onFailure",Toast.LENGTH_SHORT).show();
         }
     });
    }
}
