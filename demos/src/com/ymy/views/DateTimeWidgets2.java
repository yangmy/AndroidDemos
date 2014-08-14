package com.ymy.views;

import com.example.demos.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

public class DateTimeWidgets2 extends Activity{

	
	TimePicker timePicker;
	TextView display;
	
	
	int mHour=12;
	int mMinute=15;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datetimewidget2);
		
		timePicker=(TimePicker)findViewById(R.id.timepicker);
		display=(TextView)findViewById(R.id.display);
		
		timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				mHour=hourOfDay;
				mMinute=minute;
				updateDisplay();
			}
		});
		
	}
	
	public void updateDisplay(){
		display.setText(new StringBuilder().append(pad(mHour))
				.append(":").append(pad(mMinute)));
	}
	
	public String pad(int c){
		if(c>=10){
			return String.valueOf(c);
		}
		else{
			return "0"+String.valueOf(c);
		}
	}
}
