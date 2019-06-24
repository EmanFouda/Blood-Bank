package com.example.bloodbank.helper;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class helper {



        public static void ree(Fragment fragment, int id, FragmentTransaction fragmentTransaction) {
            FragmentTransaction transaction = fragmentTransaction;
            transaction.replace(id, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }



    //Calender
    public static String sDay, sMonth;
    public static int sYear;

    public static void showCalender(final TextView textView, String title, Context context
            , int mDay, int mMonth, int mYear) {
//        Calendar mCurrentDate = Calendar.getInstance();
        DatePickerDialog mDatePicker = new DatePickerDialog(context, AlertDialog.THEME_HOLO_DARK, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                selectedMonth=selectedMonth+1;
                if (selectedDay<10){
                    sDay="0"+selectedDay;
                }else{
                    sDay = ""+selectedDay;
                }
                if (selectedMonth<10){
                    sMonth="0"+selectedMonth;
                }else{
                    sMonth = ""+selectedMonth;

                }
                textView.setText(selectedYear + "-" + sMonth + "-" + sDay);

            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle(title);
        mDatePicker.show();
    }

}
