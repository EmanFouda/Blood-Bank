package com.example.bloodbank.ui.fragment.logIn;



import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.general.bloodTypes.BloodTypes;
import com.example.bloodbank.data.model.general.bloodTypes.bloodTypeData;
import com.example.bloodbank.data.model.general.cities.Cities;
import com.example.bloodbank.data.model.general.cities.CityData;
import com.example.bloodbank.data.model.general.governorates.GovernmentData;
import com.example.bloodbank.data.model.general.governorates.Governorates;
import com.example.bloodbank.data.model.user.logIn.LogIn;
import com.example.bloodbank.data.rest.ApiServices;
import com.example.bloodbank.ui.activity.HomeActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.rest.RetrofitClient.getClient;
import static com.example.bloodbank.helper.helper.showCalender;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    @BindView(R.id.Fragment_Create_New_account_Et_Name)
    EditText FragmentCreateNewAccountEtName;
    @BindView(R.id.Fragment_Create_New_account_Et_Email)
    EditText FragmentCreateNewAccountEtEmail;
    @BindView(R.id.Fragment_Create_New_account_Et_Birth_Date)
    TextView FragmentCreateNewAccountEtBirthDate;
    @BindView(R.id.Fragment_Create_New_account_Sp_Blood_No)
    Spinner FragmentCreateNewAccountSpBloodNo;
    @BindView(R.id._Fragment_Create_New_account_Et_Last_Donation_Date)
    TextView FragmentCreateNewAccountEtLastDonationDate;
    @BindView(R.id.Fragment_Create_New_account_Sp_Government)
    Spinner FragmentCreateNewAccountSpGovernment;
    @BindView(R.id.Fragment_Create_New_account_Sp_City)
    Spinner FragmentCreateNewAccountSpCity;
    @BindView(R.id.Fragment_Create_New_account_Et_Phone)
    EditText FragmentCreateNewAccountEtPhone;
    @BindView(R.id.Fragment_Create_New_account_Et_Password)
    EditText FragmentCreateNewAccountEtPassword;
    @BindView(R.id.Fragment_Create_New_account_Et_Confirm_Password)
    EditText FragmentCreateNewAccountEtConfirmPassword;
    @BindView(R.id.Fragment_Create_New_account_Btn_Create)
    Button FragmentCreateNewAccountBtnCreate;
    Unbinder unbinder;
    private ApiServices apiServices;
    private Call<Governorates> call;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String date;
    private Integer selctedCityId;
    private String bloodTypeSelected;
    private Integer blodIdSelected;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        apiServices = getClient().create(ApiServices.class);
        unbinder = ButterKnife.bind(this, view);



