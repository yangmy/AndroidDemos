package com.ymy.views;

import java.util.Calendar;

import com.example.demos.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DateTimewidgets extends Activity{

	int DATE_DIALOG=1;
	int TIME_DIALOG=0;
	
	
	int mYear;
	int mMonth;
	int mDay;
	int mHour;
	int mMinute;
	
	
	Button changeDateBtn;
	Button changeTimeBtn;
	TextView display;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datetimewidget1);
	
		changeDateBtn=(Button)findViewById(R.id.changedatebtn);
		changeTimeBtn=(Button)findViewById(R.id.changetimebtn);
		display=(TextView)findViewById(R.id.display);
		
		
		changeDateBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG);
			}
		});
		
		changeTimeBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOG);
			}
		});
		Calendar c=Calendar.getInstance();
		mYear=c.get(Calendar.YEAR);
		mMonth=c.get(Calendar.MONTH);
		mDay=c.get(Calendar.DAY_OF_MONTH);
		mHour=c.get(Calendar.HOUR_OF_DAY);
		mMinute=c.get(Calendar.MINUTE);
		
		updateDisplay();
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		if(id==DATE_DIALOG){
			return new DatePickerDialog(this,mDateListener, mYear, mMonth, mDay);
		}
		if(id==TIME_DIALOG){
			return new TimePickerDialog(this, mTimeListener, mHour, mMinute, true);
		}
		return null;
	}
	
	
	public OnDateSetListener mDateListener=new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear=year;
			mMonth=monthOfYear;
			mDay=dayOfMonth;
			updateDisplay();
		}
	};
	
	public OnTimeSetListener mTimeListener=new TimePickerDialog.OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			
			mHour=hourOfDay;
			mMinute=minute;
			updateDisplay();
		}
	};
	
	
	public void updateDisplay(){
		StringBuilder dateTime=new StringBuilder()
									.append(mYear)
									.append("-")
									.append(mMonth+1)
									.append("-")
									.append(mDay)
									.append("  ")
									.append(mHour)
									.append(":")
									.append(mMinute)
									;
		display.setText(dateTime);
	}
	
	
}
