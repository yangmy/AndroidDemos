package com.ymy.views;

import java.util.Calendar;

import com.example.demos.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DateTimeDialog extends Activity{

	String mYear;
	String mMonth;
	String mDay;
	String mHour;
	String mMinute;
	String mSecond;
	
	
	Button changeDateBtn;
	Button changeTimeBtn;
	TextView display;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datedialog);
	}
	
	
}