//        //make date picker listener
//        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                Log.i("registerFragment","on date set="+year+"/"+month+"/"+dayOfMonth);
//
//                date=year+"/"+(month+1)+"/"+dayOfMonth;
//                FragmentCreateNewAccountEtBirthDate.setText(date);
//
//            }
//
//        };


        getGovernment();
        getBloodId();

        return view;
    }

    private void getBloodId() {


        apiServices.getBooldTypes().enqueue(new Callback<BloodTypes>() {
            private List<bloodTypeData> bloodTypeList=new ArrayList();
            private List<String> bloodTypeName=new ArrayList();
            private  List<Integer> bloodTypeId=new ArrayList();

            @Override
            public void onResponse(Call<BloodTypes> call, Response<BloodTypes> response) {
                if(response.body().getStatus()==1){
                    bloodTypeList=response.body().getData();
                    for (int i=0;i<bloodTypeList.size();i++){
                        bloodTypeName.add(bloodTypeList.get(i).getName());
                        bloodTypeId.add(bloodTypeList.get(i).getId());
                    }
                    ArrayAdapter<String> bloodSppinerAdapter=new ArrayAdapter<String> (getActivity(), android.R.layout.simple_spinner_item, bloodTypeName); //selected item will look like a spinner set from XML
                    bloodSppinerAdapter.setDropDownViewResource(android.R.layout
                            .simple_spinner_dropdown_item);
                    FragmentCreateNewAccountSpBloodNo.setAdapter(bloodSppinerAdapter);
FragmentCreateNewAccountSpBloodNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         blodIdSelected = bloodTypeId.get(position);
         bloodTypeSelected=bloodTypeName.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
                }
                else {
                    Toast.makeText(getContext(),"status is not equal one",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<BloodTypes> call, Throwable t) {
Toast.makeText(getContext(),"this is on Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getGovernment() {

        apiServices.getGovernment().enqueue(new Callback<Governorates>() {
            private List<Integer> governmentIdList = new ArrayList<>();
            private List<String> govenmentNameList = new ArrayList<>();
            private List<GovernmentData> governmentList = new ArrayList<>();

            @Override
            public void onResponse(Call<Governorates> call, Response<Governorates> response) {
                if (response.body().getStatus() == 1) {
                    governmentList = response.body().getData();
                    for (int i = 0; i < governmentList.size(); i++) {
                        govenmentNameList.add(governmentList.get(i).getName());
                        governmentIdList.add(governmentList.get(i).getId());
                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                            (getActivity(), android.R.layout.simple_spinner_item, govenmentNameList); //selected item will look like a spinner set from XML
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                            .simple_spinner_dropdown_item);
                    FragmentCreateNewAccountSpGovernment.setAdapter(spinnerArrayAdapter);
                    FragmentCreateNewAccountSpGovernment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Integer slectedGovernmentId = governmentIdList.get(position);
                            getCity(slectedGovernmentId);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    Toast.makeText(getContext(), "status is not equal one", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Governorates> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getCity(Integer slectedGovernmentId) {
        apiServices.getCity(slectedGovernmentId).enqueue(new Callback<Cities>() {
            private List<CityData> cityList;

            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                if (response.body().getStatus() == 1) {
                    cityList = response.body().getData();
                    List<String> cityNameList = new ArrayList<>();
                    final List<Integer> cityIdList = new ArrayList<>();
                    for (int i = 0; i < cityList.size(); i++) {
                        cityNameList.add(cityList.get(i).getName());
                        cityIdList.add(cityList.get(i).getId());
                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                            (getActivity(), android.R.layout.simple_spinner_item, cityNameList); //selected item will look like a spinner set from XML
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                            .simple_spinner_dropdown_item);
                    FragmentCreateNewAccountSpCity.setAdapter(spinnerArrayAdapter);
                    FragmentCreateNewAccountSpCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                             selctedCityId = cityIdList.get(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                } else {
                    Toast.makeText(getContext(), "status does not equal one", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
                Toast.makeText(getContext(), "this is onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void register() {

    }


    @OnClick({R.id.Fragment_Create_New_account_Et_Birth_Date, R.id._Fragment_Create_New_account_Et_Last_Donation_Date, R.id.Fragment_Create_New_account_Btn_Create})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Fragment_Create_New_account_Et_Birth_Date:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog dialog=new DatePickerDialog(getContext(), AlertDialog.THEME_HOLO_DARK,mDateSetListener,year,month,dayOfMonth);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();


                showCalender(FragmentCreateNewAccountEtBirthDate, "BirthDate", getContext(), dayOfMonth, month, year);


                break;
            case R.id._Fragment_Create_New_account_Et_Last_Donation_Date:
                Calendar calendar1 = Calendar.getInstance();
                int year1 = calendar1.get(Calendar.YEAR);
                int month1 = calendar1.get(Calendar.MONTH);
                int dayOfMonth1 = calendar1.get(Calendar.DAY_OF_MONTH);
                showCalender(FragmentCreateNewAccountEtLastDonationDate, "BirthDate", getContext(), dayOfMonth1, month1, year1);

                break;

            case R.id.Fragment_Create_New_account_Btn_Create:
                String name = FragmentCreateNewAccountEtName.getText().toString();
                String email = FragmentCreateNewAccountEtEmail.getText().toString();
                String birthDate = FragmentCreateNewAccountEtBirthDate.getText().toString();
                String phone = FragmentCreateNewAccountEtPhone.getText().toString();
                String donationDate = FragmentCreateNewAccountEtLastDonationDate.getText().toString();
                String password = FragmentCreateNewAccountEtPassword.getText().toString();
                String confirmPassword = FragmentCreateNewAccountEtConfirmPassword.getText().toString();
apiServices.addRegister(name,email,birthDate,selctedCityId,phone,donationDate,password,confirmPassword,bloodTypeSelected,blodIdSelected).enqueue(new Callback<LogIn>() {
    @Override
    public void onResponse(Call<LogIn> call, Response<LogIn> response) {
        if (response.body().getStatus()==1){
            Intent intent=new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);

        }

        else {
            Toast.makeText(getContext(), "status is not equal one", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(Call<LogIn> call, Throwable t) {
        Toast.makeText(getContext(), "this is onFailure", Toast.LENGTH_SHORT).show();

    }
});

                break;
        }
    }
}